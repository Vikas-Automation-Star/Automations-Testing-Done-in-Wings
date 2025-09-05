package com.wings.utils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

public class OptimizedJsonExcelComparator {

    private static Set<String> IGNORE_COLUMNS = new HashSet<>();

    private static class TableMapping {
        final String sheetName;
        final String virtualName;
        TableMapping(String sheetName, String virtualName) {
            this.sheetName = sheetName;
            this.virtualName = virtualName;
        }
    }

    public static void JsonExcelComparator(String jsonFilePath,String excelFilePath,String transactionType) throws Exception {

        String timedStamp=Time.timeStamp();
        String diffFilePath = "./output/excelDifferences/" + transactionType + "_" + timedStamp + ".xlsx";
        String ignoreConfigPath = "./output/ignored_columns.txt";   // new config file

//        String jsonFilePath = "./salesEnquires.json"; // or your JSON path
//        String excelFilePath = "./460472 - Sales Enquiries-AC_SE_8_Output.xlsx";
//        String ignoreConfigPath = "./ignored_columns.txt";
//        String diffFilePath = "./Difference.xlsx";

        // Load ignored columns
        loadIgnoredColumns(ignoreConfigPath);

        // Read JSON
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(jsonFilePath));
        JsonNode tables = root.path("dataSet").path("tables");

        // Read Excel
        Workbook workbook = WorkbookFactory.create(new FileInputStream(excelFilePath));
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        DataFormatter dataFormatter = new DataFormatter();

        // Step 1: Build alias mapping from Tables sheet
        Map<String, TableMapping> aliasMap = new HashMap<>();
        Sheet tablesSheet = workbook.getSheet("Tables");
        if (tablesSheet != null) {
            Row header = tablesSheet.getRow(0);
            int sheetNameIdx = -1, virtualNameIdx = -1;

            if (header != null) {
                for (int c = 0; c < header.getLastCellNum(); c++) {
                    String col = safeCell(header.getCell(c), dataFormatter).trim();
                    if ("SheetNames".equalsIgnoreCase(col)) sheetNameIdx = c;
                    if ("VirtualNames".equalsIgnoreCase(col)) virtualNameIdx = c;
                }
            }

            for (int r = 1; r <= tablesSheet.getLastRowNum(); r++) {
                Row row = tablesSheet.getRow(r);
                if (row == null) continue;

                String sheetName = safeCell(row.getCell(sheetNameIdx), dataFormatter).trim();
                String virtualName = safeCell(row.getCell(virtualNameIdx), dataFormatter).trim();
                if (sheetName.isEmpty() && virtualName.isEmpty()) continue;

                TableMapping tm = new TableMapping(sheetName, virtualName);
                if (!sheetName.isEmpty()) aliasMap.put(sheetName.toLowerCase(Locale.ROOT), tm);
                if (!virtualName.isEmpty()) aliasMap.put(virtualName.toLowerCase(Locale.ROOT), tm);
            }
        }

        // Diff workbook
        Workbook diffWorkbook = new XSSFWorkbook();
        Sheet diffSheet = diffWorkbook.createSheet("Differences");
        CellStyle highlightStyle = createHighlightStyle(diffWorkbook);

        // Header
        int diffRowNum = 0;
        Row diffHeader = diffSheet.createRow(diffRowNum++);
        diffHeader.createCell(0).setCellValue("SourceSheet");
        diffHeader.createCell(1).setCellValue("VirtualName");
        diffHeader.createCell(2).setCellValue("LookupName");
        diffHeader.createCell(3).setCellValue("JsonTable");
        diffHeader.createCell(4).setCellValue("RowType");
        diffHeader.createCell(5).setCellValue("Data");

        boolean hasDifferences = false;

        // Step 2: Loop through Excel sheets
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            String actualTabName = sheet.getSheetName();
            if ("Tables".equalsIgnoreCase(actualTabName)) continue;

            TableMapping tm = aliasMap.get(actualTabName.toLowerCase(Locale.ROOT));
            if (tm == null) continue;

            String sheetNameFromTables = tm.sheetName;
            String virtualNameFromTables = tm.virtualName;

            // If VirtualName has "$", use SheetName for JSON lookup
            String lookupName = (virtualNameFromTables != null && virtualNameFromTables.contains("$"))
                    ? sheetNameFromTables
                    : virtualNameFromTables;

            if (lookupName == null || lookupName.trim().isEmpty()) continue;

            // --- Robust JSON lookup ---
            String normalizedLookup = normalizeName(lookupName);

            JsonNode matchingTable = null;
            String jsonTableName = null;
            for (JsonNode table : tables) {
                String tableName = table.path("tableName").asText();
                String normalizedTable = normalizeName(tableName);

                if (normalizedLookup.equals(normalizedTable)) {
                    matchingTable = table;
                    jsonTableName = tableName;
                    break;
                }
            }
            if (matchingTable == null) continue;

            JsonNode jsonRows = matchingTable.path("rows");
            JsonNode columnsNode = matchingTable.path("columns");

            // JSON rows
            Map<String, String> jsonRowMap = new HashMap<>();
            for (int r = 0; r < jsonRows.size(); r++) {
                JsonNode jsonRowArray = jsonRows.get(r);
                Map<String, String> rowMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
                for (int j = 0; j < columnsNode.size(); j++) {
                    String columnName = columnsNode.get(j).path("name").asText();
                    if (IGNORE_COLUMNS.contains(columnName)) continue;
                    JsonNode actualNode = jsonRowArray.get(j);
                    rowMap.put(columnName, formatJsonValue(actualNode));
                }
                jsonRowMap.put(canonicalRowKey(rowMap), prettyPrintRow(rowMap));
            }

            // Excel rows
            Map<String, String> excelRowMap = new HashMap<>();
            Map<Integer, String> columnIndexMap = new LinkedHashMap<>();
            Row headerRow = sheet.getRow(0);
            if (headerRow != null) {
                for (int c = 0; c < headerRow.getLastCellNum(); c++) {
                    String colName = safeCell(headerRow.getCell(c), dataFormatter).trim();
                    if (!colName.isEmpty() && !IGNORE_COLUMNS.contains(colName)) {
                        columnIndexMap.put(c, colName);
                    }
                }
            }

            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                Row excelRow = sheet.getRow(r);
                if (excelRow == null) continue;
                Map<String, String> rowMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
                for (int c : columnIndexMap.keySet()) {
                    rowMap.put(columnIndexMap.get(c), getCellValue(excelRow.getCell(c), evaluator));
                }
                excelRowMap.put(canonicalRowKey(rowMap), prettyPrintRow(rowMap));
            }

            // Differences
            Set<String> missingInExcel = new HashSet<>(jsonRowMap.keySet());
            missingInExcel.removeAll(excelRowMap.keySet());

            Set<String> missingInJson = new HashSet<>(excelRowMap.keySet());
            missingInJson.removeAll(jsonRowMap.keySet());

            for (String key : missingInExcel) {
                hasDifferences = true;
                Row diffRow = diffSheet.createRow(diffRowNum++);
                diffRow.createCell(0).setCellValue(actualTabName);
                diffRow.createCell(1).setCellValue(virtualNameFromTables);
                diffRow.createCell(2).setCellValue(lookupName);
                diffRow.createCell(3).setCellValue(jsonTableName);
                diffRow.createCell(4).setCellValue("MISSING_IN_EXCEL");
                diffRow.createCell(5).setCellValue(jsonRowMap.get(key));
                if (!equalsIgnoreCaseSafe(lookupName, virtualNameFromTables)) {
                    applyRowStyle(diffRow, highlightStyle, 6);
                }
            }

            for (String key : missingInJson) {
                hasDifferences = true;
                Row diffRow = diffSheet.createRow(diffRowNum++);
                diffRow.createCell(0).setCellValue(actualTabName);
                diffRow.createCell(1).setCellValue(virtualNameFromTables);
                diffRow.createCell(2).setCellValue(lookupName);
                diffRow.createCell(3).setCellValue(jsonTableName);
                diffRow.createCell(4).setCellValue("MISSING_IN_JSON");
                diffRow.createCell(5).setCellValue(excelRowMap.get(key));
                if (!equalsIgnoreCaseSafe(lookupName, virtualNameFromTables)) {
                    applyRowStyle(diffRow, highlightStyle, 6);
                }
            }
        }

        // Save differences
        if (hasDifferences) {
            for (int c = 0; c <= 5; c++) diffSheet.autoSizeColumn(c);
            try (FileOutputStream fos = new FileOutputStream(diffFilePath)) {
                diffWorkbook.write(fos);
            }
            diffWorkbook.close();
            System.out.println("✅ Differences written to: " + diffFilePath);
        } else {
            diffWorkbook.close();
            System.out.println("✅ No differences found. No file created.");
        }
    }

    // --- Helpers ---

    private static String normalizeName(String s) {
        if (s == null) return "";
        return s.replace("$", "_").trim().toLowerCase(Locale.ROOT);
    }

    private static boolean equalsIgnoreCaseSafe(String a, String b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.equalsIgnoreCase(b);
    }

    private static void applyRowStyle(Row row, CellStyle style, int cellCount) {
        for (int c = 0; c < cellCount; c++) {
            Cell cell = row.getCell(c);
            if (cell == null) cell = row.createCell(c);
            cell.setCellStyle(style);
        }
    }

    private static String safeCell(Cell cell, DataFormatter formatter) {
        return cell == null ? "" : formatter.formatCellValue(cell);
    }

    private static CellStyle createHighlightStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    private static void loadIgnoredColumns(String path) {
        IGNORE_COLUMNS.clear();
        File file = new File(path);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        IGNORE_COLUMNS.add(line.trim());
                    }
                }
                System.out.println("Loaded ignored columns: " + IGNORE_COLUMNS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            IGNORE_COLUMNS.addAll(Arrays.asList("TransactionNo", "TransactionLineNo", "RowId"));
            System.out.println("⚠️ Using default ignored columns: " + IGNORE_COLUMNS);
        }
    }

    private static String canonicalRowKey(Map<String, String> rowMap) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> e : rowMap.entrySet()) {
            sb.append(e.getKey().toLowerCase(Locale.ROOT))
                    .append("=")
                    .append(e.getValue())
                    .append("||");
        }
        return sb.toString();
    }

    private static String prettyPrintRow(Map<String, String> rowMap) {
        List<String> parts = new ArrayList<>();
        for (Map.Entry<String, String> e : rowMap.entrySet()) {
            parts.add(e.getKey() + "=" + e.getValue());
        }
        return String.join(" | ", parts);
    }

    private static String formatJsonValue(JsonNode node) {
        if (node == null || node.isNull()) return "";
        String text = node.asText();
        if (text != null && text.matches("-?\\d+(\\.\\d+)?")) {
            BigDecimal roundedValue = new BigDecimal(node.asDouble())
                    .setScale(2, RoundingMode.HALF_UP);
            return roundedValue.stripTrailingZeros().toPlainString();
        } else {
            String rawText = text == null ? "" : text.trim();
            String formattedDate = tryFormatDate(rawText);
            return (formattedDate != null) ? formattedDate : rawText;
        }
    }

    private static String getCellValue(Cell cell, FormulaEvaluator evaluator) {
        if (cell == null) return "";
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

        try {
            if (cell.getCellType() == CellType.FORMULA && evaluator != null) {
                CellValue cv = evaluator.evaluate(cell);
                if (cv == null) return "";
                switch (cv.getCellType()) {
                    case STRING: return cv.getStringValue().trim();
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            return DATE_FORMAT.format(cell.getDateCellValue());
                        } else {
                            BigDecimal roundedValue = new BigDecimal(cv.getNumberValue())
                                    .setScale(2, RoundingMode.HALF_UP);
                            return roundedValue.stripTrailingZeros().toPlainString();
                        }
                    case BOOLEAN: return String.valueOf(cv.getBooleanValue());
                    default: return "";
                }
            } else {
                switch (cell.getCellType()) {
                    case STRING: return cell.getStringCellValue().trim();
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            return DATE_FORMAT.format(cell.getDateCellValue());
                        } else {
                            BigDecimal roundedValue = new BigDecimal(cell.getNumericCellValue())
                                    .setScale(2, RoundingMode.HALF_UP);
                            return roundedValue.stripTrailingZeros().toPlainString();
                        }
                    case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
                    case FORMULA: return cell.getCellFormula();
                    default: return "";
                }
            }
        } catch (Exception e) {
            return "";
        }
    }

    private static String tryFormatDate(String input) {
        if (input == null || input.isEmpty()) return null;
        String[] patterns = {
                "yyyy-MM-dd'T'HH:mm:ss'Z'",
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                "yyyy-MM-dd HH:mm:ss",
                "yyyy-MM-dd'T'HH:mm:ss",
                "MM/dd/yyyy HH:mm:ss",
                "MM/dd/yyyy",
                "dd-MM-yyyy HH:mm:ss",
                "dd-MM-yyyy'T'HH:mm:ss'Z'",
                "dd-MM-yyyy'T'HH:mm:ss.SSS'Z'",
                "dd-MM-yyyy'T'HH:mm:ss"
        };
        for (String p : patterns) {
            try {
                SimpleDateFormat inFmt = new SimpleDateFormat(p);
                inFmt.setLenient(false);
                Date d = inFmt.parse(input);
                SimpleDateFormat outFmt = new SimpleDateFormat("dd-MM-yyyy");
                return outFmt.format(d);
            } catch (Exception ignored) {}
        }
        return null;
    }
}





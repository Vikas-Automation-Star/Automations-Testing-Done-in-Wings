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
    public static String diffFilePath;

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
        diffFilePath = "./output/excelDifferences/" + transactionType + "_" + timedStamp + ".xlsx";
        String ignoreConfigPath = "./output/ignored_columns.txt";   // new config file
//        String jsonFilePath = "./salesEnquires.json"; // your JSON path
//        String excelFilePath = "./460472 - Sales Enquiries-AC_SE_8_Output.xlsx"; // your Excel path
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

        // Step 1: Build alias mapping from Tables sheet (and keep list of mappings)
        Map<String, TableMapping> aliasMap = new HashMap<>();
        List<TableMapping> tableMappings = new ArrayList<>();
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
                tableMappings.add(tm);

                if (!sheetName.isEmpty()) aliasMap.put(sheetName.toLowerCase(Locale.ROOT), tm);
                if (!virtualName.isEmpty()) aliasMap.put(virtualName.toLowerCase(Locale.ROOT), tm);
            }
        }

        // Diff workbook
        Workbook diffWorkbook = new XSSFWorkbook();
        Sheet diffSheet = diffWorkbook.createSheet("Differences");
        CellStyle highlightStyle = createHighlightStyle(diffWorkbook);

        // Header for Differences sheet
        int diffRowNum = 0;
        Row diffHeader = diffSheet.createRow(diffRowNum++);
        diffHeader.createCell(0).setCellValue("SourceSheet");
        diffHeader.createCell(1).setCellValue("VirtualName");
        diffHeader.createCell(2).setCellValue("LookupName");
        diffHeader.createCell(3).setCellValue("JsonTable");
        diffHeader.createCell(4).setCellValue("RowType");
        diffHeader.createCell(5).setCellValue("Data");

        boolean hasDifferences = false;
        // Step 2: Loop through Excel sheets and compare using aliasMap / TableMapping logic
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
            if (matchingTable == null) {
                // No JSON table found for this lookup — we'll surface this later in ExcelOnlyTables (mapping-aware)
                continue;
            }

            JsonNode jsonRows = matchingTable.path("rows");
            JsonNode columnsNode = matchingTable.path("columns");

            // JSON rows -> Map canonical key -> pretty string
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

            // Excel rows -> Map canonical key -> pretty string
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

            // Differences: missing in Excel (json exists) and missing in JSON (excel exists)
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
                diffRow.createCell(3).setCellValue(jsonTableName == null ? "" : jsonTableName);
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
                diffRow.createCell(3).setCellValue(jsonTableName == null ? "" : jsonTableName);
                diffRow.createCell(4).setCellValue("MISSING_IN_JSON");
                diffRow.createCell(5).setCellValue(excelRowMap.get(key));
                if (!equalsIgnoreCaseSafe(lookupName, virtualNameFromTables)) {
                    applyRowStyle(diffRow, highlightStyle, 6);
                }
            }
        }

        // -----------------------------
        // Mapping-aware missing table detection
        // -----------------------------

        // Build set of normalized lookup names from Tables mapping
        Set<String> lookupNormalizedSet = new HashSet<>();
        Map<String, TableMapping> lookupNormalizedToMapping = new HashMap<>();
        Set<String> sheetNamesFromTablesNormalized = new HashSet<>();
        for (TableMapping tm : tableMappings) {
            String effectiveLookup = (tm.virtualName != null && tm.virtualName.contains("$")) ? tm.sheetName : tm.virtualName;
            if (effectiveLookup != null && !effectiveLookup.trim().isEmpty()) {
                String normalizedLookup = normalizeName(effectiveLookup);
                lookupNormalizedSet.add(normalizedLookup);
                lookupNormalizedToMapping.put(normalizedLookup, tm);
            }
            if (tm.sheetName != null && !tm.sheetName.trim().isEmpty()) {
                sheetNamesFromTablesNormalized.add(normalizeName(tm.sheetName));
            }
        }

        // Build JSON table normalized set and map to original name
        Set<String> jsonTableNamesNormalized = new HashSet<>();
        Map<String, String> normalizedJsonToOriginal = new HashMap<>();
        for (JsonNode table : tables) {
            String tableName = table.path("tableName").asText();
            String normalizedTable = normalizeName(tableName);
            jsonTableNamesNormalized.add(normalizedTable);
            normalizedJsonToOriginal.put(normalizedTable, tableName);
        }

        // JSON-only tables: json table normalized not referenced by any lookup
        Sheet jsonOnlySheet = diffWorkbook.createSheet("JsonOnlyTables");
        int jsonOnlyRowIdx = 0;
        Row jsonHeader = jsonOnlySheet.createRow(jsonOnlyRowIdx++);
        jsonHeader.createCell(0).setCellValue("JsonTableName");
        jsonHeader.createCell(1).setCellValue("Reason");

        for (String normalizedJson : jsonTableNamesNormalized) {
            if (!lookupNormalizedSet.contains(normalizedJson)) {
                Row row = jsonOnlySheet.createRow(jsonOnlyRowIdx++);
                row.createCell(0).setCellValue(normalizedJsonToOriginal.getOrDefault(normalizedJson, normalizedJson));
                row.createCell(1).setCellValue("Not referenced by any LookupName in Tables sheet");
            }
        }
        jsonOnlySheet.autoSizeColumn(0);
        jsonOnlySheet.autoSizeColumn(1);

        // Excel-only tables: (a) workbook sheets not present in Tables sheet; (b) mapped lookups with no JSON table
        /*** Sheet excelOnlySheet = diffWorkbook.createSheet("ExcelOnlyTables");
         int excelOnlyRowIdx = 0;
         Row excelHeader = excelOnlySheet.createRow(excelOnlyRowIdx++);
         excelHeader.createCell(0).setCellValue("Type"); // UNMAPPED_SHEET or MAPPED_BUT_JSON_MISSING
         excelHeader.createCell(1).setCellValue("SheetName");
         excelHeader.createCell(2).setCellValue("VirtualName");
         excelHeader.createCell(3).setCellValue("LookupName");
         excelHeader.createCell(4).setCellValue("JsonTableFound");

         // (a) Unmapped sheets present in workbook but not listed in Tables sheet (SheetNames column)
         for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
         String sheetName = workbook.getSheetAt(i).getSheetName();
         if ("Tables".equalsIgnoreCase(sheetName)) continue;
         String normalized = normalizeName(sheetName);
         if (!sheetNamesFromTablesNormalized.contains(normalized)) {
         Row row = excelOnlySheet.createRow(excelOnlyRowIdx++);
         row.createCell(0).setCellValue("UNMAPPED_SHEET");
         row.createCell(1).setCellValue(sheetName);
         row.createCell(2).setCellValue("");
         row.createCell(3).setCellValue("");
         row.createCell(4).setCellValue("N/A");
         }
         }

         // (b) Mappings (from Tables sheet) whose lookupName doesn't have a JSON table
         for (String normLookup : lookupNormalizedSet) {
         boolean jsonExists = jsonTableNamesNormalized.contains(normLookup);
         if (!jsonExists) {
         TableMapping tm = lookupNormalizedToMapping.get(normLookup);
         String effectiveLookup = (tm.virtualName != null && tm.virtualName.contains("$")) ? tm.sheetName : tm.virtualName;
         Row row = excelOnlySheet.createRow(excelOnlyRowIdx++);
         row.createCell(0).setCellValue("MAPPED_BUT_JSON_MISSING");
         row.createCell(1).setCellValue(tm.sheetName == null ? "" : tm.sheetName);
         row.createCell(2).setCellValue(tm.virtualName == null ? "" : tm.virtualName);
         row.createCell(3).setCellValue(effectiveLookup == null ? "" : effectiveLookup);
         row.createCell(4).setCellValue("No");
         }
         }

         excelOnlySheet.autoSizeColumn(0);
         excelOnlySheet.autoSizeColumn(1);
         excelOnlySheet.autoSizeColumn(2);
         excelOnlySheet.autoSizeColumn(3);
         excelOnlySheet.autoSizeColumn(4);
         **/

        // Save differences
        boolean jsonOnlyHasRows = jsonOnlySheet.getLastRowNum() > 0;
        //boolean excelOnlyHasRows = excelOnlySheet.getLastRowNum() > 0;

        //if (hasDifferences || jsonOnlyHasRows || excelOnlyHasRows) {
        if (hasDifferences || jsonOnlyHasRows) {
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
            sb.append(e.getKey().toLowerCase(Locale.ROOT)).append("=").append(e.getValue()).append("||");
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


    public static void ExcelColumnDifference(String filePath) throws IOException {
//        String filePath = "./Difference.xlsx";

        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet diffSheet = workbook.getSheet("Differences");

        if (diffSheet == null) {
            System.out.println("Differences sheet not found!");
            return;
        }

        // Remove existing ColumnDifferences sheet if present
        Sheet colDiffSheet = workbook.getSheet("ColumnDifferences");
        if (colDiffSheet != null) {
            int idx = workbook.getSheetIndex(colDiffSheet);
            workbook.removeSheetAt(idx);
        }
        colDiffSheet = workbook.createSheet("ColumnDifferences");

        // Header for new sheet
        Row header = colDiffSheet.createRow(0);
        header.createCell(0).setCellValue("LookUpName");
        header.createCell(1).setCellValue("JsonTable");
        header.createCell(2).setCellValue("RowReference_MISSING_IN_JSON");
        header.createCell(3).setCellValue("RowReference_MISSING_IN_EXCEL");
        header.createCell(4).setCellValue("Differences (MISSING_IN_JSON -> MISSING_IN_EXCEL)");

        // Identify column indexes in Differences sheet
        Row firstRow = diffSheet.getRow(0);
        int lookupCol = -1, jsonTableCol = -1, rowTypeCol = -1, dataCol = -1;

        for (Cell cell : firstRow) {
            String val = cell.getStringCellValue().trim();
            if (val.equalsIgnoreCase("LookUpName")) lookupCol = cell.getColumnIndex();
            if (val.equalsIgnoreCase("JsonTable")) jsonTableCol = cell.getColumnIndex();
            if (val.equalsIgnoreCase("RowType")) rowTypeCol = cell.getColumnIndex();
            if (val.equalsIgnoreCase("Data")) dataCol = cell.getColumnIndex();
        }

        if (lookupCol == -1 || jsonTableCol == -1 || rowTypeCol == -1 || dataCol == -1) {
            System.out.println("Required columns not found!");
            return;
        }

        class RowData {
            String data;
            int rowNum;
            RowData(String d, int r) { data = d; rowNum = r; }
        }

        // Group rows by (LookUpName, JsonTable)
        Map<String, List<RowData>> missingInJson = new HashMap<>();
        Map<String, List<RowData>> missingInExcel = new HashMap<>();

        for (int i = 1; i <= diffSheet.getLastRowNum(); i++) {
            Row row = diffSheet.getRow(i);
            if (row == null) continue;

            String lookupName = row.getCell(lookupCol).getStringCellValue();
            String jsonTable = row.getCell(jsonTableCol).getStringCellValue();
            String rowType = row.getCell(rowTypeCol).getStringCellValue();
            String data = row.getCell(dataCol).getStringCellValue();

            String key = lookupName + "|" + jsonTable;

            if ("MISSING_IN_JSON".equalsIgnoreCase(rowType)) {
                missingInJson.computeIfAbsent(key, k -> new ArrayList<>()).add(new RowData(data, i + 1));
            } else if ("MISSING_IN_EXCEL".equalsIgnoreCase(rowType)) {
                missingInExcel.computeIfAbsent(key, k -> new ArrayList<>()).add(new RowData(data, i + 1));
            }
        }

        CellStyle wrapStyle = workbook.createCellStyle();
        wrapStyle.setWrapText(true);

        int rowNum = 1;

        // Compare each group separately
        for (String key : missingInJson.keySet()) {
            List<RowData> jsonRows = new ArrayList<>(missingInJson.get(key));
            List<RowData> excelRows = new ArrayList<>(missingInExcel.getOrDefault(key, Collections.emptyList()));

            while (!jsonRows.isEmpty() && !excelRows.isEmpty()) {
                // Find best pair with minimal differences
                int bestJsonIdx = -1, bestExcelIdx = -1, minDiff = Integer.MAX_VALUE;
                List<String> bestDiffList = null;

                for (int i = 0; i < jsonRows.size(); i++) {
                    for (int j = 0; j < excelRows.size(); j++) {
                        List<String> diffs = computeDifferences(jsonRows.get(i).data, excelRows.get(j).data);
                        if (diffs.size() < minDiff) {
                            minDiff = diffs.size();
                            bestJsonIdx = i;
                            bestExcelIdx = j;
                            bestDiffList = diffs;
                        }
                    }
                }

                if (bestJsonIdx != -1 && bestExcelIdx != -1 && bestDiffList != null) {
                    RowData jsonRow = jsonRows.remove(bestJsonIdx);
                    RowData excelRow = excelRows.remove(bestExcelIdx);

                    Row r = colDiffSheet.createRow(rowNum++);
                    String[] parts = key.split("\\|");

                    r.createCell(0).setCellValue(parts[0]); // LookUpName
                    r.createCell(1).setCellValue(parts[1]); // JsonTable
                    r.createCell(2).setCellValue(jsonRow.rowNum);
                    r.createCell(3).setCellValue(excelRow.rowNum);

                    Cell diffCell = r.createCell(4);
                    String joined = String.join("\n", bestDiffList);
                    diffCell.setCellValue(joined);
                    diffCell.setCellStyle(wrapStyle);

                    int lineCount = joined.split("\n").length;
                    r.setHeightInPoints(lineCount * diffSheet.getDefaultRowHeightInPoints());
                } else {
                    break;
                }
            }

            // Handle unmatched json rows
            for (RowData jsonRow : jsonRows) {
                Row r = colDiffSheet.createRow(rowNum++);
                String[] parts = key.split("\\|");

                r.createCell(0).setCellValue(parts[0]);
                r.createCell(1).setCellValue(parts[1]);
                r.createCell(2).setCellValue(jsonRow.rowNum);
                r.createCell(3).setCellValue("");
                r.createCell(4).setCellValue(jsonRow.data + " -> ");
            }

            // Handle unmatched excel rows
            for (RowData excelRow : excelRows) {
                Row r = colDiffSheet.createRow(rowNum++);
                String[] parts = key.split("\\|");

                r.createCell(0).setCellValue(parts[0]);
                r.createCell(1).setCellValue(parts[1]);
                r.createCell(2).setCellValue("");
                r.createCell(3).setCellValue(excelRow.rowNum);
                r.createCell(4).setCellValue(" -> " + excelRow.data);
            }
        }

        // Auto-size columns
        for (int i = 0; i <= 4; i++) {
            colDiffSheet.autoSizeColumn(i);
        }

        fis.close();
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
        workbook.close();

        System.out.println("Optimized matching with raw values completed.");
    }

    private static List<String> computeDifferences(String jsonData, String excelData) {
        String[] jsonVals = jsonData.split("\\|");
        String[] excelVals = excelData.split("\\|");

        List<String> differences = new ArrayList<>();
        int max = Math.max(jsonVals.length, excelVals.length);

        for (int i = 0; i < max; i++) {
            String jVal = (i < jsonVals.length) ? jsonVals[i].trim() : "";
            String eVal = (i < excelVals.length) ? excelVals[i].trim() : "";
            if (!jVal.equals(eVal)) {
                differences.add(jVal + " -> " + eVal);
            }
        }
        return differences;
    }

}





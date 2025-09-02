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
    public static void JsonExcelComparator(String jsonFilePath,String excelFilePath,String transactionType) throws Exception {

        String timedStamp=Time.timeStamp();
        String diffFilePath = "./output/excelDifferences/" + transactionType + "_" + timedStamp + ".xlsx";
        String ignoreConfigPath = "./ignored_columns.txt";   // new config file
//        String diffFilePath = "./output/excelDifferences/" + transactionType + "_"+ ".xlsx";


        // Load ignored columns from config file
        loadIgnoredColumns(ignoreConfigPath);

        // Read JSON
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(jsonFilePath));
        JsonNode tables = root.path("dataSet").path("tables");

        // Read Excel
        Workbook workbook = WorkbookFactory.create(new FileInputStream(excelFilePath));

        // Step 1: Build mapping from Tables sheet
        Map<String, String> sheetToVirtualMap = new HashMap<>();
        Sheet tablesSheet = workbook.getSheet("Tables");
        if (tablesSheet != null) {
            Row header = tablesSheet.getRow(0);
            int sheetNameIdx = -1, virtualNameIdx = -1;
            for (int c = 0; c < header.getLastCellNum(); c++) {
                String col = header.getCell(c).getStringCellValue();
                if (col.equalsIgnoreCase("SheetNames")) sheetNameIdx = c;
                if (col.equalsIgnoreCase("VirtualNames")) virtualNameIdx = c;
            }

            for (int r = 1; r <= tablesSheet.getLastRowNum(); r++) {
                Row row = tablesSheet.getRow(r);
                if (row == null) continue;
                String sheetName = row.getCell(sheetNameIdx).getStringCellValue();
                String virtualName = row.getCell(virtualNameIdx).getStringCellValue();
                sheetToVirtualMap.put(sheetName, virtualName);
            }
        }

        // Diff workbook
        Workbook diffWorkbook = new XSSFWorkbook();
        Sheet diffSheet = diffWorkbook.createSheet("Differences");

        // Header
        int diffRowNum = 0;
        Row diffHeader = diffSheet.createRow(diffRowNum++);
        diffHeader.createCell(0).setCellValue("SourceSheet");
        diffHeader.createCell(1).setCellValue("JsonTable");
        diffHeader.createCell(2).setCellValue("RowType");
        diffHeader.createCell(3).setCellValue("Data");

        boolean hasDifferences = false;

        // Step 2: Loop through Excel sheets
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            String sheetName = sheet.getSheetName();
            if (sheetName.equalsIgnoreCase("Tables")) continue; // skip mapping sheet

            // Lookup virtualName for this sheet
            String virtualName = sheetToVirtualMap.get(sheetName);
            if (virtualName == null) continue;

            // Find matching JSON table by tableName
            JsonNode matchingTable = null;
            String jsonTableName = null;
            for (JsonNode table : tables) {
                String tableName = table.path("tableName").asText();
                if (virtualName.equalsIgnoreCase(tableName)) {
                    matchingTable = table;
                    jsonTableName = tableName;
                    break;
                }
            }
            if (matchingTable == null) continue;

            JsonNode jsonRows = matchingTable.path("rows");
            JsonNode columnsNode = matchingTable.path("columns");

            // Build JSON row set
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
                String key = canonicalRowKey(rowMap);
                jsonRowMap.put(key, prettyPrintRow(rowMap));
            }

            // Build Excel row set
            Map<String, String> excelRowMap = new HashMap<>();
            Map<Integer, String> columnIndexMap = new LinkedHashMap<>();
            if (sheet.getRow(0) != null) {
                Row headerRow = sheet.getRow(0);
                for (int c = 0; c < headerRow.getLastCellNum(); c++) {
                    String colName = headerRow.getCell(c).getStringCellValue();
                    if (IGNORE_COLUMNS.contains(colName)) continue;
                    columnIndexMap.put(c, colName);
                }
            }

            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                Row excelRow = sheet.getRow(r);
                if (excelRow == null) continue;
                Map<String, String> rowMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
                for (int c : columnIndexMap.keySet()) {
                    Cell cell = excelRow.getCell(c);
                    rowMap.put(columnIndexMap.get(c), getCellValue(cell));
                }
                String key = canonicalRowKey(rowMap);
                excelRowMap.put(key, prettyPrintRow(rowMap));
            }

            // Differences
            Set<String> missingInExcel = new HashSet<>(jsonRowMap.keySet());
            missingInExcel.removeAll(excelRowMap.keySet());

            Set<String> missingInJson = new HashSet<>(excelRowMap.keySet());
            missingInJson.removeAll(jsonRowMap.keySet());

            for (String key : missingInExcel) {
                hasDifferences = true;
                Row diffRow = diffSheet.createRow(diffRowNum++);
                diffRow.createCell(0).setCellValue(sheetName);
                diffRow.createCell(1).setCellValue(jsonTableName);
                diffRow.createCell(2).setCellValue("MISSING_IN_EXCEL");
                diffRow.createCell(3).setCellValue(jsonRowMap.get(key));
            }

            for (String key : missingInJson) {
                hasDifferences = true;
                Row diffRow = diffSheet.createRow(diffRowNum++);
                diffRow.createCell(0).setCellValue(sheetName);
                diffRow.createCell(1).setCellValue(jsonTableName);
                diffRow.createCell(2).setCellValue("MISSING_IN_JSON");
                diffRow.createCell(3).setCellValue(excelRowMap.get(key));
            }
        }

        // Save differences only if any
        if (hasDifferences) {
            try (FileOutputStream fos = new FileOutputStream(diffFilePath)) {
                diffWorkbook.write(fos);
            }
            diffWorkbook.close();
            System.out.println("Comparison completed. Differences written to: " + diffFilePath);
        } else {
            diffWorkbook.close();
            System.out.println("✅ No differences found. No file created.");
        }
    }

    // Load ignored columns from config file
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
                System.out.println("Loaded ignored columns from config: " + IGNORE_COLUMNS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Default fallback
            IGNORE_COLUMNS.addAll(Arrays.asList("TransactionNo", "TransactionLineNo", "RowId"));
            System.out.println("⚠️ Config file not found. Using default ignored columns: " + IGNORE_COLUMNS);
        }
    }

    // Build a stable canonical row key (ignores column order)
    private static String canonicalRowKey(Map<String, String> rowMap) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> e : rowMap.entrySet()) {
            sb.append(e.getKey().toLowerCase()).append("=").append(e.getValue()).append("||");
        }
        return sb.toString();
    }

    // Pretty print row for Excel report
    private static String prettyPrintRow(Map<String, String> rowMap) {
        List<String> parts = new ArrayList<>();
        for (Map.Entry<String, String> e : rowMap.entrySet()) {
            parts.add(e.getKey() + "=" + e.getValue());
        }
        return String.join(" | ", parts);
    }

    private static String formatJsonValue(JsonNode node) {
        if (node == null || node.isNull()) return "";
        if (node.asText().matches("-?\\d+(\\.\\d+)?")) {
            BigDecimal roundedValue = new BigDecimal(node.asDouble())
                    .setScale(2, RoundingMode.HALF_UP);
            return roundedValue.stripTrailingZeros().toPlainString();
        } else {
            String rawText = node.asText().trim();
            String formattedDate = tryFormatDate(rawText);
            return (formattedDate != null) ? formattedDate : rawText;
        }
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return DATE_FORMAT.format(cell.getDateCellValue());
                } else {
                    BigDecimal roundedValue = new BigDecimal(cell.getNumericCellValue())
                            .setScale(2, RoundingMode.HALF_UP);
                    return roundedValue.stripTrailingZeros().toPlainString();
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    private static String tryFormatDate(String input) {
        String[] datePatterns = {
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
        for (String pattern : datePatterns) {
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat(pattern);
                inputFormat.setLenient(false);
                Date date = inputFormat.parse(input);
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
                return outputFormat.format(date);
            } catch (Exception ignored) {}
        }
        return null;
    }
}





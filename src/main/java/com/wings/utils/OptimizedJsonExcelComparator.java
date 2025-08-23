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
    public static void JsonExcelComparator(String jsonFilePath,String excelFilePath,String transactionType) throws Exception {
//        String jsonFilePath = "./journalEntries.txt";
//        String excelFilePath = "./464159 - Journal Entries-AC_JE_3_Output.xls";
//        String diffFilePath = "./Difference.xlsx";

        String timeStamp=Time.timeStamp();
        String diffFilePath = "./output/excelDifferences/" + transactionType + "_" + timeStamp + ".xlsx";

        // Read and parse JSON
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(jsonFilePath));
        JsonNode tables = root.path("dataSet").path("tables");

        // Read Excel
        Workbook workbook = WorkbookFactory.create(new FileInputStream(excelFilePath));

        // Create ONE workbook for differences
        Workbook diffWorkbook = new XSSFWorkbook();
        Sheet diffSheet = diffWorkbook.createSheet("Differences");

        // Header row
        int diffRowNum = 0;
        Row diffHeader = diffSheet.createRow(diffRowNum++);
        diffHeader.createCell(0).setCellValue("SourceSheet");
        diffHeader.createCell(1).setCellValue("Row");
        diffHeader.createCell(2).setCellValue("Column");
        diffHeader.createCell(3).setCellValue("Expected");
        diffHeader.createCell(4).setCellValue("Actual");

        boolean hasDifferences = false;  // 🔹 Track if any differences found

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            String sheetName = sheet.getSheetName();

            JsonNode matchingTable = null;
            for (JsonNode table : tables) {
                if (sheetName.equalsIgnoreCase(table.path("tableName").asText())) {
                    matchingTable = table;
                    break;
                }
            }

            if (matchingTable == null) continue;

            JsonNode jsonRows = matchingTable.path("rows");
            JsonNode columnsNode = matchingTable.path("columns");

            Map<String, Integer> columnMap = new HashMap<>();
            if (sheet.getRow(0) != null) {
                Row headerRow = sheet.getRow(0);
                for (int c = 0; c < headerRow.getLastCellNum(); c++) {
                    String colName = headerRow.getCell(c).getStringCellValue();
                    columnMap.put(colName, c);
                }
            }

            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                Row excelRow = sheet.getRow(r);
                if (excelRow == null || (r - 1) >= jsonRows.size()) continue;

                JsonNode jsonRowArray = jsonRows.get(r - 1);

                for (int j = 0; j < columnsNode.size(); j++) {
                    String columnName = columnsNode.get(j).path("name").asText();
                    JsonNode actualNode = jsonRowArray.get(j);

                    String actualValue = "";
                    if (actualNode != null && !actualNode.isNull()) {
                        if (actualNode.asText().matches("-?\\d+(\\.\\d+)?")) {
                            BigDecimal roundedValue = new BigDecimal(actualNode.asDouble())
                                    .setScale(2, RoundingMode.HALF_UP);
                            actualValue = roundedValue.stripTrailingZeros().toPlainString();
                        } else {
                            String rawText = actualNode.asText().trim();
                            String formattedDate = tryFormatDate(rawText);
                            actualValue = (formattedDate != null) ? formattedDate : rawText;
                        }
                    }

                    if (columnMap.containsKey(columnName)) {
                        Cell excelCell = excelRow.getCell(columnMap.get(columnName));
                        String expectedValue = getCellValue(excelCell);

                        if (!expectedValue.equals(actualValue)) {
                            hasDifferences = true; // 🔹 Found at least one diff
                            Row diffRow = diffSheet.createRow(diffRowNum++);
                            diffRow.createCell(0).setCellValue(sheetName);
                            diffRow.createCell(1).setCellValue(r);
                            diffRow.createCell(2).setCellValue(columnName);
                            diffRow.createCell(3).setCellValue(expectedValue);
                            diffRow.createCell(4).setCellValue(actualValue);
                        }
                    }
                }
            }
        }

        if (hasDifferences) {
            try (FileOutputStream fos = new FileOutputStream(diffFilePath)) {
                diffWorkbook.write(fos);
            }
            diffWorkbook.close();
            System.out.println("Comparison completed. Differences😴 written to: " + diffFilePath);
        } else {
            diffWorkbook.close(); // just close without writing
            System.out.println("✅ No differences found. No file created.");
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
        return null; // Not a date
    }

    public static void main(String[] args) throws Exception {
        JsonExcelComparator("./","./","");
    }

}


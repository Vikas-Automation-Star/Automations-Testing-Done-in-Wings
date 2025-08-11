package com.wings.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

import java.io.*;
import java.io.File;
import java.util.*;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.text.SimpleDateFormat;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class AdvancedJsonExcelComparatorNew {
    public static void JsonExcelComparator(String jsonFilePath,String excelFilePath,String transactionType) throws IOException {
//        String jsonFilePath = "./SalesReturns.txt";
//        String excelFilePath = "./480463 - Sales Returns-AC_Output.xlsx";
//        String diffFilePath = "./Difference.xlsx";

        String timeStamp=Time.timeStamp();
        String diffFilePath = "./output/excelDifferences/" + transactionType + "_" + timeStamp + ".xlsx";

        // Read and parse JSON
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(jsonFilePath));
        JsonNode tables = root.path("dataSet").path("tables");

        // Read Excel
        Workbook workbook = WorkbookFactory.create(new FileInputStream(excelFilePath));
        Workbook diffWorkbook=null;

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
            if(sheet.getRow(0)!=null){
                org.apache.poi.ss.usermodel.Row headerRow = sheet.getRow(0);
                for (int c = 0; c < headerRow.getLastCellNum(); c++) {
                    String colName = headerRow.getCell(c).getStringCellValue();
                    System.out.println("SheetName: "+sheetName+" | Column: "+colName);
                    columnMap.put(colName, c);
                }
            }
            // Create diff sheet
            Sheet diffSheet = null;
            int diffRowNum = 0;

            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                org.apache.poi.ss.usermodel.Row excelRow = sheet.getRow(r);
                if (excelRow == null || (r - 1) >= jsonRows.size()) continue;

                JsonNode jsonRowArray = jsonRows.get(r - 1);

                for (int j = 0; j < columnsNode.size(); j++) {
                    String columnName = columnsNode.get(j).path("name").asText();
                    JsonNode actualNode = jsonRowArray.get(j);
                    String actualValue = "";
                    if (actualNode != null && !actualNode.isNull()) {

                        if (actualNode.asText().matches("-?\\d+(\\.\\d+)?")) {
                            BigDecimal roundedValue = new BigDecimal(actualNode.asDouble()).setScale(2, RoundingMode.HALF_UP);
                            actualValue = roundedValue.stripTrailingZeros().toPlainString();
                        } else {
                            String rawText = actualNode.asText().trim();

                            // Try to parse date strings and format to yyyy-MM-dd
                            String formattedDate = tryFormatDate(rawText);
                            if (formattedDate != null) {
                                actualValue = formattedDate;
                            } else {
                                actualValue = rawText;
                            }
                        }
                    }

                    if (columnMap.containsKey(columnName)) {
                        Cell excelCell = excelRow.getCell(columnMap.get(columnName));
                        String expectedValue = getCellValue(excelCell);
                        //String expectedValue = excelCell.getStringCellValue();
                        //System.out.println("Exp value: "+expectedValue+" | Actual Value - "+actualValue);
                        //System.out.println(jsonRows.size());

                        if (!expectedValue.equals(actualValue)) {
                            if (diffSheet == null) {
                                diffWorkbook= new XSSFWorkbook();

                                diffSheet = diffWorkbook.createSheet(sheetName);
                                Row diffHeader = diffSheet.createRow(diffRowNum++);
                                diffHeader.createCell(0).setCellValue("Row");
                                diffHeader.createCell(1).setCellValue("Column");
                                diffHeader.createCell(2).setCellValue("Expected");
                                diffHeader.createCell(3).setCellValue("Actual");
                            }
                            Row diffRow = diffSheet.createRow(diffRowNum++);
                            diffRow.createCell(0).setCellValue(r);
                            diffRow.createCell(1).setCellValue(columnName);
                            diffRow.createCell(2).setCellValue(expectedValue);
                            diffRow.createCell(3).setCellValue(actualValue);
                        }
                    }
                }
            }
        }
        if(diffWorkbook!=null){
            System.out.println("Manoj");
            // Save differences
            try (FileOutputStream fos = new FileOutputStream(diffFilePath)) {
                diffWorkbook.write(fos);
                System.out.println("Comparison completed. Differences written to:😥 " + diffFilePath);
            }
        }else {
            System.out.println("No differences generated 😃 All sheets and it's values matched Perfectly🍟🍗");
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
                    BigDecimal roundedValue = new BigDecimal(cell.getNumericCellValue()).setScale(2, RoundingMode.HALF_UP);
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


    public static void main(String[] args) throws IOException {
        JsonExcelComparator("./output/SRWIR.txt","./src/main/resources/menuItems/Sales/Transactions/480464 - Sales Return with Invoice Reference-AC_Output.xlsx","SalesReturnsWIR");
    }

}

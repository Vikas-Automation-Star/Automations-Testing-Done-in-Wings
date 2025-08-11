package com.wings.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class JsonExcelValidator {

    public static void main(String[] args) throws Exception {
        String jsonFilePath = "./output/SI.txt";
        String excelFilePath = "./src/main/resources/menuItems/Sales/Transactions/480460_SI 10_Output.xlsx";
//        String outputDiffPath = "./output/difference.xls";
        String timestamp = Time.timeStamp();
        String outputDiffPath = "output/" + "SalesInvoice" + "_" + timestamp + ".xls";

        // Load JSON from text file
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(jsonFilePath));

        // Extract dataset tables
        JsonNode tables = root.path("dataSet").path("tables");

        // Load Excel file
        FileInputStream excelStream = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(excelStream);

        // Create workbook for differences
        Workbook diffWorkbook = new HSSFWorkbook();

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

            Sheet diffSheet = diffWorkbook.createSheet(sheetName);
            int diffRowNum = 0;
            Row diffHeader = diffSheet.createRow(diffRowNum++);
            diffHeader.createCell(0).setCellValue("Row");
            diffHeader.createCell(1).setCellValue("Column");
            diffHeader.createCell(2).setCellValue("Expected");
            diffHeader.createCell(3).setCellValue("Actual");

            JsonNode jsonRows = matchingTable.path("rows");
            Row header = sheet.getRow(0);

            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                Row excelRow = sheet.getRow(r);
                if (excelRow == null || r - 1 >= jsonRows.size()) continue;
                JsonNode jsonRow = jsonRows.get(r - 1);

                for (int c = 0; c < header.getLastCellNum(); c++) {
                    String columnName = header.getCell(c).getStringCellValue();
                    String expected = getCellValue(excelRow.getCell(c));
                    String actual = jsonRow.get(c).asText();

                    if (!expected.equals(actual)) {
                        Row diffRow = diffSheet.createRow(diffRowNum++);
                        diffRow.createCell(0).setCellValue(r);
                        diffRow.createCell(1).setCellValue(columnName);
                        diffRow.createCell(2).setCellValue(expected);
                        diffRow.createCell(3).setCellValue(actual);
                    }
                }
            }
        }

        // Save differences
        FileOutputStream out = new FileOutputStream(outputDiffPath);
        diffWorkbook.write(out);
        out.close();
        workbook.close();
        System.out.println("Comparison complete. Differences written to " + outputDiffPath);
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
}

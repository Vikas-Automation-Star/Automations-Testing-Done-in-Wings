package com.wings.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.*;
import java.sql.DriverManager;
import java.util.*;

public class SQLToExcelComparator {

    private static final String SQL_URL = "jdbc:sqlserver://10.10.10.90:1433;databaseName=24D Books Automation;encrypt=true;trustServerCertificate=true";
    private static final String SQL_USER = "dbuser1";
    private static final String SQL_PASSWORD = "dbuser1";

    private static final String EXPECTED_EXCEL_PATH = "ExpectedData.xlsx";
    private static final String ACTUAL_EXCEL_PATH = "ActualData.xlsx";
    private static final String DIFFERENCE_EXCEL_PATH = "Differences.xlsx";

    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection(SQL_URL, SQL_USER, SQL_PASSWORD);

        // Define custom SQL queries and sheet names
        Map<String, String> queryMap = new LinkedHashMap<>();
        queryMap.put("AB2_Sales_ChargesAndDeductions", "select * from dbo.AB2_Sales_ChargesAndDeductions;");

        exportQueriesToExcel(conn, queryMap, ACTUAL_EXCEL_PATH);
//        compareExcels(EXPECTED_EXCEL_PATH, ACTUAL_EXCEL_PATH, DIFFERENCE_EXCEL_PATH);
        System.out.println("executed Successfully");
        conn.close();
//        System.out.println("Done. Differences written to " + DIFFERENCE_EXCEL_PATH);
    }


    public static void exportQueriesToExcel(Connection conn, Map<String, String> queryMap, String filePath) throws Exception {
        Workbook workbook = new XSSFWorkbook();

        for (Map.Entry<String, String> entry : queryMap.entrySet()) {
            String sheetName = entry.getKey();
            String query = entry.getValue();

            Sheet sheet = workbook.createSheet(sheetName);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();

            // Header Row
            Row header = sheet.createRow(0);
            for (int i = 1; i <= colCount; i++) {
                header.createCell(i - 1).setCellValue(rsmd.getColumnLabel(i));
            }

            // Data Rows
            int rowNum = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 1; i <= colCount; i++) {
                    row.createCell(i - 1).setCellValue(String.valueOf(rs.getObject(i)));
                }
            }

            stmt.close();
        }

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        }
        workbook.close();
    }

    // Compare actual and expected Excel files
    public static void compareExcels(String expectedPath, String actualPath, String diffPath) throws Exception {
        Workbook expectedWb = WorkbookFactory.create(new FileInputStream(expectedPath));
        Workbook actualWb = WorkbookFactory.create(new FileInputStream(actualPath));
        Workbook diffWb = new XSSFWorkbook();
        Sheet diffSheet = diffWb.createSheet("Differences");

        int diffRowNum = 0;
        Row header = diffSheet.createRow(diffRowNum++);
        header.createCell(0).setCellValue("Sheet");
        header.createCell(1).setCellValue("Row");
        header.createCell(2).setCellValue("Column");
        header.createCell(3).setCellValue("Expected");
        header.createCell(4).setCellValue("Actual");

        for (int i = 0; i < expectedWb.getNumberOfSheets(); i++) {
            Sheet expectedSheet = expectedWb.getSheetAt(i);
            String sheetName = expectedSheet.getSheetName();
            Sheet actualSheet = actualWb.getSheet(sheetName);
            if (actualSheet == null) continue;

            int lastRow = Math.max(expectedSheet.getLastRowNum(), actualSheet.getLastRowNum());
            for (int r = 0; r <= lastRow; r++) {
                Row expRow = expectedSheet.getRow(r);
                Row actRow = actualSheet.getRow(r);
                int maxCol = 0;
                if (expRow != null) maxCol = expRow.getLastCellNum();
                if (actRow != null) maxCol = Math.max(maxCol, actRow.getLastCellNum());

                for (int c = 0; c < maxCol; c++) {
                    String expectedVal = getCellValue(expRow, c);
                    String actualVal = getCellValue(actRow, c);
                    if (!Objects.equals(expectedVal, actualVal)) {
                        Row diffRow = diffSheet.createRow(diffRowNum++);
                        diffRow.createCell(0).setCellValue(sheetName);
                        diffRow.createCell(1).setCellValue(r + 1);
                        diffRow.createCell(2).setCellValue(c + 1);
                        diffRow.createCell(3).setCellValue(expectedVal);
                        diffRow.createCell(4).setCellValue(actualVal);
                    }
                }
            }
        }

        try (FileOutputStream fos = new FileOutputStream(diffPath)) {
            diffWb.write(fos);
        }

        expectedWb.close();
        actualWb.close();
        diffWb.close();
    }

    private static String getCellValue(Row row, int colIndex) {
        if (row == null) return "";
        Cell cell = row.getCell(colIndex);
        if (cell == null) return "";
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

}



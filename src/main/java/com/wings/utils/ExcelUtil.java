package com.wings.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtil {
    public boolean  excelComparator(String file1Path,String file2Path,String transactionName) {
        boolean diference=true;
        String timestamp = Time.timeStamp();
        String diffOutputPath = "output/" + transactionName + "_" + timestamp + ".xlsx";
        try (
                FileInputStream fis1 = new FileInputStream(file1Path);
                FileInputStream fis2 = new FileInputStream(file2Path);
                Workbook wb1 = new XSSFWorkbook(fis1);
                Workbook wb2 = new XSSFWorkbook(fis2);
                Workbook diffWb = new XSSFWorkbook()
        ) {
            Sheet diffSheet = diffWb.createSheet("Differences");
            createHeaderRow(diffSheet);
            int diffRowNum = 1;
            int totalSheets = Math.max(wb1.getNumberOfSheets(), wb2.getNumberOfSheets());
            for (int s = 0; s < totalSheets; s++) {
                Sheet sheet1 = s < wb1.getNumberOfSheets() ? wb1.getSheetAt(s) : null;
                Sheet sheet2 = s < wb2.getNumberOfSheets() ? wb2.getSheetAt(s) : null;

                String sheetName = (sheet1 != null) ? sheet1.getSheetName() : (sheet2 != null) ? sheet2.getSheetName() : "Sheet" + (s + 1);

                int maxRows = Math.max(sheet1 != null ? sheet1.getLastRowNum() : 0, sheet2 != null ? sheet2.getLastRowNum() : 0);

                for (int i = 0; i <= maxRows; i++) {
                    Row row1 = sheet1 != null ? sheet1.getRow(i) : null;
                    Row row2 = sheet2 != null ? sheet2.getRow(i) : null;

                    int maxCols = Math.max(
                            row1 != null ? row1.getLastCellNum() : 0,
                            row2 != null ? row2.getLastCellNum() : 0
                    );

                    for (int j = 0; j < maxCols; j++) {
                        String val1 = getCellValue(row1, j);
                        String val2 = getCellValue(row2, j);

                        if (!val1.equals(val2)) {
                            Row diffRow = diffSheet.createRow(diffRowNum++);
                            diffRow.createCell(0).setCellValue(sheetName);
                            diffRow.createCell(1).setCellValue(i + 1); // Row (1-based)
                            diffRow.createCell(2).setCellValue(j + 1); // Column index (1-based)
                            diffRow.createCell(3).setCellValue(getCellRef(i, j));
                            diffRow.createCell(4).setCellValue(val1);
                            diffRow.createCell(5).setCellValue(val2);
                            diference=false;
                        }
                    }
                }
            }

            try (FileOutputStream fos = new FileOutputStream(diffOutputPath)) {
                diffWb.write(fos);
            }
            System.out.println("✅ Comparison completed. Differences saved to: " + diffOutputPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return diference;
    }

    private static String getCellValue(Row row, int cellIndex) {
        if (row == null) return "";
        Cell cell = row.getCell(cellIndex);
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            case FORMULA: return cell.getCellFormula();
            case BLANK: return "";
            default: return "";
        }
    }

    private static void createHeaderRow(Sheet sheet) {
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Sheet Name");
        header.createCell(1).setCellValue("Row");
        header.createCell(2).setCellValue("Column");
        header.createCell(3).setCellValue("Cell Ref");
        header.createCell(4).setCellValue("File1 Value");
        header.createCell(5).setCellValue("File2 Value");
    }

    private static String getCellRef(int rowIndex, int colIndex) {
        return CellReference.convertNumToColString(colIndex) + (rowIndex + 1);
    }

    public static void main(String[] args) throws FileNotFoundException {
        ExcelUtil excelUtil=new ExcelUtil();
        excelUtil.excelComparator("./src/main/resources/menuItems/Sales/Transactions/480460_SI1 1_Output1.xlsx",
                "./src/main/resources/menuItems/Sales/Transactions/480460_SI1 1_Output2.xlsx","SaleInvoiceOutputFile");
    }

}

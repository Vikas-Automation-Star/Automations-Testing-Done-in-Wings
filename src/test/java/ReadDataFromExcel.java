import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadDataFromExcel {

    static String file = "./src/main/resources/ExcelData.xlsx";

    public String readExcel(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(new File(filePath));
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(2);
        Cell cell = row.getCell(1);
        String data = cell.getStringCellValue();
        return data;
    }

    public static void main(String[] args) throws IOException {
        ReadDataFromExcel read = new ReadDataFromExcel();
        System.out.println(read.readExcel(file, "Credentials"));
    }
}

package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesPrices;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.*;

import java.io.IOException;

public class TestSalesPrice {
    WindowsDriver driver;
    AppLogin login = new AppLogin();
    private  Process winAppDriverProcess;

    private static final String TEMP_API_SALES_PRICE="./output/temp_api_request_bodies/salesPrice.json";
    private static final String API_RESPONSE_SALES_PRICE="./output/api_responses/salesPrice.json";
    private static final String OUTPUT_FILE_SALES_PRICE="./src/main/resources/menuItems/Sales/Transactions/477030 - Sales Prices-AC_Output.xls";

    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/477030 - Sales Prices-AC.xls";


    @BeforeMethod
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        winAppDriverProcess = new ProcessBuilder("cmd.exe", "/c", "start", "WinAppDriver.exe", "127.0.0.1", "4723").start();
        Thread.sleep(5000);
        System.out.println("✅ WinAppDriver started");
        driver = login.login();
    }

    @Test
    public void salesPrice() throws Exception {
        SalesPrices pricesAndDiscount = new SalesPrices(driver, dataFile);
        pricesAndDiscount.salesPrices(TEMP_API_SALES_PRICE,API_RESPONSE_SALES_PRICE,OUTPUT_FILE_SALES_PRICE);
    }

    @AfterMethod
    public void afterTest() throws IOException {
        login.logout();
        if(driver != null) driver.quit();
        Runtime.getRuntime().exec("taskkill /F /IM WinAppDriver.exe");
        System.out.println("✅ WinAppDriver stopped");
    }

}
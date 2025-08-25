package menuItems.inventory.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.transactions.StockConsumption;
import java.io.IOException;

public class TestStockConsumption {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    private static final String TEMP_API_BODY_STOCK_CONSUMPTION="./output/temp_api_request_bodies/stockConsumption.json";
    private static final String API_RESPONSE_STOCK_CONSUMPTION ="./output/api_responses/stockConsumption.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/inventory/transactions/458959 - Stock Consumption-AC_SCN_1_Output.xls";
    String file = "./src/main/resources/menuItems/inventory/transactions/458959 - Stock Consumption-AC_SCN_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void stockConsumption() throws InterruptedException, IOException, ParseException {
        StockConsumption consumptionTrans = new StockConsumption(driver, file);
        consumptionTrans.stockConsumption(TEMP_API_BODY_STOCK_CONSUMPTION,API_RESPONSE_STOCK_CONSUMPTION,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
package menuItems.inventory.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.transactions.StockCreation;
import java.awt.*;
import java.io.IOException;

public class TestStockCreation {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    private static final String TEMP_API_BODY_STOCK_CREATION="./output/temp_api_request_bodies/stockCreation.json";
    private static final String API_RESPONSE_STOCK_CREATION ="./output/api_responses/stockCreation.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/inventory/transactions/476947 - Stock Creation-AC_SCR_16_Output.xls";

    String file = "./src/main/resources/menuItems/inventory/transactions/476947 - Stock Creation-AC_SCR_16.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void stockCreation() throws InterruptedException, AWTException, IOException, ParseException {
        StockCreation creationTrans = new StockCreation(driver, file);
        creationTrans.stockCreation(TEMP_API_BODY_STOCK_CREATION,API_RESPONSE_STOCK_CREATION,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}
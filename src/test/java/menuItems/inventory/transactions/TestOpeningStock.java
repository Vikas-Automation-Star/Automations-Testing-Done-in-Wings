package menuItems.inventory.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.transactions.OpeningStock;
import java.io.IOException;

public class TestOpeningStock {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    private static final String TEMP_API_BODY_OPENING_STOCK="./output/temp_api_request_bodies/openingStock.json";
    private static final String API_RESPONSE_OPENING_STOCK ="./output/api_responses/openingStock.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/inventory/transactions/455721 - Opening Stock-AC_OS_3_Output.xls";
    String file = "./src/main/resources/menuItems/inventory/transactions/455721 - Opening Stock-AC_OS_3.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void openingStock() throws Exception {
        OpeningStock stockTrans = new OpeningStock(driver, file);
        stockTrans.openingStock(TEMP_API_BODY_OPENING_STOCK,API_RESPONSE_OPENING_STOCK,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
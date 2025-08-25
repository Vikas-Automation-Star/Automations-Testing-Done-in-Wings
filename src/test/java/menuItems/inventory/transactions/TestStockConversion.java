package menuItems.inventory.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.transactions.StockConversion;
import java.io.IOException;

public class TestStockConversion {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_STOCK_CONVERSION="./output/temp_api_request_bodies/stockConversion.json";
    private static final String API_RESPONSE_STOCK_CONVERSION ="./output/api_responses/stockConversion.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/inventory/transactions/458456 - Stock Conversion-AC_SC_1_Output.xls";
    String file = "./src/main/resources/menuItems/inventory/transactions/458456 - Stock Conversion-AC_SC_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }
    @Test
    public void stockConversion() throws InterruptedException, IOException, ParseException {
        StockConversion conversionTrans = new StockConversion(driver, file);
        conversionTrans.stockConversion(TEMP_API_BODY_STOCK_CONVERSION,API_RESPONSE_STOCK_CONVERSION,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
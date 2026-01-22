package tradeTesting.inventory.transctions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.inventory.transactions.StockConversions;

import java.io.IOException;

public class TestStockConversion {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();


    private static final String TEMP_API_BODY_StockConversation="./output/tradeOutputs/temp_api_request_bodies/StockConversation.json";
    private static final String API_RESPONSE_StockConversation="./output/tradeOutputs/api_responses/StockConversation.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/inventory/transactions/456247 - Stock Conversion-Trd_STC_1_Output.xls";

    String dataFile = "./src/main/resources/tradeAutomation/inventory/transactions/456247 - Stock Conversion-Trd_STC_1.xls";

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException {
        driver= appLogin.tradeLogin();
    }

    @Test
    public void stockConversion() throws Exception {
        StockConversions conversions=new StockConversions(driver,dataFile);
        conversions.stockConversion();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}

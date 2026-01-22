package tradeTesting.inventory.transctions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.inventory.transactions.InitiateStockTake;

import java.io.IOException;

public class TestInitiateStockTake {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();


    private static final String TEMP_API_BODY_StockConversation="./output/tradeOutputs/temp_api_request_bodies/InitiateStockTake.json";
    private static final String API_RESPONSE_StockConversation="./output/tradeOutputs/api_responses/InitiateStockTake.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/inventory/transactions/453504 - Initiate Stock Take-Trd_IST_1_Output.xls";

    String dataFile = "./src/main/resources/tradeAutomation/inventory/transactions/453504 - Initiate Stock Take-Trd_IST_1.xls";

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException {
        driver= appLogin.tradeLogin();
    }

    @Test
    public void stockConversion() throws Exception {
        InitiateStockTake stockTake=new InitiateStockTake(driver,dataFile);
        stockTake.initiateStockTake();

    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}

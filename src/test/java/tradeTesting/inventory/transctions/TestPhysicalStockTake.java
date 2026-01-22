package tradeTesting.inventory.transctions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.inventory.transactions.PhysicalStockTake;

import java.io.IOException;

public class TestPhysicalStockTake {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();


    private static final String TEMP_API_BODY_StockConversation="./output/tradeOutputs/temp_api_request_bodies/PhysicalStockTake.json";
    private static final String API_RESPONSE_StockConversation="./output/tradeOutputs/api_responses/PhysicalStockTake.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/inventory/transactions/456250 - Physical Stock Take-Trd_PST_1_Output.xls";

    String dataFile = "./src/main/resources/tradeAutomation/inventory/transactions/456250 - Physical Stock Take-Trd_PST_1.xls";

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException {
        driver= appLogin.tradeLogin();
    }

    @Test
    public void physicalStockTake() throws Exception {
        PhysicalStockTake physicalStockTake=new PhysicalStockTake(driver,dataFile);
        physicalStockTake.physicalStockTake();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}

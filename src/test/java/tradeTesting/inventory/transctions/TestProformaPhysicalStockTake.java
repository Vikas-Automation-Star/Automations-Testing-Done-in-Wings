package tradeTesting.inventory.transctions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.inventory.transactions.ProformaPhysicalStockTake;

import java.io.IOException;

public class TestProformaPhysicalStockTake {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();


    private static final String TEMP_API_BODY_StockConversation="./output/tradeOutputs/temp_api_request_bodies/ProformaPhysicalStockTake.json";
    private static final String API_RESPONSE_StockConversation="./output/tradeOutputs/api_responses/ProformaPhysicalStockTake.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/inventory/transactions/456252 - Physical Stock-Proforma-Trd_PSSP_1_Output.xls";

    String dataFile = "./src/main/resources/tradeAutomation/inventory/transactions/456252 - Physical Stock-Proforma-Trd_PSSP_1.xls";

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException {
        driver= appLogin.tradeLogin();
    }

    @Test
    public void stockConversion() throws Exception {
        ProformaPhysicalStockTake physicalStockTake=new ProformaPhysicalStockTake(driver,dataFile);
        physicalStockTake.proformaPhysicalStockTake();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}

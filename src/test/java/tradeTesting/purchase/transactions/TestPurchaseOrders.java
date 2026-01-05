package tradeTesting.purchase.transactions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestPurchaseOrders {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();


    private static final String TEMP_API_BODY_PurchaseOrders="./output/tradeOutputs/temp_api_request_bodies/PurchaseOrders.json";
    private static final String API_RESPONSE_BatchPurchasePrices="./output/tradeOutputs/api_responses/PurchaseOrders.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/purchase/transactions/487547 - Purchase Orders-Trd_PO_1_Output.xls";

    String dataFile = "./src/main/resources/tradeAutomation/purchase/transactions/487547 - Purchase Orders-Trd_PO_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver= appLogin.tradeLogin();
    }

    @Test
    public void purchaseOrders() throws Exception {
        PurchaseOrders orders=new PurchaseOrders(driver,dataFile);
        orders.purchaseOrders();

    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}

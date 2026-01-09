package tradeTesting.purchase.transactions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestPurchaseOrdersCancellation {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();


    private static final String TEMP_API_BODY_PurchaseOrdersCancellation="./output/tradeOutputs/temp_api_request_bodies/PurchaseOrdersCancellation.json";
    private static final String API_RESPONSE_PurchaseOrdersCancellation="./output/tradeOutputs/api_responses/PurchaseOrdersCancellation.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/purchase/transactions/481499 - Purchase Order Cancellation-Trd_POC_1_PO_2_Output.xls";

    String dataFile = "./src/main/resources/tradeAutomation/purchase/transactions/481499 - Purchase Order Cancellation-Trd_POC_1_PO_2.xls";

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException {
        driver= appLogin.tradeLogin();
    }

    @Test
    public void purchaseOrdersCancellation() throws Exception {
        PurchaseOrdersCancellation cancellation=new PurchaseOrdersCancellation(driver,dataFile);
        cancellation.purchaseOrderCancellation("PO 5");
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}

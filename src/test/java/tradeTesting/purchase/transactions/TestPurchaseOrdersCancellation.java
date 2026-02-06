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

    private static final String TEMP_API_BODY_PurchaseOrders="./output/tradeOutputs/temp_api_request_bodies/PurchaseOrders.json";
    private static final String API_RESPONSE_PurchaseOrders="./output/tradeOutputs/api_responses/PurchaseOrders.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/purchase/transactions/493145 - Purchase Orders-Trd_PO_4_Output.xls";


    private static final String TEMP_API_BODY_PurchaseOrdersCancellation="./output/tradeOutputs/temp_api_request_bodies/PurchaseOrdersCancellation.json";
    private static final String API_RESPONSE_PurchaseOrdersCancellation="./output/tradeOutputs/api_responses/PurchaseOrdersCancellation.json";
    private static final String OUTPUT_FILE1="./src/main/resources/tradeAutomation/purchase/transactions/490959 - Purchase Order Cancellation-Trd_POC_2_PO_3_Output.xls";

    String dataFile = "./src/main/resources/tradeAutomation/purchase/transactions/493145 - Purchase Orders-Trd_PO_4.xls";
    String dataFile1 = "./src/main/resources/tradeAutomation/purchase/transactions/490959 - Purchase Order Cancellation-Trd_POC_2_PO_3.xls";

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException {
        driver= appLogin.tradeLogin();
    }

    @Test
    public void purchaseOrdersCancellation() throws Exception {
        PurchaseOrders orders=new PurchaseOrders(driver,dataFile);
        String order= orders.purchaseOrders("","","");
//
//        appLogin.logout();
//        driver= appLogin.tradeLogin();

        PurchaseOrdersCancellation cancellation=new PurchaseOrdersCancellation(driver,dataFile1);
        cancellation.purchaseOrderCancellation("PO 5","","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}

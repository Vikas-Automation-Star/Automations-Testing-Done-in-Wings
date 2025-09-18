package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseOrderCancellation;
import com.wings.pages.purchase.transactions.PurchaseOrders;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestPurchaseOrderCancellation {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_PURCHASE_ORDERS="./output/temp_api_request_bodies/purchaseOrders.json";
    private static final String API_RESPONSE_PURCHASE_ORDERS="./output/api_responses/purchaseOrders.json";
    private static final String OUTPUT_FILE_PURCHASE_ORDERS="./src/main/resources/menuItems/purchase/transactions/476937 - Purchase Orders-AC_PO_7_Output.xls";

    private static final String TEMP_API_PURCHASE_ORDER_CANCELLATION="./output/temp_api_request_bodies/purchaseOrderCancellation.json";
    private static final String API_RESPONSE_PURCHASE_ORDER_CANCELLATION="./output/api_responses/purchaseOrderCancellation.json";
    private static final String OUTPUT_FILE_PURCHASE_ORDER_CANCELLATION="./src/main/resources/menuItems/purchase/transactions/458834 - Purchase Orders Cancellation-AC_POC_2_Output.xls";

    String file = "./src/main/resources/menuItems/purchase/transactions/476937 - Purchase Orders-AC_PO_7.xls";
    String file1 = "./src/main/resources/menuItems/purchase/transactions/458834 - Purchase Orders Cancellation-AC_POC_2.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void purchaseOrderCancellations() throws Exception, AWTException {
        PurchaseOrders purchaseOrders=new PurchaseOrders(driver,file);
        String purchaseOrderVoucher= purchaseOrders.purchaseOrders(TEMP_API_PURCHASE_ORDERS,API_RESPONSE_PURCHASE_ORDERS,OUTPUT_FILE_PURCHASE_ORDERS);

        appLogin.logout();
        driver=appLogin.login();

        PurchaseOrderCancellation poc = new PurchaseOrderCancellation(driver, file1);
        poc.purchaseOrderCancellation(purchaseOrderVoucher,TEMP_API_PURCHASE_ORDER_CANCELLATION,API_RESPONSE_PURCHASE_ORDER_CANCELLATION,OUTPUT_FILE_PURCHASE_ORDER_CANCELLATION);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
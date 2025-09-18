package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.*;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestPurchaseOrders {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_PURCHASE_ORDERS="./output/temp_api_request_bodies/purchaseOrders.json";
    private static final String API_RESPONSE_PURCHASE_ORDERS="./output/api_responses/purchaseOrders.json";
    private static final String OUTPUT_FILE_PURCHASE_ORDERS="./src/main/resources/menuItems/purchase/transactions/476937 - Purchase Orders-AC_PO_7_Output.xls";
    String file = "./src/main/resources/menuItems/purchase/transactions/476937 - Purchase Orders-AC_PO_7.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void purchaseOrders() throws Exception {
        PurchaseOrders purchaseOrders=new PurchaseOrders(driver,file);
        purchaseOrders.purchaseOrders(TEMP_API_PURCHASE_ORDERS,API_RESPONSE_PURCHASE_ORDERS,OUTPUT_FILE_PURCHASE_ORDERS);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

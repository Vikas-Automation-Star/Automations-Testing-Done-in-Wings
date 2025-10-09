package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.MaterialReceiptsAgainstOrder;
import com.wings.pages.purchase.transactions.PurchaseOrders;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestMaterialReceiptsAgainstOrders {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_PURCHASE_ORDERS="./output/temp_api_request_bodies/purchaseOrders.json";
    private static final String API_RESPONSE_PURCHASE_ORDERS="./output/api_responses/purchaseOrders.json";
    private static final String OUTPUT_FILE_PURCHASE_ORDERS="./src/main/resources/menuItems/purchase/transactions/476937 - Purchase Orders-AC_PO_7_Output.xls";

    private static final String TEMP_API_MATERIAL_RECEIPT_AGAINST_ORDER="./output/temp_api_request_bodies/materialReceiptAgainstOrder.json";
    private static final String API_RESPONSE_MATERIAL_RECEIPT_AGAINST_ORDER="./output/api_responses/materialReceiptAgainstOrder.json";
    private static final String OUTPUT_FILE_MATERIAL_RECEIPT_AGAINST_ORDER="./src/main/resources/menuItems/purchase/transactions/476849 - Material Receipts against Orders-AC_MRO_4_Output.xls";

    String dataFile="./src/main/resources/menuItems/purchase/transactions/476937 - Purchase Orders-AC_PO_7.xls";
    String file = "./src/main/resources/menuItems/purchase/transactions/476849 - Material Receipts against Orders-AC_MRO_4.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void materialReceiptsAgainstOrders() throws Exception {
//        PurchaseOrders purchaseOrders=new PurchaseOrders(driver,dataFile);
//        String po=purchaseOrders.purchaseOrders(TEMP_API_PURCHASE_ORDERS,API_RESPONSE_PURCHASE_ORDERS,OUTPUT_FILE_PURCHASE_ORDERS);
//        appLogin.logout();
//        driver=appLogin.login();
        MaterialReceiptsAgainstOrder mrao = new MaterialReceiptsAgainstOrder(driver, file);
        mrao.materialReceiptsAgainstOrder("PO 14",TEMP_API_MATERIAL_RECEIPT_AGAINST_ORDER,API_RESPONSE_MATERIAL_RECEIPT_AGAINST_ORDER,OUTPUT_FILE_MATERIAL_RECEIPT_AGAINST_ORDER);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

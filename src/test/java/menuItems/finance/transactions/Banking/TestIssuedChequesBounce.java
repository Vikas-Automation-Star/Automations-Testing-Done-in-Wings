package menuItems.finance.transactions.Banking;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Banking.IssuedChequesBounce;
import com.wings.pages.finance.transactions.Banking.ReceivedChequesBounce;
import com.wings.pages.purchase.transactions.PurchaseOrders;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestIssuedChequesBounce {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_PURCHASE_ORDERS="./output/temp_api_request_bodies/purchaseOrders.json";
    private static final String API_RESPONSE_PURCHASE_ORDERS="./output/api_responses/purchaseOrders.json";
    private static final String OUTPUT_FILE_PURCHASE_ORDERS="./src/main/resources/menuItems/purchase/transactions/476937 - Purchase Orders-AC_PO_7_Output.xls";
    String file = "./src/main/resources/menuItems/purchase/transactions/476937 - Purchase Orders-AC_PO_7.xls";

    private static final String TEMP_API_BODY_ISSUED_CHEQUES_BOUNCE ="./output/temp_api_request_bodies/issuedChequesBounce.json";
    private static final String API_RESPONSE_ISSUED_CHEQUES_BOUNCE ="./output/api_responses/issuedChequesBounce.json";
    private static final String OUTPUT_FILE_ISSUED_CHEQUES_BOUNCE="./src/main/resources/menuItems/finance/transaction/459050 - Issued Cheques Bounce-AC_CBI_3_Output.xls";
    String dataFile = "./src/main/resources/menuItems/finance/transaction/459050 - Issued Cheques Bounce-AC_CBI_3.xls";

    private static final String TEMP_API_RECEIVED_CHEQUES_BOUNCE="./output/temp_api_request_bodies/receivedChequesBounce.json";
    private static final String API_RESPONSE_RECEIVED_CHEQUES_BOUNCE="./output/api_responses/receivedChequesBounce.json";
    private static final String OUTPUT_FILE_RECEIVED_CHEQUES_BOUNCE="./src/main/resources/menuItems/finance/transaction/460820 - Received Cheques Bounce-AC_CBR_3_Output.xls";
    String dataFile1 = "./src/main/resources/menuItems/finance/transaction/460820 - Received Cheques Bounce-AC_CBR_3.xls";

    @BeforeTest
    public void beforeTest() throws Exception {
        driver = appLogin.login();
    }

    @Test
    public void issuedChequesBounce() throws Exception {
//        PurchaseOrders purchaseOrders=new PurchaseOrders(driver,file);
//        String purchaseOrderVoucher= purchaseOrders.purchaseOrders(TEMP_API_PURCHASE_ORDERS,API_RESPONSE_PURCHASE_ORDERS,OUTPUT_FILE_PURCHASE_ORDERS);
//
//        appLogin.logout();
//        driver=appLogin.login();

        IssuedChequesBounce issuedChequesBounce=new IssuedChequesBounce(driver,dataFile);
        String issuesVoucher=issuedChequesBounce.issuedChequesBounce("PO 10",TEMP_API_BODY_ISSUED_CHEQUES_BOUNCE,API_RESPONSE_ISSUED_CHEQUES_BOUNCE,OUTPUT_FILE_ISSUED_CHEQUES_BOUNCE);

        appLogin.logout();
        driver=appLogin.login();

        ReceivedChequesBounce chequesBounce = new ReceivedChequesBounce(driver, dataFile1);
        chequesBounce.receivedCheckBounce(issuesVoucher,TEMP_API_RECEIVED_CHEQUES_BOUNCE,API_RESPONSE_RECEIVED_CHEQUES_BOUNCE,OUTPUT_FILE_RECEIVED_CHEQUES_BOUNCE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
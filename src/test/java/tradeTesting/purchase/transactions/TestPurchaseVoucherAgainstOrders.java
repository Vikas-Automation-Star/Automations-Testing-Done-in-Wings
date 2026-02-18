package tradeTesting.purchase.transactions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestPurchaseVoucherAgainstOrders {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();


    private static final String TEMP_API_BODY_PURCHASE_ORDERS="./output/temp_api_request_bodies/PurchaseOrders.json";
    private static final String API_RESPONSE_PURCHASE_VOUCHERS_AGAINST_ORDERS="./output/api_responses/PurchaseOrders.json";
    private static final String OUTPUT_FILE4="./src/main/resources/tradeAutomation/purchase/transactions/493145 - Purchase Orders-Trd_PO_4_Output.xls";

    private static final String TEMP_API_BODY_PurchaseVouchers_AgainstOrders="./output/tradeOutputs/temp_api_request_bodies/PurchaseVouchers_AgainstOrders.json";
    private static final String API_RESPONSE_PurchaseVouchers_AgainstOrders="./output/tradeOutputs/api_responses/PurchaseVouchers_AgainstOrders.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/purchase/transactions/490416 - Purchase Vouchers against Order-Trd_PVAO_2_PO_4_Output.xls";

    String dataFile = "./src/main/resources/tradeAutomation/purchase/transactions/493145 - Purchase Orders-Trd_PO_4.xls";
    String dataFile1 = "./src/main/resources/tradeAutomation/purchase/transactions/490416 - Purchase Vouchers against Order-Trd_PVAO_2_PO_4.xls";


    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver= appLogin.tradeLogin();
    }

    @Test
    public void purchaseVouchersAgainstOrders() throws Exception {
//        PurchaseOrders orders=new PurchaseOrders(driver,dataFile);
//        String purchaseOrders=orders.purchaseOrders("","","");
//
//        appLogin.logout();
//        driver= appLogin.tradeLogin();

        PurchaseVouchersAgainstOrders vouchersAgainstOrder = new PurchaseVouchersAgainstOrders(driver, dataFile1);
        String PurchaseVouchersAgainstOrder=vouchersAgainstOrder.purchaseVouchersAgainstOrder("PO 6","BP 2","","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

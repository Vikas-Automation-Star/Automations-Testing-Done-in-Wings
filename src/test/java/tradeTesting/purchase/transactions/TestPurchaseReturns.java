package tradeTesting.purchase.transactions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestPurchaseReturns {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_PurchaseVouchers="./output/tradeOutputs/temp_api_request_bodies/PurchaseVouchers.json";
    private static final String API_RESPONSE_PurchaseVouchers="./output/tradeOutputs/api_responses/PurchaseVouchers.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/purchase/transactions/491716 - Purchase Vouchers-Trd_PV_3_CR_2_CP_1_Output.xls";

    private static final String TEMP_API_BODY_PURCHASE_RETURNS="./output/temp_api_request_bodies/purchaseReturns.json";
    private static final String API_RESPONSE_PURCHASE_RETURNS="./output/api_responses/purchaseReturns.json";
    private static final String OUTPUT_FILE_PURCHASE_RETURNS="./src/main/resources/tradeAutomation/purchase/transactions/494428 - Purchase Returns-Trd_PRT_2_PV_3_Output.xls";

    String file = "./src/main/resources/tradeAutomation/purchase/transactions/494428 - Purchase Returns-Trd_PRT_2_PV_3.xls";
    String dataFile = "./src/main/resources/tradeAutomation/purchase/transactions/491716 - Purchase Vouchers-Trd_PV_3_CR_2_CP_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.tradeLogin();
    }

    @Test
    public void purchaseReturns() throws Exception {
//        PurchaseVouchers purchaseVouchers=new PurchaseVouchers(driver,dataFile);
//        String voucher= purchaseVouchers.purchaseVouchers("","","","","");
//
//        appLogin.logout();
//        driver= appLogin.tradeLogin();

        PurchaseReturns purchaseReturns=new PurchaseReturns(driver,file);
        purchaseReturns.purchaseReturns("PV 2");
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }

}

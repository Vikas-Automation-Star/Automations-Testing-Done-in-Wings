package tradeTesting.purchase.transactions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestPurchaseVouchers {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();


    private static final String TEMP_API_BODY_PurchaseOrdersCancellation="./output/tradeOutputs/temp_api_request_bodies/PurchaseVouchers.json";
    private static final String API_RESPONSE_PurchaseOrdersCancellation="./output/tradeOutputs/api_responses/PurchaseVouchers.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/purchase/transactions/487709 - Purchase Vouchers-Trd_PV_1_Output.xls";

    String dataFile = "./src/main/resources/tradeAutomation/purchase/transactions/487709 - Purchase Vouchers-Trd_PV_1.xls";

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException {
        driver= appLogin.tradeLogin();
    }

    @Test
    public void purchaseVoucher() throws Exception {
        PurchaseVouchers purchaseVouchers=new PurchaseVouchers(driver,dataFile);
        purchaseVouchers.purchaseVouchers();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}

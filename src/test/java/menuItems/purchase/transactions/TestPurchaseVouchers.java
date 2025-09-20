package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseVoucher;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestPurchaseVouchers {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_PURCHASE_VOUCHERS="./output/temp_api_request_bodies/PurchaseVouchers.json";
    private static final String API_RESPONSE_PURCHASE_VOUCHERS="./output/api_responses/PurchaseVouchers.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9_Output.xls";

    String file = "./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void purchaseVouchers() throws Exception {
        PurchaseVoucher po = new PurchaseVoucher(driver, file);
        po.purchaseVoucher(TEMP_API_BODY_PURCHASE_VOUCHERS,API_RESPONSE_PURCHASE_VOUCHERS,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}

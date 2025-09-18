package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.ProformaPurchaseVouchers;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestProformaPurchaseVouchers {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_PROFORMA_PURCHASE_VOUCHER="./output/temp_api_request_bodies/proformaPurchaseVoucher.json";
    private static final String API_RESPONSE_PROFORMA_PURCHASE_VOUCHER="./output/api_responses/proformaPurchaseVoucher.json";
    private static final String OUTPUT_FILE_PROFORMA_PURCHASE_VOUCHER="./src/main/resources/menuItems/purchase/transactions/460243 - Proforma Purchase Vouchers-AC_PPV_1_Output.xls";

    String file = "./src/main/resources/menuItems/purchase/transactions/460243 - Proforma Purchase Vouchers-AC_PPV_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void proformaPurchaseVouchers() throws Exception {
        ProformaPurchaseVouchers proformaPurchaseVouchers=new ProformaPurchaseVouchers(driver,file);
        proformaPurchaseVouchers.proformaPurchaseVouchers(TEMP_API_PROFORMA_PURCHASE_VOUCHER,API_RESPONSE_PROFORMA_PURCHASE_VOUCHER,OUTPUT_FILE_PROFORMA_PURCHASE_VOUCHER);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

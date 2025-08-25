package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseVouchersAgainstReceipt;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestPurchaseVouchersAgainstReceipts {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/purchase/transactions/479085 - Purchase Vouchers against Receipts-AC_PVAMR_1_MR_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void purchaseVouchersAgainstTheReceipts() throws Exception, AWTException {
        PurchaseVouchersAgainstReceipt vouchersAgainstReceipt=new PurchaseVouchersAgainstReceipt(driver,file);
        vouchersAgainstReceipt.purchaseVouchersAgainstReceipt("MR 3");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

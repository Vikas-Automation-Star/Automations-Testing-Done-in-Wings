package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.ProformaPurchaseVouchers;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestProformaPurchaseVouchers {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/purchase/transactions/460243 - Proforma Purchase Vouchers-AC_PPV_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void proformaPurchaseVouchers() throws Exception, AWTException {
        ProformaPurchaseVouchers proformaPurchaseVouchers=new ProformaPurchaseVouchers(driver,file);
        proformaPurchaseVouchers.proformaPurchaseVouchers();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

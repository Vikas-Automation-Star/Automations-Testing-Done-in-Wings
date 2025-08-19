package menuItems.finance.transactions.Payments;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Payments.CashPayments;
import java.awt.*;
import java.io.IOException;

public class TestCashPayments {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String file = "./src/main/resources/menuItems/finance/transaction/459470 - Cash Payments-AC_CP_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common = new Common(driver);
        driver = appLogin.login();
    }

    @Test
    public void cashPayment() throws InterruptedException, AWTException, IOException, ParseException {
        CashPayments cashPayments = new CashPayments(driver, file);
        cashPayments.cashPayment();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
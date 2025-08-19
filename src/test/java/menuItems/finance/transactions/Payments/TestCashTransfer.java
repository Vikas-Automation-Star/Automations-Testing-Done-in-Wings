package menuItems.finance.transactions.Payments;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Payments.CashTransfer;
import java.awt.*;
import java.io.IOException;

public class TestCashTransfer {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/finance/transaction/454215 - Cash Transfers-AC_CT_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void cashTransfer() throws InterruptedException, AWTException, IOException, ParseException {
        CashTransfer cashTransfer = new CashTransfer(driver, dataFile);
        cashTransfer.cashTransfer();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
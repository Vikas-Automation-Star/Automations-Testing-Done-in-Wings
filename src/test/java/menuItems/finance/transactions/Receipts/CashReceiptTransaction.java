package menuItems.finance.transactions.Receipts;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Receipts.CashReceipts;
import java.awt.*;
import java.io.IOException;

public class CashReceiptTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/finance/transaction/cashReceipt.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();

    }

    @Test
    public void cashReceipt() throws InterruptedException, AWTException, IOException, ParseException {
        CashReceipts cashReceipts = new CashReceipts(driver, dataFile);
        cashReceipts.cashReceipt();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
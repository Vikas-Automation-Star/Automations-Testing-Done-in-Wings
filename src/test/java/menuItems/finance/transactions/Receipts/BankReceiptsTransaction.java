package menuItems.finance.transactions.Receipts;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Receipts.BankReceipts;
import java.awt.*;
import java.io.IOException;

public class BankReceiptsTransaction{
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/bankReceipt.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test- Bank Receipts");
    }

    @Test
    public void bankReceipt() throws InterruptedException, AWTException, IOException, ParseException {
        BankReceipts bankReceipts=new BankReceipts(driver,dataFile);
        bankReceipts.bankReceipt();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
        Allure.step("After Test- Bank Receipts");
    }
}
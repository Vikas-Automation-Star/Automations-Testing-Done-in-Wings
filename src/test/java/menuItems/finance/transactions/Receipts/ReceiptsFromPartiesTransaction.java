package menuItems.finance.transactions.Receipts;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Receipts.ReceiptsFromParties;

import java.awt.*;
import java.io.IOException;

public class ReceiptsFromPartiesTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String file = "./src/main/resources/menuItems/finance/transaction/receiptFromParty.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(file,"receiptsFromParties","userName"),common.getData(file,"receiptsFromParties","password"));
    }

    @Test
    public void receiptFromParty() throws IOException, ParseException, InterruptedException, AWTException {
        ReceiptsFromParties fromParties = new ReceiptsFromParties(driver, file);
        fromParties.receiptFromParty();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
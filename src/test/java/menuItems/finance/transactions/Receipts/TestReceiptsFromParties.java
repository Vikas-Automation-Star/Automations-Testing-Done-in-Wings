package menuItems.finance.transactions.Receipts;


import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Receipts.ReceiptsFromParties;

import java.awt.*;
import java.io.IOException;

public class TestReceiptsFromParties {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/465649 - Receipts from Parties-AC_PREC_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void receiptFromParty() throws IOException, ParseException, InterruptedException, AWTException {
        ReceiptsFromParties fromParties = new ReceiptsFromParties(driver, dataFile);
        fromParties.receiptFromParty();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
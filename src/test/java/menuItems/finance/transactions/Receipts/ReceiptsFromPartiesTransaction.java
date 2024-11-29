package menuItems.finance.transactions.Receipts;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
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
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/receiptFromParty.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test - Receipts from Parties");
    }

    @Test
    public void receiptFromParty() throws IOException, ParseException, InterruptedException, AWTException {
        ReceiptsFromParties fromParties=new ReceiptsFromParties(driver,dataFile);
        fromParties.receiptFromParty();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
        Allure.step("After Test - Receipts from Parties");
    }
}
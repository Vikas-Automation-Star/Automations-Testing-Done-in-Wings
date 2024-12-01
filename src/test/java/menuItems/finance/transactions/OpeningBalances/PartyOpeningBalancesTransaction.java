package menuItems.finance.transactions.OpeningBalances;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.OpeningBalances.PartyOpeningBalances;
import java.awt.*;
import java.io.IOException;

public class PartyOpeningBalancesTransaction {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/partyOpeningBalance.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test - Party Opening Balances");
    }

    @Test
    public void partyOpeningBalance() throws IOException, ParseException, InterruptedException, AWTException {
        PartyOpeningBalances partyOpeningBalances=new PartyOpeningBalances(driver,dataFile);
        partyOpeningBalances.partyOpeningBalance();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
        Allure.step("After Test - Party Opening Balances");
    }
}
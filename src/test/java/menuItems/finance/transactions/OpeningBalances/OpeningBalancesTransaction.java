package menuItems.finance.transactions.OpeningBalances;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.OpeningBalances.OpeningBalance;
import java.awt.*;
import java.io.IOException;

public class OpeningBalancesTransaction {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/openingBalance.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void openingBalance() throws IOException, ParseException, InterruptedException, AWTException {
        OpeningBalance openingBalance=new OpeningBalance(driver, dataFile);
        openingBalance.openingBalance();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

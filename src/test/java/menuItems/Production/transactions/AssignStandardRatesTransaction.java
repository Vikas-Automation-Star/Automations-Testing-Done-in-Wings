package menuItems.Production.transactions;

import com.wings.pages.production.transactions.AssignStandardRates;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class AssignStandardRatesTransaction {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="./src/main/resources/MenuItems/production/transactions/AssignStandardRatesTransaction.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();

    }

    @Test
    public void assignStandardRatesTransaction() throws IOException, ParseException, InterruptedException, AWTException {
        AssignStandardRates asr=new AssignStandardRates(driver,file);
        asr.assignStandardRates();

    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}

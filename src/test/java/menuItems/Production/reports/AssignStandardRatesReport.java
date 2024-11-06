package menuItems.Production.reports;

import com.wings.pages.production.reports.AssignStandardRates;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class AssignStandardRatesReport  {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();

    @BeforeTest
    public void AssignStandardRates() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void assignStandardRates() throws IOException, ParseException, InterruptedException {
        AssignStandardRates asr=new AssignStandardRates(driver);
        asr.assignStandardRatesReport();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

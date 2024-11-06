package  menuItems.Taxes.reports.TCS;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.reports.TCS.TcsOpeningBalances;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TcsOpeningBalancesReport {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void TcsOpeningBalancesReport() throws InterruptedException, IOException, ParseException {
        TcsOpeningBalances tob=new TcsOpeningBalances(driver);
        tob.tcsOpeningBalences();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

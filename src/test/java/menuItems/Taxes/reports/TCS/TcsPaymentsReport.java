package menuItems.Taxes.reports.TCS;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.reports.TCS.TcsPayments;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TcsPaymentsReport {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void TcsPaymentsReport() throws InterruptedException, IOException, ParseException {
        TcsPayments tp=new TcsPayments(driver);
        tp.tcsPayments();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

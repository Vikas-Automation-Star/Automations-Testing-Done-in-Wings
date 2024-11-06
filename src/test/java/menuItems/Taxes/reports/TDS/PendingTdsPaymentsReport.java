package menuItems.Taxes.reports.TDS;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.reports.TDS.PendigTdsPayments;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PendingTdsPaymentsReport {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void pendingTdsPaymentsReport() throws InterruptedException, IOException, ParseException {
        PendigTdsPayments ptp=new PendigTdsPayments(driver);
        ptp.pendigTdsPayments();
    }
    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

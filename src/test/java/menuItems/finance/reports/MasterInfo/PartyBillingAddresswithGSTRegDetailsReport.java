package menuItems.finance.reports.MasterInfo;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.MasterInfo.PartyBillingAddresswithGSTRegDetailsReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class PartyBillingAddresswithGSTRegDetailsReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void billingAddressGST() throws InterruptedException, AWTException {
        PartyBillingAddresswithGSTRegDetailsReportCode regDetailsReportCode = new PartyBillingAddresswithGSTRegDetailsReportCode(driver);
        regDetailsReportCode.billingAdresswithGSTdetails();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

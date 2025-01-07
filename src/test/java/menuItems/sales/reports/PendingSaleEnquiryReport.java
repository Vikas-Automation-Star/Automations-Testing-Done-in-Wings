package menuItems.sales.reports;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.reports.PendingSalesEnquiryReportCode;

import java.io.IOException;

public class PendingSaleEnquiryReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void pendingSalesEnquiryReport() throws IOException, ParseException, InterruptedException {
        PendingSalesEnquiryReportCode salesEnquiry=new PendingSalesEnquiryReportCode(driver);
        salesEnquiry.pendingEnquiryReport();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }

}

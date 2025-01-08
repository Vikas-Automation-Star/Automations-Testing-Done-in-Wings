package menuItems.sales.reports;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.reports.salesEnquiryReportCode;

import java.io.IOException;

public class salesEnquiryReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void newSalesEnquiryTrsncn() throws InterruptedException {
        salesEnquiryReportCode sales = new salesEnquiryReportCode(driver);
        sales.enquiryReport();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

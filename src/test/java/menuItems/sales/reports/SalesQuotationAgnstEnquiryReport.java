package menuItems.sales.reports;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.reports.SalesQuotationAgainstEnquiryReportCode;
import java.io.IOException;

public class SalesQuotationAgnstEnquiryReport {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void quotationAgainstEnquiry() throws InterruptedException {
        SalesQuotationAgainstEnquiryReportCode report=new SalesQuotationAgainstEnquiryReportCode(driver);
        report.enquiryReport();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}

package menuItems.sales.reports;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.reports.SalesOrderAgainstQuotationReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class SalesOrdrAgainstQuotationReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void quotationAgainstEnquiry() throws InterruptedException {
        SalesOrderAgainstQuotationReportCode quotationReport = new SalesOrderAgainstQuotationReportCode(driver);
        quotationReport.orderAgnstQuotation();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}

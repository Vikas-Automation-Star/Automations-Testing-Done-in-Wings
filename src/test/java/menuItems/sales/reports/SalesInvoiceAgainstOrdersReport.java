package menuItems.sales.reports;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.reports.SalesInvoiceAgainstOrdersReportCode;

import java.awt.*;
import java.io.IOException;

public class SalesInvoiceAgainstOrdersReport {
    WindowsDriver driver;
    AppLogin login=new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=login.launchSingleUserApp();
        login.singleUserLogin();
    }

    @Test
    public void salesInvoiceAgainstOrders() throws IOException, ParseException, InterruptedException, AWTException {
        SalesInvoiceAgainstOrdersReportCode invoiceAgainstOrdersReportCode=new SalesInvoiceAgainstOrdersReportCode(driver);
        invoiceAgainstOrdersReportCode.salesInvoiceAgainstorders();

    }

    @AfterTest
    public void afterTest(){
        login.logout();
    }
}

package menuItems.sales.reports;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.reports.ProformaSalesReportCode;

import java.awt.*;
import java.io.IOException;

public class ProformaSalesInvoiceReport {
    WindowsDriver driver;
    AppLogin login=new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=login.launchSingleUserApp();
        login.singleUserLogin();
    }

    @Test
    public void pfSalesReport() throws IOException, ParseException, InterruptedException, AWTException {
        ProformaSalesReportCode salesReport=new ProformaSalesReportCode(driver);
        salesReport.proformaSales();
    }

    @AfterTest
    public void afterTest(){
        login.logout();
    }

}

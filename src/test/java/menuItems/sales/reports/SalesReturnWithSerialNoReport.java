package menuItems.sales.reports;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.reports.SalesReturnsWithSerialNoReportCode;
import java.io.IOException;

public class SalesReturnWithSerialNoReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void salesReturnWithSerialNo() throws InterruptedException {
        SalesReturnsWithSerialNoReportCode reportCode=new SalesReturnsWithSerialNoReportCode(driver);
        reportCode.salesReturnwithSerialNoReport();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }

}

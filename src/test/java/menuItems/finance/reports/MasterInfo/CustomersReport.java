package menuItems.finance.reports.MasterInfo;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.MasterInfo.CustomersReportCode;
import java.awt.*;
import java.io.IOException;

public class CustomersReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void customerReport() throws  InterruptedException, AWTException {
        CustomersReportCode customersReportCode=new CustomersReportCode(driver);
        customersReportCode.customerReport();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

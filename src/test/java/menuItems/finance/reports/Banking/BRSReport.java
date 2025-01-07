package menuItems.finance.reports.Banking;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.Banking.BRSReportCode;

import java.awt.*;
import java.io.IOException;

public class BRSReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void brsReport() throws  InterruptedException, AWTException {
        BRSReportCode brsReportCode=new BRSReportCode(driver);
        brsReportCode.brsReport();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}

package menuItems.audit.reports;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.audit.reports.LoginStatusReportCode;
import java.awt.*;
import java.io.IOException;

public class LoginStatusReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void loginStatus() throws  InterruptedException, AWTException {
        LoginStatusReportCode statusReportCode=new LoginStatusReportCode(driver);
        statusReportCode.loginStatus();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}

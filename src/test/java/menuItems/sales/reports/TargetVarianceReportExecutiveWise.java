package menuItems.sales.reports;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.reports.TargetVarianceReportsExecWiseReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TargetVarianceReportExecutiveWise {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/Sales/Transactions/targetVariance.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void salesTarget() throws IOException, ParseException, InterruptedException {
        TargetVarianceReportsExecWiseReportCode varianceReportsExecWise = new TargetVarianceReportsExecWiseReportCode(driver, file);
        varianceReportsExecWise.TargetVarianceReport();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}

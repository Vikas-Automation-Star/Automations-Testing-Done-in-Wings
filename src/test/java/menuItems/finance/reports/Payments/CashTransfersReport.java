package menuItems.finance.reports.Payments;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.payments.CashTransfersReportCode;
import java.awt.*;
import java.io.IOException;

public class CashTransfersReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void cashTransfer() throws  InterruptedException, AWTException {
        CashTransfersReportCode transfersReportCode=new CashTransfersReportCode(driver);
        transfersReportCode.cashTransfer();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }

}

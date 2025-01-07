package menuItems.finance.reports.Payments;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.payments.PaymentToPartiesReportCode;

import java.awt.*;
import java.io.IOException;

public class PaymentToPartiesReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void paymentToParty() throws  InterruptedException, AWTException {
        PaymentToPartiesReportCode partiesReportCode=new PaymentToPartiesReportCode(driver);
        partiesReportCode.paymentToParty();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}

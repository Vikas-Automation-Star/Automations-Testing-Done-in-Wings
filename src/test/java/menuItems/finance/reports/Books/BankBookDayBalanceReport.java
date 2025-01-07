package menuItems.finance.reports.Books;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.Books.BankBookDayBalanceReportCode;
import java.awt.*;
import java.io.IOException;

public class BankBookDayBalanceReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }
    @Test
    public void bankBookDayBalanceReport() throws InterruptedException, AWTException {
        BankBookDayBalanceReportCode bookDayBalanceReportCode=new BankBookDayBalanceReportCode(driver);
        bookDayBalanceReportCode.bankBookDayBalance();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}

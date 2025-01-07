package menuItems.finance.reports.Books;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.Books.CashBookDayBalanceReportCode;
import java.awt.*;
import java.io.IOException;

public class CashBookDayBalanceReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }
    @Test
    public void cashBookDayBalanceReport() throws InterruptedException, AWTException {
        CashBookDayBalanceReportCode bookDayBalanceReportCode=new CashBookDayBalanceReportCode(driver);
        bookDayBalanceReportCode.cashBookDayBalance();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}

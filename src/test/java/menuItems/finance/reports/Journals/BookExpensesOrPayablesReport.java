package menuItems.finance.reports.Journals;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.Journals.BookExpensesOrPayablesReportCode;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class BookExpensesOrPayablesReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void receivablesReport() throws IOException, ParseException, InterruptedException, AWTException {
        BookExpensesOrPayablesReportCode expensesOrPayablesReportCode = new BookExpensesOrPayablesReportCode(driver);
        expensesOrPayablesReportCode.payablesReport();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

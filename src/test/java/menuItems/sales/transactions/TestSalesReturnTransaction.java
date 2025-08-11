package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesReturns;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestSalesReturnTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/480463 - Sales Returns-AC.xlsx";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver= appLogin.login();
    }

    @Test
    public void salesInvoiceTransaction() throws IOException, InterruptedException, ParseException, AWTException {
        SalesReturns salesReturns = new SalesReturns(driver, dataFile);
        salesReturns.salesReturns("SI 15");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
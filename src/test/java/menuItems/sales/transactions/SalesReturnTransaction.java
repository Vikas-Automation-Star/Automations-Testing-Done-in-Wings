package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesReturns;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class SalesReturnTransaction {
    WindowsDriver driver;
    AppLogin login = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/salesReturns.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.launchSingleUserApp();
        login.singleUserLogin();
        Allure.step("Before Test Sales Return");
    }

    @Test
    public void salesReturns() throws IOException, ParseException, InterruptedException, AWTException {
        SalesReturns salesReturns = new SalesReturns(driver, dataFile);
        salesReturns.salesReturns();
    }

    @AfterTest
    public void afterTest() throws IOException {
        login.logout();
        Allure.step("After Test Sales Return");
    }
}
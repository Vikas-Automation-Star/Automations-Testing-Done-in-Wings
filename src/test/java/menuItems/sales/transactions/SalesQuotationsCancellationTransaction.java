package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesQuotationCancellaton;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class SalesQuotationsCancellationTransaction {
    WindowsDriver driver;
    AppLogin login = new AppLogin();
    String dataFile = "./src/main/resources/MenuItems/Sales/Transactions/salesQuotationCancellation.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.launchSingleUserApp();
        login.singleUserLogin();
        Allure.step("Before Test Sales Quotations Cancellation");
    }

    @Test
    public void salesQuoteCancel() throws InterruptedException, IOException, ParseException {
        SalesQuotationCancellaton cancellation = new SalesQuotationCancellaton(driver, dataFile);
        cancellation.salesQuotationCancelltion();
    }

    @AfterTest
    public void afterTest() throws IOException {
        login.logout();
        Allure.step("After Test Sales Quotations Cancellation");
    }
}

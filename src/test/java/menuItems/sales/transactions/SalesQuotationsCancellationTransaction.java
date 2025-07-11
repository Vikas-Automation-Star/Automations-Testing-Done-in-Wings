package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesQuotationCancellaton;
import com.wings.pages.sales.transactions.SalesQuotations;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class SalesQuotationsCancellationTransaction {
    WindowsDriver driver;
    AppLogin login = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/460554 - Sales Quotations Cancellation.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.login();
    }

    @Test
    public void salesQuotationCancellation() throws InterruptedException, IOException, ParseException, AWTException {
        SalesQuotationCancellaton cancellation = new SalesQuotationCancellaton(driver, dataFile);
//        SalesQuotations salesQuotations=new SalesQuotations(driver,dataFile);
        cancellation.salesQuotationCancelltion("SQ 7");
    }

    @AfterTest
    public void afterTest() throws IOException {
//        login.logout();
    }
}

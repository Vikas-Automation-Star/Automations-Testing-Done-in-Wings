package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesQuotationCancellaton;
import com.wings.pages.sales.transactions.SalesQuotations;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class SalesQuotationsCancellationTransaction {
    private static final Logger log = LoggerFactory.getLogger(SalesQuotationsCancellationTransaction.class);
    WindowsDriver driver;
    AppLogin login = new AppLogin();
    String file = "./src/main/resources/menuItems/Sales/Transactions/460553 - Sales Quotations-AC.xls";
    String dataFile1 = "./src/main/resources/menuItems/Sales/Transactions/460554 - Sales Quotations Cancellation.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.login();
    }

    @Test
    public void salesQuotationCancellation() throws InterruptedException, IOException, ParseException, AWTException {
        SalesQuotations salesQuotations=new SalesQuotations(driver,file);
        String quotationVoucher=salesQuotations.salesQuotation();

        login.logout();
        driver=login.login();

        SalesQuotationCancellaton cancellation = new SalesQuotationCancellaton(driver, dataFile1);
        cancellation.salesQuotationCancelltion(quotationVoucher);
    }

    @AfterTest
    public void afterTest() throws IOException {
        login.logout();
    }
}

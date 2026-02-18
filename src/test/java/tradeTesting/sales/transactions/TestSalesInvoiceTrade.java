package tradeTesting.sales.transactions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesInvoiceTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/sales/transactions/497188 - Sales Invoices-Trd_SI_8.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testSalesInvoiceTrade() throws IOException, ParseException, InterruptedException, AWTException {
        SalesInvoiceTrade salesInvoiceTrade=new SalesInvoiceTrade(driver,dataFile);
        salesInvoiceTrade.salesInvoiceTrade();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
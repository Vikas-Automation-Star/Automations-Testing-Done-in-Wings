package tradeTesting.sales.transactions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesReturnWithInvoiceReferenceTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/sales/transactions/495666 - Sales Return With Invoice Reference-Trd_SRWR_4_SI_7.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testSalesReturnWithInvoiceTrade() throws IOException, ParseException, InterruptedException, AWTException {
        SalesReturnWithInvoiceReferenceTrade invoiceReferenceTrade=new SalesReturnWithInvoiceReferenceTrade(driver,dataFile);
        invoiceReferenceTrade.salesReturnWinInvoiceRefTrade("SI 8");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
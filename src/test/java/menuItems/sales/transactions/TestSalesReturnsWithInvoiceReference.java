package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesInvoice;
import com.wings.pages.sales.transactions.SalesReturnWithInvoiceReference;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesReturnsWithInvoiceReference {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/480460 - Sales Invoices-AC.xlsx";
    String dataFile1 = "./src/main/resources/menuItems/Sales/Transactions/480464 - Sales Return with Invoice Reference-AC.xlsx";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver= appLogin.login();
    }

    @Test
    public void salesReturnsWithInvoiceReference() throws IOException, ParseException, InterruptedException, AWTException {
//        SalesInvoice salesInvoice=new SalesInvoice(driver,dataFile);
//        String salesI=salesInvoice.salesInvoice();
//
//        appLogin.logout();
//        driver= appLogin.login();

        SalesReturnWithInvoiceReference salesRWIR =new SalesReturnWithInvoiceReference(driver,dataFile1);
        salesRWIR.salesReturnWithInvoiceReference("SI 19");
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
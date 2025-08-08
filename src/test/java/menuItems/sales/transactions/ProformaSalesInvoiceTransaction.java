package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.ProformaSalesInvoice;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class ProformaSalesInvoiceTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/Sales/Transactions/475923 - Proforma Sales Invoices-AC.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();

    }

    @Test
    public void proformaSalesInvoice() throws IOException, ParseException, InterruptedException, AWTException {
        ProformaSalesInvoice sales = new ProformaSalesInvoice(driver, file);
        sales.proformaSalesInvoice();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
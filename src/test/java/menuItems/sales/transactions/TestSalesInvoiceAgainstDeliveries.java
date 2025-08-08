package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.Deliveries;
import com.wings.pages.sales.transactions.SalesInvoiceAgainstDeliveries;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesInvoiceAgainstDeliveries {

        WindowsDriver driver;
        AppLogin appLogin=new AppLogin();
        String dataFile1="./src/main/resources/menuItems/Sales/Transactions/476979 - Deliveries-AC.xls";
        String dataFile2 ="./src/main/resources/menuItems/Sales/Transactions/480462 - Sales Invoices against Deliveries-AC.xls";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver=appLogin.login();
        }

        @Test
        public void deliveriesAgainstOrders() throws IOException, ParseException, InterruptedException, AWTException {
            Deliveries deliveries=new Deliveries(driver,dataFile1);
            String deliveriesVoucher=deliveries.salesDeliveries();

            appLogin.logout();
            driver = appLogin.login();

            SalesInvoiceAgainstDeliveries salesInvoiceAgainstDeliveries=new SalesInvoiceAgainstDeliveries(driver,dataFile2);
            salesInvoiceAgainstDeliveries.salesInvoiceAgainstDeliveries(deliveriesVoucher);

        }

        @AfterTest
        public void afterTest() throws IOException {
            appLogin.logout();
        }
    }
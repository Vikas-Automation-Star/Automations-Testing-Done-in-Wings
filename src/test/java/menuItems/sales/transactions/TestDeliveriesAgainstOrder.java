package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.DeliveriesAgainstOrdersTransaction;
import com.wings.pages.sales.transactions.SalesInvoiceAgainstDeliveries;
import com.wings.pages.sales.transactions.SalesOrders;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestDeliveriesAgainstOrder {
        WindowsDriver driver;
        AppLogin appLogin=new AppLogin();
        Common common;
        String dataFile="./src/main/resources/menuItems/Sales/Transactions/salesEnquiry.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver=appLogin.login();
        }

        @Test
        public void deliveriesAgainstOrders() throws IOException, ParseException, InterruptedException, AWTException {
//            SalesOrders salesOrders=new SalesOrders(driver,dataFile);
//            String salesOrderVoucher=salesOrders.salesOrders();
//
//            appLogin.logout();
//            driver = appLogin.login();
//
            DeliveriesAgainstOrdersTransaction deliveriesAgainstOrdersTransaction=new DeliveriesAgainstOrdersTransaction(driver,dataFile);
            String deliveriesAgainstOrdersVoucher=deliveriesAgainstOrdersTransaction.deliveriesAgainstOrders("SO 15");

//            SalesInvoiceAgainstDeliveries salesInvoiceAgainstDeliveries=new SalesInvoiceAgainstDeliveries(driver,dataFile);
//            salesInvoiceAgainstDeliveries.salesInvoiceAgainstDeliveries("DELO 4");
        }

        @AfterTest
        public void afterTest() throws IOException {
//            appLogin.logout();
        }
    }
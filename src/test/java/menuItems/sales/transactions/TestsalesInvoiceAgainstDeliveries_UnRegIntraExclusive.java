package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.DeliveriesAgainstOrders_UnRegIntraExclusive;
import com.wings.pages.sales.transactions.SalesInvoiceAgainstDeliveries_UnRegIntraExclusive;
import com.wings.pages.sales.transactions.SalesOrder_UnRegIntraExclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestsalesInvoiceAgainstDeliveries_UnRegIntraExclusive {

        WindowsDriver driver;
        AppLogin appLogin=new AppLogin();
        String dataFile="./src/main/resources/menuItems/Sales/Transactions/deliveriesAgainstOrder_UnRegIntraExclusive.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver=appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
        }

        @Test
        public void siad_UnRegIntraExclusive() throws IOException, ParseException, InterruptedException, AWTException {
            SalesOrder_UnRegIntraExclusive unRegIntraExclusive=new SalesOrder_UnRegIntraExclusive(driver,dataFile);
            String []salesOrder=unRegIntraExclusive.intraExclusiveUnReg();
            System.out.println("first Voucher " +salesOrder[0]); //without space
            System.out.println("sec Voucher " +salesOrder[1]); //with space

            DeliveriesAgainstOrders_UnRegIntraExclusive regIntraExclusive=new DeliveriesAgainstOrders_UnRegIntraExclusive(driver,dataFile);
            String[] deliveriesAgainstOrders=regIntraExclusive.intraExclusiveUnRegDeliveries(salesOrder[1]);
            System.out.println("deliveriesAgainstOrder 1stV: "+deliveriesAgainstOrders[0]);
            System.out.println("deliveriesAgainstOrder 2ndV: "+deliveriesAgainstOrders[1]);

            SalesInvoiceAgainstDeliveries_UnRegIntraExclusive invoiceAgainstDeliveriesUnRegIntraExclusive=new SalesInvoiceAgainstDeliveries_UnRegIntraExclusive(driver,dataFile);
            invoiceAgainstDeliveriesUnRegIntraExclusive.UnRegIntraExclusiveSIAD(deliveriesAgainstOrders[1]);
//            invoiceAgainstDeliveriesUnRegIntraExclusive.UnRegIntraExclusiveSIAD("DELO 9");

        }

        @AfterTest
        public void afterTest() throws IOException {
            appLogin.logout();
        }
    }
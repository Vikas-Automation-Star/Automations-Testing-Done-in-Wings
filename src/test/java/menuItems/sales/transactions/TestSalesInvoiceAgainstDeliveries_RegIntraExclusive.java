package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.*;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesInvoiceAgainstDeliveries_RegIntraExclusive {

        WindowsDriver driver;
        AppLogin appLogin=new AppLogin();
        String dataFile="./src/main/resources/menuItems/Sales/Transactions/deliveriesAgainstOrder_RegIntraExclusive.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver=appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
        }

        @Test
        public void siad_RegIntraExclusive() throws IOException, ParseException, InterruptedException, AWTException {
            SalesOrder_RegIntraExclusive orderRegIntraExclusive=new SalesOrder_RegIntraExclusive(driver,dataFile);
            String[] salesOrder =orderRegIntraExclusive.intraExclusiveReg();
            System.out.println("first Voucher " +salesOrder[0]); //without space
            System.out.println("secVoucher " +salesOrder[1]); //with space

            DeliveriesAgainstOrders_RegIntraExclusive regIntraExclusive=new DeliveriesAgainstOrders_RegIntraExclusive(driver,dataFile);
            String[] deliveriesAgainstOrders=regIntraExclusive.intraExclusiveRegDeliveries(salesOrder[1]);
            System.out.println("deliveriesAgainstOrder 1stV: "+deliveriesAgainstOrders[0]);
            System.out.println("deliveriesAgainstOrder 2ndV: "+deliveriesAgainstOrders[1]);

//            invoiceAgainstDeliveriesRegIntraExclusive.RegIntraExclusiveSIAD("DELO 8");

        }

        @AfterTest
        public void afterTest() throws IOException {
//            appLogin.logout();
        }
    }
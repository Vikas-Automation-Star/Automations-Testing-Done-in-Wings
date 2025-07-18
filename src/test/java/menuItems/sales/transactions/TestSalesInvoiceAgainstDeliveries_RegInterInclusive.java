package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.DeliveriesAgainstOrders_RegInterInclusive;
import com.wings.pages.sales.transactions.SalesOrder_RegInterInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesInvoiceAgainstDeliveries_RegInterInclusive {

        WindowsDriver driver;
        AppLogin appLogin=new AppLogin();
        String dataFile="./src/main/resources/menuItems/Sales/Transactions/deliveriesAgainstOrder_RegInterInclusive.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver=appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
        }

        @Test
        public void siad_RegInterInclusive() throws IOException, ParseException, InterruptedException, AWTException {
            SalesOrder_RegInterInclusive orderRegInterInclusive=new SalesOrder_RegInterInclusive(driver,dataFile);
            String []salesOrder=orderRegInterInclusive.interInclusiveReg();
            System.out.println("salesOrder firstVoucher " +salesOrder[0]); //without space
            System.out.println("salesOrder secVoucher " +salesOrder[1]); //with space

            DeliveriesAgainstOrders_RegInterInclusive againstOrders_regInterInclusive=new DeliveriesAgainstOrders_RegInterInclusive(driver,dataFile);
            String []deliveriesAgainstOrders=againstOrders_regInterInclusive.interInclusiveRegDeliveries(salesOrder[1]);
            System.out.println("deliveriesAgainstOrder 1stV: "+deliveriesAgainstOrders[0]);
            System.out.println("deliveriesAgainstOrder 2ndV: "+deliveriesAgainstOrders[1]);



        }

        @AfterTest
        public void afterTest() throws IOException {
            appLogin.logout();
        }
    }
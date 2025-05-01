package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.DeliveriesAgainstOrders_UnRegInterInclusive;
import com.wings.pages.sales.transactions.SRWIR_SIADUnRegInterInclusive;
import com.wings.pages.sales.transactions.SalesInvoiceAgainstDeliveries_UnRegInterInclusive;
import com.wings.pages.sales.transactions.SalesOrder_UnRegInterInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesReturnWIR_SIADUnRegInterInclusive {

        WindowsDriver driver;
        AppLogin appLogin=new AppLogin();
        String dataFile="./src/main/resources/menuItems/Sales/Transactions/deliveriesAgainstOrder_UnRegInterInclusive.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver=appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
        }

        @Test
        public void siad_UnRegInterInclusiveWithInvoice() throws IOException, ParseException, InterruptedException, AWTException {
            SalesOrder_UnRegInterInclusive unRegInterInclusive =new SalesOrder_UnRegInterInclusive(driver,dataFile);
//            String []salesOrder=unRegInterInclusive.interInclusiveUnReg();
//            System.out.println("first Voucher " +salesOrder[0]); //without space
//            System.out.println("sec Voucher " +salesOrder[1]); //with space

            DeliveriesAgainstOrders_UnRegInterInclusive againstOrdersUnRegInterInclusive=new DeliveriesAgainstOrders_UnRegInterInclusive(driver,dataFile);
            String []deliveriesAgainstOrders=againstOrdersUnRegInterInclusive.interInclusiveUnRegDeliveries(unRegInterInclusive.interInclusiveUnReg()[1]);
//            System.out.println("deliveriesAgainstOrder 1stV: "+deliveriesAgainstOrders[0]);
//            System.out.println("deliveriesAgainstOrder 2ndV: "+deliveriesAgainstOrders[1]);


            SalesInvoiceAgainstDeliveries_UnRegInterInclusive againstDeliveries_unRegInterInclusive=new SalesInvoiceAgainstDeliveries_UnRegInterInclusive(driver,dataFile);
            String []againstDeliveries=againstDeliveries_unRegInterInclusive.UnRegInterInclusiveSIAD(deliveriesAgainstOrders[1]);

            SRWIR_SIADUnRegInterInclusive srwirSiadUnRegInterInclusive=new SRWIR_SIADUnRegInterInclusive(driver,dataFile);
            srwirSiadUnRegInterInclusive.unRegInclusiveInterInvoiceRef_SIAD(againstDeliveries[1]);
//            srwirSiadUnRegInterInclusive.unRegInclusiveInterInvoiceRef_SIAD("SIAD 6");

        }

        @AfterTest
        public void afterTest() throws IOException {
//            appLogin.logout();
        }
    }
package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.DeliveriesAgainstOrders_UnRegIntraExclusive;
import com.wings.pages.sales.transactions.SRWIR_SIADUnRegIntraExclusive;
import com.wings.pages.sales.transactions.SalesInvoiceAgainstDeliveries_UnRegIntraExclusive;
import com.wings.pages.sales.transactions.SalesOrder_UnRegIntraExclusive;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesReturnWIR_SIADUnRegIntraExclusive {

        WindowsDriver driver;
        AppLogin appLogin=new AppLogin();
        Common common;
        String dataFile="./src/main/resources/menuItems/Sales/Transactions/deliveriesAgainstOrder_UnRegIntraExclusive.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            common=new Common(driver);
            driver=appLogin.launchSingleUserApp();
            appLogin.singleUserLogin(common.getData(dataFile,"SalesOrder","userName"),common.getData(dataFile,"SalesOrder","password"));
        }

        @Test
        public void siad_UnRegIntraExclusivewithInvocie() throws IOException, ParseException, InterruptedException, AWTException {
            SalesOrder_UnRegIntraExclusive unRegIntraExclusive=new SalesOrder_UnRegIntraExclusive(driver,dataFile);
            String []salesOrder=unRegIntraExclusive.intraExclusiveUnReg();

            appLogin.logout();
            driver = appLogin.launchSingleUserApp();
            appLogin.singleUserLogin(common.getData(dataFile,"SalesOrder","userName"),common.getData(dataFile,"SalesOrder","password"));

            DeliveriesAgainstOrders_UnRegIntraExclusive regIntraExclusive=new DeliveriesAgainstOrders_UnRegIntraExclusive(driver,dataFile);
            String[] deliveriesAgainstOrders=regIntraExclusive.intraExclusiveUnRegDeliveries(salesOrder[1]);

            appLogin.logout();
            driver = appLogin.launchSingleUserApp();
            appLogin.singleUserLogin(common.getData(dataFile,"SalesOrder","userName"),common.getData(dataFile,"SalesOrder","password"));

            SalesInvoiceAgainstDeliveries_UnRegIntraExclusive invoiceAgainstDeliveriesUnRegIntraExclusive=new SalesInvoiceAgainstDeliveries_UnRegIntraExclusive(driver,dataFile);
            String []deliveries=invoiceAgainstDeliveriesUnRegIntraExclusive.UnRegIntraExclusiveSIAD(deliveriesAgainstOrders[1]);
//            invoiceAgainstDeliveriesUnRegIntraExclusive.UnRegIntraExclusiveSIAD("DELO 9");

            appLogin.logout();
            driver = appLogin.launchSingleUserApp();
            appLogin.singleUserLogin(common.getData(dataFile,"SalesOrder","userName"),common.getData(dataFile,"SalesOrder","password"));

            SRWIR_SIADUnRegIntraExclusive siadUnRegIntraExclusive=new SRWIR_SIADUnRegIntraExclusive(driver,dataFile);
            siadUnRegIntraExclusive.unRegExclusiveIntraInvoiceRef_SIAD(deliveries[1]);

        }

        @AfterTest
        public void afterTest() throws IOException {
//            appLogin.logout();
        }
    }
package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesInvoiceAgainstOrders_UnRegInterInclusive;
import com.wings.pages.sales.transactions.SalesOrder_UnRegInterInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesInvoiceAgainstOrders_UnRegInterInclusive {

        WindowsDriver driver;
        AppLogin appLogin=new AppLogin();
        String dataFile="./src/main/resources/menuItems/Sales/Transactions/deliveriesAgainstOrder_UnRegInterInclusive.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver=appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
        }

        @Test
        public void siad_UnRegInterInclusive() throws IOException, ParseException, InterruptedException, AWTException {
            SalesOrder_UnRegInterInclusive unRegInterInclusive =new SalesOrder_UnRegInterInclusive(driver,dataFile);
            String []salesOrder=unRegInterInclusive.interInclusiveUnReg();
            System.out.println("first Voucher " +salesOrder[0]); //without space
            System.out.println("sec Voucher " +salesOrder[1]); //with space

            SalesInvoiceAgainstOrders_UnRegInterInclusive ordersUnRegInterInclusive=new SalesInvoiceAgainstOrders_UnRegInterInclusive(driver,dataFile);
            ordersUnRegInterInclusive.interInclusiveUnRegSIAO(salesOrder[1]);

        }

        @AfterTest
        public void afterTest() throws IOException {
//            appLogin.logout();
        }
    }
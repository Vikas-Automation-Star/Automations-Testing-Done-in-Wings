package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.DeliveriesAgainstOrders_RegIntraExclusive;
import com.wings.pages.sales.transactions.SalesOrder_RegIntraExclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestDeliveriesAgainstOrder_RegIntraExclusive {

        WindowsDriver driver;
        AppLogin appLogin=new AppLogin();
        String dataFile="./src/main/resources/menuItems/Sales/Transactions/deliveriesAgainstOrder_RegIntraExclusive.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver=appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
        }

        @Test
        public void delo_regIntraExclusive() throws IOException, ParseException, InterruptedException, AWTException {
            SalesOrder_RegIntraExclusive orderRegIntraExclusive=new SalesOrder_RegIntraExclusive(driver,dataFile);
            String[] salesOrder =orderRegIntraExclusive.intraExclusiveReg();
            System.out.println("first Voucher " +salesOrder[0]); //without space
            System.out.println("secVoucher " +salesOrder[1]); //with space

            DeliveriesAgainstOrders_RegIntraExclusive regIntraExclusive=new DeliveriesAgainstOrders_RegIntraExclusive(driver,dataFile);
            regIntraExclusive.intraExclusiveRegDeliveries(salesOrder[1]);
//            againstOrders_regInterInclusive.interInclusiveRegDeliveries("SO 11");

        }

        @AfterTest
        public void afterTest() throws IOException {
            appLogin.logout();
        }
    }
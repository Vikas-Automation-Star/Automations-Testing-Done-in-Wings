package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesInvoiceFQ_UnRegIntraExclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSIFQ_UnRegIntraExclusive {
        WindowsDriver driver;
        AppLogin appLogin=new AppLogin();
        String dataFile="./src/main/resources/menuItems/Sales/Transactions/SalesInvoiceFQ_UnRegIntraExclusive.json";

        @BeforeTest
        public void beforeTest() throws IOException, ParseException, InterruptedException {
            driver=appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
        }

        @Test
        public void UnRegExclusiveTCSGSTFreeQuantity() throws IOException, ParseException, InterruptedException, AWTException {
            SalesInvoiceFQ_UnRegIntraExclusive salesInvoiceFQUnRegIntraExclusive=new SalesInvoiceFQ_UnRegIntraExclusive(driver,dataFile);
            salesInvoiceFQUnRegIntraExclusive.intraStateExclusiveTCSGST_FreeQty_UnReg();
        }

        @AfterTest
        public void afterTest() throws IOException {
            appLogin.logout();
        }
    }
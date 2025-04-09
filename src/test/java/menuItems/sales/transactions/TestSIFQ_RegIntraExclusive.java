package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesInvoiceFQ_RegIntraExclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSIFQ_RegIntraExclusive {
        WindowsDriver driver;
        AppLogin appLogin=new AppLogin();
        String dataFile="./src/main/resources/menuItems/Sales/Transactions/SalesInvoiceFQ_RegExclusiveIntra.json";

        @BeforeTest
        public void beforeTest() throws IOException, ParseException, InterruptedException {
            driver=appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
        }

        @Test
        public void InclusiveTCSGSTFreeQuantity() throws IOException, ParseException, InterruptedException, AWTException {
            SalesInvoiceFQ_RegIntraExclusive salesInvoiceFQRegIntraExclusive=new SalesInvoiceFQ_RegIntraExclusive(driver,dataFile);
            salesInvoiceFQRegIntraExclusive.intraStateExclusiveTCSGST_FreeQty();
        }

        @AfterTest
        public void afterTest() throws IOException {
            appLogin.logout();
        }
    }
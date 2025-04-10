package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesInvoiceFQ_RegIntraExclusive;
import com.wings.pages.sales.transactions.SalesReturnFreeQuantity_RegIntraExclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesReturnFQ_RegIntraExclusive {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/menuItems/Sales/Transactions/SalesReturnFQRegIntraExclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }
    @Test
    public void salesReturnRegIntraExclusive() throws IOException, ParseException, InterruptedException, AWTException {
        SalesInvoiceFQ_RegIntraExclusive exclusiveTCSGST_freeQty=new SalesInvoiceFQ_RegIntraExclusive(driver,dataFile);
        SalesReturnFreeQuantity_RegIntraExclusive returnFreeQuantityRegIntraExclusive=new SalesReturnFreeQuantity_RegIntraExclusive(driver,dataFile);
        returnFreeQuantityRegIntraExclusive.regIntraExclusiveSalesReturn(exclusiveTCSGST_freeQty.intraStateExclusiveTCSGST_FreeQty());
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
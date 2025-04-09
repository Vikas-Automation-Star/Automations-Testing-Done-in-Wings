package menuItems.sales.transactions;
//1hr 40mnts
import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesInvoiceFQ_UnRegInterInclusive;
import com.wings.pages.sales.transactions.SalesReturnFreeQuantity_UnRegInterInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesReturnFQ_UnRegInterInclusive {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/menuItems/Sales/Transactions/SalesReturnFQUnRegInterInclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void salesReturnUnRegInterInclusive() throws IOException, ParseException, InterruptedException, AWTException {
        SalesInvoiceFQ_UnRegInterInclusive salesInvoiceFQUnRegInterInclusive=new SalesInvoiceFQ_UnRegInterInclusive(driver,dataFile);
        SalesReturnFreeQuantity_UnRegInterInclusive unRegInterInclusive=new SalesReturnFreeQuantity_UnRegInterInclusive(driver,dataFile);
        unRegInterInclusive.unRegInterInclusive(salesInvoiceFQUnRegInterInclusive.interStateInclusiveTCSGST_FreeQty());
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
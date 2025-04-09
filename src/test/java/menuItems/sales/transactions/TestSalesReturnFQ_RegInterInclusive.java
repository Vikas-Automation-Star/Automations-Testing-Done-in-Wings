package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesInvoiceFQ_RegInterInclusive;
import com.wings.pages.sales.transactions.SalesReturnFreeQuantity_RegInterInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesReturnFQ_RegInterInclusive {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/menuItems/Sales/Transactions/SalesReturnFQRegInterInclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }
    @Test
    public void salesReturnRegInterInclusive() throws IOException, ParseException, InterruptedException, AWTException {
        SalesInvoiceFQ_RegInterInclusive fq_regInterInclusive=new SalesInvoiceFQ_RegInterInclusive(driver,dataFile);
        SalesReturnFreeQuantity_RegInterInclusive regInterInclusive=new SalesReturnFreeQuantity_RegInterInclusive(driver,dataFile);
        regInterInclusive.regInterInclusiveSalesReturn(fq_regInterInclusive.interInclusiveTCSGST_FreeQty());
//        regInterInclusive.regInterInclusiveSalesReturn("si21");


    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

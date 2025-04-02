package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesOrder_UnRegIntraExclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesOrder_UnRegIntraExclusive {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/menuItems/Sales/Transactions/SalesOrderUnRegIntraExclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void salesOrderUnRegIntraExclusive() throws IOException, ParseException, InterruptedException, AWTException {
        SalesOrder_UnRegIntraExclusive unRegIntraExclusive=new SalesOrder_UnRegIntraExclusive(driver,dataFile);
        unRegIntraExclusive.intraExclusiveUnReg();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesOrder_UnRegInterInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesOrder_UnRegInterInclusive {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/menuItems/Sales/Transactions/SalesOrderUnRegInterInclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void salesOrderUnRegInterInclusive() throws IOException, ParseException, InterruptedException, AWTException {
        SalesOrder_UnRegInterInclusive unRegInterInclusive=new SalesOrder_UnRegInterInclusive(driver,dataFile);
        unRegInterInclusive.interInclusiveUnReg();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesOrderCancellation;
import com.wings.pages.sales.transactions.SalesOrders;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class SalesOrderCancellationTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/salesEnquiry.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(dataFile,"salesEnquiry","userName"),common.getData(dataFile,"salesEnquiry","password"));
    }

    @Test
    public void salesOrderCancellation() throws IOException, InterruptedException, ParseException, AWTException {
        SalesOrders salesOrders=new SalesOrders(driver, dataFile);
        String salesOrderVoucher= salesOrders.salesOrders();

        appLogin.logout();
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(dataFile,"salesEnquiry","userName"),common.getData(dataFile,"salesEnquiry","password"));

        SalesOrderCancellation orderCancellation = new SalesOrderCancellation(driver, dataFile);
        orderCancellation.salesOrderCancellations(salesOrderVoucher);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
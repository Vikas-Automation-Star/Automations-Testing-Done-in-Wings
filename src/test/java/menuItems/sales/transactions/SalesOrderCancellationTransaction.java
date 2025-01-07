package menuItems.sales.transactions;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesOrderCancellation;
import java.io.IOException;

public class SalesOrderCancellationTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/Sales/Transactions/salesOrderCancellation.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test Sales Order Cancellation");
    }

    @Test
    public void salesOrderCancellation() throws IOException, InterruptedException, ParseException {
        SalesOrderCancellation orderCancellation=new SalesOrderCancellation(driver,file);
        orderCancellation.salesOrderCancellations();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
        Allure.step("After Test Sales Order Cancellation");
    }
}
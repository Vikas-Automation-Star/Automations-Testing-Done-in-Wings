package menuItems.sales.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesOrder;
import java.io.IOException;

public class SalesOrderTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file="./src/main/resources/menuItems/Sales/Transactions/salesOrder.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void salesOrder() throws IOException, ParseException, InterruptedException {
        SalesOrder salesOrder=new SalesOrder(driver,file);
        salesOrder.salesOrder();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

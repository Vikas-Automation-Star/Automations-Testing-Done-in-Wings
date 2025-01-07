package menuItems.sales.transactions;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.DeliveriesAgainstOrders;
import java.io.IOException;

public class DeliveriesAgainstOrdersTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/menuItems/Sales/Transactions/deliveriesAgainstOrders.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test Deliveries Against Orders");
    }

    @Test
    public void deliveriesAgainstOrders() throws IOException, InterruptedException, ParseException {
        DeliveriesAgainstOrders deliveriesAgainstOrders=new DeliveriesAgainstOrders(driver,dataFile);
        deliveriesAgainstOrders.deliveriesAgainstOrders();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
        Allure.step("After Test Deliveries Against Orders");
    }
}
package menuItems.sales.masters;

import org.testng.annotations.AfterTest;
import com.wings.pages.sales.masters.Customer;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;

import java.awt.*;
import java.io.IOException;

public class CustomerMaster {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/Sales/Masters/customer.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void customer() throws InterruptedException, IOException, ParseException, AWTException {
        Customer customer = new Customer(driver, file);
        customer.newCustomer();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
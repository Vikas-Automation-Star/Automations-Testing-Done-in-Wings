package menuItems.sales.masters;

import org.testng.annotations.AfterTest;
import com.wings.pages.sales.masters.TypeOfCustomer;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;

import java.awt.*;
import java.io.IOException;

public class TypeOfCustomerMaster {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/MenuItems/Sales/Masters/typeOfCustomer.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void newtypeofCust() throws InterruptedException, IOException, ParseException, AWTException {
        TypeOfCustomer customer = new TypeOfCustomer(driver, dataFile);
        customer.typecust();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}


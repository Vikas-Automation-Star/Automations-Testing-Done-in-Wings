package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesEnquiryCancellation;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class SalesEnquiryCancellationTransaction {
    WindowsDriver driver;
    AppLogin login = new AppLogin();
    String dataFile = "./src/main/resources/MenuItems/Sales/Transactions/salesEnquiryCancellation.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.launchSingleUserApp();
        login.singleUserLogin();
        Allure.step("Before Test Sales Enquiry Cancellation");
    }

    @Test
    public void salesEnquiryCancellation() throws IOException, ParseException, InterruptedException, AWTException {
        SalesEnquiryCancellation cancellation = new SalesEnquiryCancellation(driver, dataFile);
        cancellation.enquiryCancel();
    }

    @AfterTest
    public void afterTest() throws IOException {
        login.logout();
        Allure.step("Before Test Sales Enquiry Cancellation");
    }
}
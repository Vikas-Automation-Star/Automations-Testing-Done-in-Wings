package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesEnquiry;
import com.wings.pages.sales.transactions.SalesEnquiryCancellation;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesEnquiryCancellation {
    WindowsDriver driver;
    AppLogin login = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/salesEnquiry.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.launchSingleUserApp();
        login.singleUserLogin();
    }

    @Test
    public void salesEnquiryCancellation() throws IOException, ParseException, InterruptedException, AWTException {
        SalesEnquiryCancellation cancellation = new SalesEnquiryCancellation(driver, dataFile);
        SalesEnquiry salesEnquiry=new SalesEnquiry(driver,dataFile);
        cancellation.salesEnquiryCancellation(salesEnquiry.salesEnquiry());
//        cancellation.salesEnquiryCancellation("SE 3");

    }


    @AfterTest
    public void afterTest() throws IOException {
        login.logout();
    }
}
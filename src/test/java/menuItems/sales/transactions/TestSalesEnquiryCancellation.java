package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesEnquiry;
import com.wings.pages.sales.transactions.SalesEnquiryCancellation;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesEnquiryCancellation {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/460469 - Sales Enquiries Cancellation-AC.xlsx";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void salesEnquiryCancellation() throws IOException, ParseException, InterruptedException, AWTException {
//        SalesEnquiry salesEnquiry=new SalesEnquiry(driver,dataFile);
//        String se=salesEnquiry.salesEnquiry();
        SalesEnquiryCancellation cancellation = new SalesEnquiryCancellation(driver, dataFile);
        cancellation.salesEnquiryCancellation("SE 15");
//        cancellation.salesEnquiryCancellation("SE 9");
    }


    @AfterTest
    public void afterTest() throws IOException {
//        login.logout();
    }
}
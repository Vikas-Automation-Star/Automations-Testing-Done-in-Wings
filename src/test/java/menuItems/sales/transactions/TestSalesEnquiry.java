package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesEnquiry;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesEnquiry {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/Sales/Transactions/460472_SE 17.xlsx";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void SalesEnquiryTransaction() throws IOException, InterruptedException, ParseException, AWTException {
        SalesEnquiry sales = new SalesEnquiry(driver, file);
        sales.salesEnquiry();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }

}
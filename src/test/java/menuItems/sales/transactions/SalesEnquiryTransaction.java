package menuItems.sales.transactions;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesEnquiry;
import java.io.IOException;

public class SalesEnquiryTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/Sales/Transactions/salesEnquiry.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test Sales Enquiry");
    }

    @Test
    public void SalesEnquiryTransaction() throws IOException, InterruptedException, ParseException {
        SalesEnquiry sales=new SalesEnquiry(driver,file);
        sales.salesEnquiry();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
        Allure.step("After Test Sales Enquiry");
    }
}
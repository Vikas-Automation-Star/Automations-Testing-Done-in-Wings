package menuItems.sales.transactions;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesQuotationAgainstEnquiry;

import java.io.IOException;

public class SalesQuotationAgainstEnquiryTransaction {
    WindowsDriver driver;
    AppLogin login=new AppLogin();
    String dataFile="./src/main/resources/MenuItems/Sales/Transactions/salesQuotationAgnstEnq.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=login.launchSingleUserApp();
        login.singleUserLogin();
        Allure.step("Before Test Sales Quotations against Enquiry");
    }

    @Test
    public void salesQuotationAgainstEnquiry() throws IOException, ParseException, InterruptedException {
        SalesQuotationAgainstEnquiry agnstEnquiry=new SalesQuotationAgainstEnquiry(driver,dataFile);
        agnstEnquiry.quotationAgainstEnquiry();
    }

    @AfterTest
    public void afterTest(){
        login.logout();
        Allure.step("After Test Sales Quotations against Enquiry");
    }
}

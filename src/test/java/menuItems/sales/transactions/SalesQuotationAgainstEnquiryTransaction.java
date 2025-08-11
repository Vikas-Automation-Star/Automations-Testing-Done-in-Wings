package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesEnquiry;
import com.wings.pages.sales.transactions.SalesQuotationAgainstEnquiry;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class SalesQuotationAgainstEnquiryTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/460472 - Sales Enquiries-AC.xlsx";
    String dataFile1 = "./src/main/resources/menuItems/Sales/Transactions/460567 - Sales Quotations against Enquiries-AC.xlsx";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void salesQuotationAgainstEnquiry() throws IOException, ParseException, InterruptedException, AWTException {
        SalesEnquiry salesEnquiry=new SalesEnquiry(driver,dataFile);
        String se=salesEnquiry.salesEnquiries();
        appLogin.logout();
        driver= appLogin.login();
        SalesQuotationAgainstEnquiry againstEnquiry = new SalesQuotationAgainstEnquiry(driver, dataFile1);
        againstEnquiry.quotationAgainstEnquiry(se);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}

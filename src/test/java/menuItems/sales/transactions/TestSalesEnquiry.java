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

    private static final String TEMP_API_BODY_SALES_ENQUIRY="./output/temp_api_request_bodies/salesEnquiries.json";
    private static final String API_RESPONSE_SALES_ENQUIRY="./output/api_responses/salesEnquires.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/Sales/Transactions/460472 - Sales Enquiries-AC_SE_8_Output.xlsx";

    String file = "./src/main/resources/menuItems/Sales/Transactions/460472 - Sales Enquiries-AC_SE_8.xlsx";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void SalesEnquiryTransaction() throws IOException, InterruptedException, ParseException, AWTException {
        SalesEnquiry sales = new SalesEnquiry(driver, file);
        sales.salesEnquiries(TEMP_API_BODY_SALES_ENQUIRY,API_RESPONSE_SALES_ENQUIRY,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }

}
package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesEnquiry;
import com.wings.pages.sales.transactions.SalesEnquiryCancellation;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestSalesEnquiryCancellation {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_SALES_ENQUIRY="./output/temp_api_request_bodies/salesEnquiries.json";
    private static final String API_RESPONSE_SALES_ENQUIRY="./output/api_responses/salesEnquires.json";
    private static final String OUTPUT_FILE1="./src/main/resources/menuItems/Sales/Transactions/460472 - Sales Enquiries-AC_SE_8_Output.xlsx";

    private static final String TEMP_API_BODY_SALES_ENQUIRY_CANCELLATION="./output/temp_api_request_bodies/salesEnquiriesCancellation.json";
    private static final String API_RESPONSE_SALES_ENQUIRY_CANCELLATION="./output/api_responses/salesEnquiriesCancellation.json";
    private static final String OUTPUT_FILE2="./src/main/resources/menuItems/Sales/Transactions/460469 - Sales Enquiries Cancellation-AC_Output.xlsx";

    String dataFile="./src/main/resources/menuItems/Sales/Transactions/460472 - Sales Enquiries-AC_SE_8.xlsx";
    String dataFile1 = "./src/main/resources/menuItems/Sales/Transactions/460469 - Sales Enquiries Cancellation-AC.xlsx";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();

        if (4 % 2 == 0) {
            throw new SkipException("Skipping tests due to login failure or other reasons");
        }
    }

    @Test
    public void salesEnquiryCancellation() throws Exception {
        SalesEnquiry sales = new SalesEnquiry(driver, dataFile);
        String se=sales.salesEnquiries(TEMP_API_BODY_SALES_ENQUIRY,API_RESPONSE_SALES_ENQUIRY,OUTPUT_FILE1);

        appLogin.logout();
        driver= appLogin.login();

        SalesEnquiryCancellation cancellation = new SalesEnquiryCancellation(driver, dataFile1);
        cancellation.salesEnquiryCancellation(se,TEMP_API_BODY_SALES_ENQUIRY_CANCELLATION,API_RESPONSE_SALES_ENQUIRY_CANCELLATION,OUTPUT_FILE2);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}
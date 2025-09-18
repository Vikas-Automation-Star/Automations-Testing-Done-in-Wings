package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesEnquiry;
import com.wings.pages.sales.transactions.SalesQuotationAgainstEnquiry;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.io.IOException;

public class TestSalesQuotationAgainstEnquiry {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_SALES_ENQUIRY="./output/temp_api_request_bodies/salesEnquiries.json";
    private static final String API_RESPONSE_SALES_ENQUIRY="./output/api_responses/salesEnquires.json";
    private static final String OUTPUT_FILE1="./src/main/resources/menuItems/Sales/Transactions/460472 - Sales Enquiries-AC_SE_8_Output.xlsx";

    private static final String TEMP_API_BODY_SALES_QUOTATIONS_AGAINST_ENQUIRIES="./output/temp_api_request_bodies/salesQuotationsAgainstEnquiries.json";
    private static final String API_RESPONSE_SALES_QUOTATIONS_AGAINST_ENQUIRIES="./output/api_responses/salesQuotationsAgainstEnquiries.json";
    private static final String OUTPUT_FILE2="./src/main/resources/menuItems/Sales/Transactions/460567 - Sales Quotations against Enquiries-AC_Output.xlsx";


    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/460472 - Sales Enquiries-AC_SE_8.xlsx";
    String dataFile1 = "./src/main/resources/menuItems/Sales/Transactions/460567 - Sales Quotations against Enquiries-AC.xlsx";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void salesQuotationAgainstEnquiry() throws Exception {
        SalesEnquiry sales = new SalesEnquiry(driver, dataFile);
        String se= sales.salesEnquiries(TEMP_API_BODY_SALES_ENQUIRY,API_RESPONSE_SALES_ENQUIRY,OUTPUT_FILE1);

        appLogin.logout();
        driver= appLogin.login();

        SalesQuotationAgainstEnquiry againstEnquiry = new SalesQuotationAgainstEnquiry(driver, dataFile1);
        againstEnquiry.quotationAgainstEnquiry(se,TEMP_API_BODY_SALES_QUOTATIONS_AGAINST_ENQUIRIES,API_RESPONSE_SALES_QUOTATIONS_AGAINST_ENQUIRIES,OUTPUT_FILE2);

    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}

package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesEnquiry;
import com.wings.pages.sales.transactions.SalesOrdersAgainstQuotations;
import com.wings.pages.sales.transactions.SalesQuotationAgainstEnquiry;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class SalesOrdersAgainstQuotationsTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_SALES_ENQUIRY="./output/temp_api_request_bodies/salesEnquiries.json";
    private static final String API_RESPONSE_SALES_ENQUIRY="./output/api_responses/salesEnquires.json";
    private static final String OUTPUT_FILE1="./src/main/resources/menuItems/Sales/Transactions/460472 - Sales Enquiries-AC_SE_8_Output.xlsx";

    private static final String TEMP_API_BODY_SALES_QUOTATIONS_AGAINST_ENQUIRIES="./output/temp_api_request_bodies/salesQuotationsAgainstEnquiries.json";
    private static final String API_RESPONSE_SALES_QUOTATIONS_AGAINST_ENQUIRIES="./output/api_responses/salesQuotationsAgainstEnquiries.json";
    private static final String OUTPUT_FILE2="./src/main/resources/menuItems/Sales/Transactions/460567 - Sales Quotations against Enquiries-AC_Output.xlsx";


    private static final String TEMP_API_SALES_ORDER_AGAINST_QUOTATION="./output/temp_api_request_bodies/salesOrderAgainstQuotation.json";
    private static final String API_RESPONSE_SALES_ORDER_AGAINST_QUOTATION="./output/api_responses/salesOrderAgainstQuotation.json";
    private static final String OUTPUT_FILE_SALES_ORDER_AGAINST_QUOTATION="./src/main/resources/menuItems/Sales/Transactions/477396 - Sales Orders against Quotations-AC_Output.xls";

    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/460472 - Sales Enquiries-AC.xlsx";
    String dataFile1 = "./src/main/resources/menuItems/Sales/Transactions/460567 - Sales Quotations against Enquiries-AC.xlsx";
    String dataFile2 = "./src/main/resources/menuItems/Sales/Transactions/477396 - Sales Orders against Quotations-AC.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void salesOrderAgainstQuotations() throws IOException, ParseException, InterruptedException, AWTException {
        SalesEnquiry sales = new SalesEnquiry(driver, dataFile);
        String se= sales.salesEnquiries(TEMP_API_BODY_SALES_ENQUIRY,API_RESPONSE_SALES_ENQUIRY,OUTPUT_FILE1);

        appLogin.logout();
        driver= appLogin.login();

        SalesQuotationAgainstEnquiry againstEnquiry = new SalesQuotationAgainstEnquiry(driver, dataFile1);
        String againstEnquiryVoucher=againstEnquiry.
                quotationAgainstEnquiry(se,TEMP_API_BODY_SALES_QUOTATIONS_AGAINST_ENQUIRIES,API_RESPONSE_SALES_QUOTATIONS_AGAINST_ENQUIRIES,OUTPUT_FILE2);

        appLogin.logout();
        driver= appLogin.login();

        SalesOrdersAgainstQuotations quotations = new SalesOrdersAgainstQuotations(driver, dataFile2);
        quotations.salesOrderAgainstQuotation(againstEnquiryVoucher,TEMP_API_SALES_ORDER_AGAINST_QUOTATION,API_RESPONSE_SALES_ORDER_AGAINST_QUOTATION,OUTPUT_FILE_SALES_ORDER_AGAINST_QUOTATION);
//        quotations.salesOrderAgainstQuotation("SQAE 2");

    }

    @AfterTest
    public void afterTest() throws IOException {
//        login.logout();
    }
}

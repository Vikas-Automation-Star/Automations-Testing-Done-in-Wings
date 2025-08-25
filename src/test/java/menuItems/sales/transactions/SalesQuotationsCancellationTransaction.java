package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesQuotationCancellaton;
import com.wings.pages.sales.transactions.SalesQuotations;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class SalesQuotationsCancellationTransaction {
    private static final Logger log = LoggerFactory.getLogger(SalesQuotationsCancellationTransaction.class);
    WindowsDriver driver;
    AppLogin login = new AppLogin();

    private static final String TEMP_API_SALES_QUOTATION="./output/temp_api_request_bodies/salesQuotation.json";
    private static final String API_RESPONSE_SALES_QUOTATION="./output/api_responses/salesQuotation.json";
    private static final String OUTPUT_FILE_QUOTATIONS="./src/main/resources/menuItems/Sales/Transactions/460553 - Sales Quotations-AC_Output.xls";

    private static final String TEMP_API_SALESQUOTATION_CANCELLATION="./output/temp_api_request_bodies/salesQuotationCancellation.json";
    private static final String API_RESPONSE_SALESQUOTATION_CANCELLATION="./output/api_responses/salesQuotationCancellation.json";
    private static final String OUTPUT_FILE_QUOTATION_CANCELLATION="./src/main/resources/menuItems/Sales/Transactions/460554 - Sales Quotations Cancellation_Output.xls";

    String file = "./src/main/resources/menuItems/Sales/Transactions/460553 - Sales Quotations-AC.xls";
    String dataFile1 = "./src/main/resources/menuItems/Sales/Transactions/460554 - Sales Quotations Cancellation.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.login();
    }

    @Test
    public void salesQuotationCancellation() throws InterruptedException, IOException, ParseException, AWTException {
        SalesQuotations salesQuotations=new SalesQuotations(driver,file);
        String quotationVoucher=salesQuotations.salesQuotation(TEMP_API_SALES_QUOTATION,API_RESPONSE_SALES_QUOTATION,OUTPUT_FILE_QUOTATIONS);

        login.logout();
        driver=login.login();

        SalesQuotationCancellaton cancellation = new SalesQuotationCancellaton(driver, dataFile1);
        cancellation.salesQuotationCancelltion(quotationVoucher,TEMP_API_SALESQUOTATION_CANCELLATION,API_RESPONSE_SALESQUOTATION_CANCELLATION,OUTPUT_FILE_QUOTATION_CANCELLATION);
    }

    @AfterTest
    public void afterTest() throws IOException {
        login.logout();

    }
}

package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesQuotations;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestSalesQuotation {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_SALES_QUOTATION="./output/temp_api_request_bodies/salesQuotation.json";
    private static final String API_RESPONSE_SALES_QUOTATION="./output/api_responses/salesQuotation.json";
    private static final String OUTPUT_FILE_SALES_QUOTATION="./src/main/resources/menuItems/Sales/Transactions/460553 - Sales Quotations-AC_SQ_35_Output.xls";

    String file = "./src/main/resources/menuItems/Sales/Transactions/460553 - Sales Quotations-AC_SQ_35.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void salesQuotation() throws Exception {
        SalesQuotations quotations = new SalesQuotations(driver, file);
        quotations.salesQuotation(TEMP_API_SALES_QUOTATION,API_RESPONSE_SALES_QUOTATION,OUTPUT_FILE_SALES_QUOTATION);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
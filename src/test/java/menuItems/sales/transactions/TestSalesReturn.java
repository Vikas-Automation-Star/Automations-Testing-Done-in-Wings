package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.Transaction;
import com.wings.pages.TransactionsBaseClass;
import com.wings.pages.sales.transactions.SalesReturns;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestSalesReturn {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_SALES_INVOICES="./output/temp_api_request_bodies/SalesInvoices.json";
    private static final String API_RESPONSE_SALES_SALES_INVOICES="./output/api_responses/SalesInvoices.json";
    private static final String OUTPUT_FILE1="./src/main/resources/menuItems/Sales/Transactions/480460 - Sales Invoices-AC_SI1_1_Output.xls";

    private static final String TEMP_API_BODY_SALES_RETURNS="./output/temp_api_request_bodies/SalesReturns.json";
    private static final String API_RESPONSE_SALES_RETURNS="./output/api_responses/SalesReturns.json";
    private static final String OUTPUT_FILE2="./src/main/resources/menuItems/Sales/Transactions/480463 - Sales Returns-AC_Output.xlsx";


    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/480460 - Sales Invoices-AC_SI1_1.xls";
    String dataFile1 = "./src/main/resources/menuItems/Sales/Transactions/480463 - Sales Returns-AC.xlsx";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver= appLogin.login();
    }

    @Test
    public void salesReturns() throws Exception {
//        SalesInvoice invoice = new SalesInvoice(driver, dataFile);
//        String salesI=invoice.salesInvoice(TEMP_API_BODY_SALES_INVOICES,API_RESPONSE_SALES_SALES_INVOICES,OUTPUT_FILE1);
//
//        appLogin.logout();
//        driver= appLogin.login();

        SalesReturns salesReturns = new SalesReturns(driver, dataFile1);
        salesReturns.salesReturns("SI 23",TEMP_API_BODY_SALES_RETURNS,API_RESPONSE_SALES_RETURNS,OUTPUT_FILE2);
    }

    @AfterTest
    public void afterTest() throws IOException, InterruptedException {
//        TransactionsBaseClass transactionsBaseClass=new TransactionsBaseClass(driver);
//        Transaction transaction=new Transaction(driver);
//        transaction.navigateToMastersWhen3Steps("Sales","Invoices","Sales Returns");
//        Thread.sleep(1000);
//        transactionsBaseClass.deleteRecentTransaction();
        appLogin.logout();
    }
}
package menuItems.finance.transactions.Banking;

import com.wings.pages.sales.transactions.SalesInvoice;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Banking.DepositPostDatedCheques;
import java.io.IOException;

public class TestDepositPostDatedCheques {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_SALES_INVOICES="./output/temp_api_request_bodies/SalesInvoices.json";
    private static final String API_RESPONSE_SALES_SALES_INVOICES="./output/api_responses/SalesInvoices.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/Sales/Transactions/480460 - Sales Invoices-AC_SI1_1_Output.xls";

    private static final String TEMP_API_BODY_DEPOSIT_POSTDATED_CHEQUES="./output/temp_api_request_bodies/depositPostDatedCheques.json";
    private static final String API_RESPONSE_DEPOSIT_POSTDATED_CHEQUES="./output/api_responses/depositPostDatedCheques.json";
    private static final String OUTPUT_FILE_DEPOSIT_POSTDATED_CHEQUES="./src/main/resources/menuItems/finance/transaction/458330 - Deposit Post Dated Cheques-AC_DPDC_1_Output.xls";
    String dataFile1 = "./src/main/resources/menuItems/Sales/Transactions/480460 - Sales Invoices-AC_SI1_1.xls";

    String dataFile = "./src/main/resources/menuItems/finance/transaction/458330 - Deposit Post Dated Cheques-AC_DPDC_1.xls";

    @BeforeTest
    public void beforeTest() throws Exception {
        driver = appLogin.login();
    }

    @Test
    public void postDatedCheques() throws Exception {
        SalesInvoice invoice = new SalesInvoice(driver, dataFile1);
        String invoiceVoucher=invoice.salesInvoice(TEMP_API_BODY_SALES_INVOICES,API_RESPONSE_SALES_SALES_INVOICES,OUTPUT_FILE);

        appLogin.logout();
        driver=appLogin.login();

        DepositPostDatedCheques postDatedCheques = new DepositPostDatedCheques(driver, dataFile);
        postDatedCheques.postDatedChques(invoiceVoucher,TEMP_API_BODY_DEPOSIT_POSTDATED_CHEQUES,API_RESPONSE_DEPOSIT_POSTDATED_CHEQUES,OUTPUT_FILE_DEPOSIT_POSTDATED_CHEQUES);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
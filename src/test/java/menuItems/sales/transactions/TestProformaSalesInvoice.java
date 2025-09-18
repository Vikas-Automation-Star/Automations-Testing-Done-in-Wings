package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.ProformaSalesInvoice;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestProformaSalesInvoice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_PROFORMA_SALES_INVOICE="./output/temp_api_request_bodies/proformaSaleSInvoice.json";
    private static final String API_RESPONSE_PROFORMA_SALES_INVOICE="./output/api_responses/proformaSalesInvoice.json";
    private static final String OUTPUT_FILE_PROFORMA_SALES_INVOICE="./src/main/resources/menuItems/Sales/Transactions/475923 - Proforma Sales Invoices-AC_Output.xls";

    String file = "./src/main/resources/menuItems/Sales/Transactions/475923 - Proforma Sales Invoices-AC.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void proformaSalesInvoice() throws Exception, AWTException {
        ProformaSalesInvoice sales = new ProformaSalesInvoice(driver, file);
        sales.proformaSalesInvoice(TEMP_API_PROFORMA_SALES_INVOICE,API_RESPONSE_PROFORMA_SALES_INVOICE,OUTPUT_FILE_PROFORMA_SALES_INVOICE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.Deliveries;
import com.wings.pages.sales.transactions.SalesInvoiceAgainstDeliveries;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesInvoiceAgainstDeliveries {

    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();

    private static final String TEMP_API_SALES_DELIVERIES="./output/temp_api_request_bodies/deliveries.json";
    private static final String API_RESPONSE_SALES_DELIVERIES="./output/api_responses/deliveries.json";
    private static final String OUTPUT_FILE_SALES_DELIVERIES="./src/main/resources/menuItems/Sales/Transactions/476979 - Deliveries-AC_Output.xls";

    private static final String TEMP_API_SALES_INVOICE_AGAINST_DELIVERIES="./output/temp_api_request_bodies/salesInvoiceAgainstDeliveries.json";
    private static final String API_RESPONSE_SALES_INVOICE_AGAINST_DELIVERIES="./output/api_responses/salesInvoiceAgainstDeliveries.json";
    private static final String OUTPUT_FILE_SALES_INVOICE_AGAINST_DELIVERIES="./src/main/resources/menuItems/Sales/Transactions/480462 - Sales Invoices against Deliveries-AC_SIAD_4_Output.xls";

    String dataFile1="./src/main/resources/menuItems/Sales/Transactions/476979 - Deliveries-AC.xls";
    String dataFile2 ="./src/main/resources/menuItems/Sales/Transactions/480462 - Sales Invoices against Deliveries-AC_SIAD_4.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void deliveriesAgainstOrders() throws Exception, AWTException {
        Deliveries deliveries=new Deliveries(driver,dataFile1);
        String deliveriesVoucher=deliveries.salesDeliveries(TEMP_API_SALES_DELIVERIES,API_RESPONSE_SALES_DELIVERIES,OUTPUT_FILE_SALES_DELIVERIES);

        appLogin.logout();
        driver = appLogin.login();

        SalesInvoiceAgainstDeliveries salesInvoiceAgainstDeliveries=new SalesInvoiceAgainstDeliveries(driver,dataFile2);
        salesInvoiceAgainstDeliveries.salesInvoiceAgainstDeliveries
                (deliveriesVoucher,TEMP_API_SALES_INVOICE_AGAINST_DELIVERIES,API_RESPONSE_SALES_INVOICE_AGAINST_DELIVERIES,OUTPUT_FILE_SALES_INVOICE_AGAINST_DELIVERIES);

    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
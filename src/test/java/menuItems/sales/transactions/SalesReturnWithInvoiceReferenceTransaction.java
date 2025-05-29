package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.*;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class SalesReturnWithInvoiceReferenceTransaction {

        WindowsDriver driver;
        AppLogin appLogin = new AppLogin();
        Common common;
        String dataFile = "./src/main/resources/menuItems/Sales/Transactions/salesEnquiry.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            common=new Common(driver);
            driver = appLogin.launchSingleUserApp();
            appLogin.singleUserLogin(common.getData(dataFile,"salesEnquiry","userName"),common.getData(dataFile,"salesEnquiry","password"));
        }

        @Test
        public void salesReturnWithInvoiceReference() throws IOException, ParseException, InterruptedException, AWTException {
            SalesEnquiry salesEnquiry=new SalesEnquiry(driver,dataFile);
            String salesEnquiryVoucher=salesEnquiry.salesEnquiry();

            appLogin.logout();
            driver = appLogin.launchSingleUserApp();
            appLogin.singleUserLogin(common.getData(dataFile,"salesEnquiry","userName"),common.getData(dataFile,"salesEnquiry","password"));

            SalesQuotationAgainstEnquiry agnstEnquiry = new SalesQuotationAgainstEnquiry(driver, dataFile);
            String orderAgainstQuotation=agnstEnquiry.quotationAgainstEnquiry(salesEnquiryVoucher);

            appLogin.logout();
            driver = appLogin.launchSingleUserApp();
            appLogin.singleUserLogin(common.getData(dataFile,"salesEnquiry","userName"),common.getData(dataFile,"salesEnquiry","password"));

            SalesOrdersAgainstQuotations quotations = new SalesOrdersAgainstQuotations(driver, dataFile);
            String ordersVoucherNum= quotations.salesOrderAgainstQuotation(orderAgainstQuotation);

            appLogin.logout();
            driver = appLogin.launchSingleUserApp();
            appLogin.singleUserLogin(common.getData(dataFile,"salesEnquiry","userName"),common.getData(dataFile,"salesEnquiry","password"));

            SalesInvoiceAgainstOrders invoiceAgainstOrders=new SalesInvoiceAgainstOrders(driver,dataFile);
            String invoiceVoucher= invoiceAgainstOrders.invoiceAgainstOrders(ordersVoucherNum);

            appLogin.logout();
            driver = appLogin.launchSingleUserApp();
            appLogin.singleUserLogin(common.getData(dataFile,"salesEnquiry","userName"),common.getData(dataFile,"salesEnquiry","password"));
            
            SalesReturnWithInvoiceReference salesReturnWithInvoiceReference=new SalesReturnWithInvoiceReference(driver,dataFile);
            salesReturnWithInvoiceReference.salesReturnWithInvoiceReference(invoiceVoucher);
//            salesReturnWithInvoiceReference.salesReturnWithInvoiceReference("SIAO 4");
        }

        @AfterTest
        public void afterTest() throws IOException {
            appLogin.logout();
        }
    }
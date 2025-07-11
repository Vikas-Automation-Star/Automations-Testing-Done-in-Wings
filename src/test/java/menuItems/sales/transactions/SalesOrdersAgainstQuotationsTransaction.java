package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesEnquiry;
import com.wings.pages.sales.transactions.SalesOrdersAgainstQuotations;
import com.wings.pages.sales.transactions.SalesQuotationAgainstEnquiry;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class SalesOrdersAgainstQuotationsTransaction {
    WindowsDriver driver;
    AppLogin login = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/477396 - Sales Orders against Quotations-AC.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.login();
    }

    @Test
    public void salesOrderAgainstQuotations() throws IOException, ParseException, InterruptedException, AWTException {
//        SalesEnquiry salesEnquiry=new SalesEnquiry(driver,dataFile);
//        String salesEnquiryVoucher= salesEnquiry.salesEnquiry();
//
//        login.logout();
//        driver=login.launchSingleUserApp();
//        login.singleUserLogin(common.getData(dataFile,"salesEnquiry","userName"),common.getData(dataFile,"salesEnquiry","password"));
//
//        SalesQuotationAgainstEnquiry agnstEnquiry = new SalesQuotationAgainstEnquiry(driver, dataFile);
//        String orderAgainstQuotation=agnstEnquiry.quotationAgainstEnquiry(salesEnquiryVoucher);
//
//        login.logout();
//        driver=login.launchSingleUserApp();
//        login.singleUserLogin(common.getData(dataFile,"salesEnquiry","userName"),common.getData(dataFile,"salesEnquiry","password"));

        SalesOrdersAgainstQuotations quotations = new SalesOrdersAgainstQuotations(driver, dataFile);
        quotations.salesOrderAgainstQuotation("SQ 7");
//        quotations.salesOrderAgainstQuotation("SQAE 2");

    }

    @AfterTest
    public void afterTest() throws IOException {
//        login.logout();
    }
}

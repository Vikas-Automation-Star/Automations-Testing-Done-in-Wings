package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesEnquiry;
import com.wings.pages.sales.transactions.SalesOrdersAgainstQuotations;
import com.wings.pages.sales.transactions.SalesQuotationAgainstEnquiry;
import com.wings.pages.sales.transactions.SalesQuotations;
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
    String dataFile1="./src/main/resources/menuItems/Sales/Transactions/460553 - Sales Quotations-AC.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.login();
    }

    @Test
    public void salesOrderAgainstQuotations() throws IOException, ParseException, InterruptedException, AWTException {

        SalesQuotations salesQuotations=new SalesQuotations(driver,dataFile1);
        String quotationVoucher=salesQuotations.salesQuotation();

        login.logout();
        driver=login.login();

        SalesOrdersAgainstQuotations quotations = new SalesOrdersAgainstQuotations(driver, dataFile);
        quotations.salesOrderAgainstQuotation(quotationVoucher);
//        quotations.salesOrderAgainstQuotation("SQAE 2");

    }

    @AfterTest
    public void afterTest() throws IOException {
        login.logout();
    }
}

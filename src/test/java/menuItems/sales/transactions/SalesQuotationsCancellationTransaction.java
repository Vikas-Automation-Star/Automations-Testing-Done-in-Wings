package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesQuotationCancellaton;
import com.wings.pages.sales.transactions.SalesQuotations;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class SalesQuotationsCancellationTransaction {
    WindowsDriver driver;
    AppLogin login = new AppLogin();
    Common common;
    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/salesQuotation.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = login.launchSingleUserApp();
        login.singleUserLogin(common.getData(dataFile,"salesQuotation","userName"), common.getData(dataFile,"salesQuotation","password"));
    }

    @Test
    public void salesQuotationCancellation() throws InterruptedException, IOException, ParseException, AWTException {
        SalesQuotationCancellaton cancellation = new SalesQuotationCancellaton(driver, dataFile);
        SalesQuotations salesQuotations=new SalesQuotations(driver,dataFile);
        cancellation.salesQuotationCancelltion(salesQuotations.salesQuotation());
    }

    @AfterTest
    public void afterTest() throws IOException {
//        login.logout();
    }
}

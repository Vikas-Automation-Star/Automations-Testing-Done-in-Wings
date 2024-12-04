package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import TestCases.SalesInvoicesExcludingGST;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class SalesInvoiceExludingGSTTransaction {
    WindowsDriver driver;
    AppLogin login=new AppLogin();
    String dataFile="src/main/resources/menuItems/Sales/Transactions/salesInvoiceExludingGST.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=login.launchSingleUserApp();
        login.singleUserLogin();

    }
    @Test
    public void salesInvoiceAgainstOrders() throws IOException, ParseException, InterruptedException, AWTException {
        SalesInvoicesExcludingGST sieg=new SalesInvoicesExcludingGST(driver,dataFile);
        sieg.salesInvoicesExcludingGST();
    }

    @AfterTest
    public void afterTest(){
//        login.logout();
    }
}

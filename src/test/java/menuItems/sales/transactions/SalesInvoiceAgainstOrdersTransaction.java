package menuItems.sales.transactions;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesInvoiceAgainstOrders;
import java.awt.*;
import java.io.IOException;

public class SalesInvoiceAgainstOrdersTransaction {
    WindowsDriver driver;
    AppLogin login=new AppLogin();
    String dataFile="./src/main/resources/menuItems/Sales/Transactions/salesInvoiceAgainstOrders.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=login.launchSingleUserApp();
        login.singleUserLogin();
        Allure.step("Before Test Sales Invoice Against Orders");
    }
    @Test
    public void salesInvoiceAgainstOrders() throws IOException, ParseException, InterruptedException, AWTException {
        SalesInvoiceAgainstOrders invoiceAgainstOrders=new SalesInvoiceAgainstOrders(driver,dataFile);
        invoiceAgainstOrders.salesInvoicesAgainstOrders();
    }

    @AfterTest
    public void afterTest() throws IOException{
        login.logout();
        Allure.step("After Test Sales Invoice Against Orders");
    }
}
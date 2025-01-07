package menuItems.sales.transactions;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesInvoiceAgainstDeliveries;
import java.awt.*;
import java.io.IOException;

public class SalesInvoiceAgainstDeliveriesTransaction {
    WindowsDriver driver;
    AppLogin login=new AppLogin();
    String dataFile="./src/main/resources/menuItems/Sales/Transactions/salesInvoiceAgainstDeliveries.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=login.launchSingleUserApp();
        login.singleUserLogin();
        Allure.step("Before Test Sales Invoice Against Deliveries");
    }

    @Test
    public void salesInvoiceAgainstDeliveries() throws IOException, ParseException, InterruptedException, AWTException {
        SalesInvoiceAgainstDeliveries invoiceAgainstDeliveries=new SalesInvoiceAgainstDeliveries(driver,dataFile);
        invoiceAgainstDeliveries.salesInvoicesAgainstDeliveries();
    }

    @AfterTest
    public void afterTest() throws IOException{
        login.logout();
        Allure.step("After Test Sales Invoice Against Deliveries");
    }
}
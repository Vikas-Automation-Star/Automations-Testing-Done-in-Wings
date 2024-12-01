package menuItems.sales.transactions;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesReturnWithInvoiceReference;
import java.awt.*;
import java.io.IOException;

public class SalesReturnWithInvoiceReferenceTransaction {
    WindowsDriver driver;
    AppLogin login=new AppLogin();
    String dataFile="./src/main/resources/menuItems/Sales/Transactions/salesReturnWithInvoiceReference.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=login.launchSingleUserApp();
        login.singleUserLogin();
        Allure.step("Before Test Sales Return with Invoice Reference");
    }

    @Test
    public void salesReturnsWithInvoiceReference() throws IOException, ParseException, InterruptedException, AWTException {
        SalesReturnWithInvoiceReference returnWithInvoiceReference=new SalesReturnWithInvoiceReference(driver,dataFile);
        returnWithInvoiceReference.salesReturnWithInvoiceReference();
    }

    @AfterTest
    public void afterTest(){
        login.logout();
        Allure.step("After Test Sales Return with Invoice Reference");
    }
}
package menuItems.sales.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesQuotationCancellaton;

import java.io.IOException;

public class SalesQuotationsCancellationTransaction {
    WindowsDriver driver;
    AppLogin login=new AppLogin();
    String dataFile="./src/main/resources/MenuItems/Sales/Transactions/salesQuotationCancellation.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=login.launchSingleUserApp();
        login.singleUserLogin();
    }

    @Test
    public void salesQuoteCancel() throws InterruptedException, IOException, ParseException {
        SalesQuotationCancellaton cancellaton=new SalesQuotationCancellaton(driver,dataFile);
        cancellaton.salesQuotationCancelltion();
    }

    @AfterTest
    public void afterTest(){
        login.logout();
    }
}

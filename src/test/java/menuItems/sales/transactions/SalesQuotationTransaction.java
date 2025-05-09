package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesQuotations;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class SalesQuotationTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String file = "./src/main/resources/menuItems/Sales/Transactions/salesQuotation.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(file,"salesQuotation","userName"), common.getData(file,"salesQuotation","password"));
    }

    @Test
    public void salesQuotation() throws IOException, ParseException, InterruptedException, AWTException {
        SalesQuotations quotations = new SalesQuotations(driver, file);
        quotations.salesQuotation();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
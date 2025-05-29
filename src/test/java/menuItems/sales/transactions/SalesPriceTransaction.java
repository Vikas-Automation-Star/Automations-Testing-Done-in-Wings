package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesPrices;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class SalesPriceTransaction {
    WindowsDriver driver;
    AppLogin login = new AppLogin();
    Common common;
    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/salesEnquiry.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = login.launchSingleUserApp();
        login.singleUserLogin(common.getData(dataFile,"salesEnquiry","userName"),common.getData(dataFile,"salesEnquiry","password"));
    }

    @Test
    public void salesPrice() throws IOException, ParseException, InterruptedException {
        SalesPrices pricesAndDiscount = new SalesPrices(driver, dataFile);
        pricesAndDiscount.salesPrices();
    }

    @AfterTest
    public void afterTest() throws IOException {
        login.logout();
    }
}
package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.ProductndPartyDiscount;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class ProductPartyDiscountTransaction {
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
    public void partyDsicount() throws IOException, ParseException, InterruptedException {
        ProductndPartyDiscount partyDiscount = new ProductndPartyDiscount(driver, dataFile);
        partyDiscount.productDiscount();
    }

    @AfterTest
    public void afterTest() throws IOException {
        login.logout();
    }
}
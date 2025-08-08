package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.ProductndPartyDiscount;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class ProductPartyDiscountTransaction {
    WindowsDriver driver;
    AppLogin login = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/251491 - Party and Product wise Discounts-AC.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.login();
    }

    @Test
    public void productAndPartyDiscount() throws IOException, ParseException, InterruptedException {
        ProductndPartyDiscount partyDiscount = new ProductndPartyDiscount(driver, dataFile);
        partyDiscount.productDiscount();
    }

    @AfterTest
    public void afterTest() throws IOException {
        login.logout();
    }
}
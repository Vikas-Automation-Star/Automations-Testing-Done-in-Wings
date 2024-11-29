package menuItems.sales.transactions;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.ProductndPartyDiscount;

import java.io.IOException;

public class ProductPartyDiscountTransaction {
    WindowsDriver driver;
    AppLogin login=new AppLogin();
    String dataFile="./src/main/resources/menuItems/Sales/Transactions/productPartyDiscount.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=login.launchSingleUserApp();
        login.singleUserLogin();
        Allure.step("Before Test Product and Party wise Discounts");
    }

    @Test
    public void partyDsicount() throws IOException, ParseException, InterruptedException {
        ProductndPartyDiscount partyDiscount=new ProductndPartyDiscount(driver,dataFile);
        partyDiscount.productDiscount();
    }

    @AfterTest
    public void afterTest(){
        login.logout();
        Allure.step("After test Product and Party wise Discounts");
    }
}
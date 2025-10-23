package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.ProductndPartyDiscount;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.*;

import java.io.IOException;

public class TestProductPartyDiscount {
    WindowsDriver driver;
    AppLogin login = new AppLogin();
    private static final String TEMP_API_PARTY_PRODUCT_DISCOUNT="./output/temp_api_request_bodies/partyProductDiscount.json";
    private static final String API_RESPONSE_PARTY_PRODUCT_DISCOUNT="./output/api_responses/partyProductDiscount.json";
    private static final String OUTPUT_FILE_PARTY_PRODUCT_DISCOUNT="./src/main/resources/menuItems/Sales/Transactions/251491 - Party and Product wise Discounts-AC_Output.xls";

    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/251491 - Party and Product wise Discounts-AC.xls";

    @BeforeMethod
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.login();
    }

    @Test
    public void productAndPartyDiscount() throws Exception {
        ProductndPartyDiscount partyDiscount = new ProductndPartyDiscount(driver, dataFile);
        partyDiscount.productDiscount(TEMP_API_PARTY_PRODUCT_DISCOUNT,API_RESPONSE_PARTY_PRODUCT_DISCOUNT,OUTPUT_FILE_PARTY_PRODUCT_DISCOUNT);
    }

    @AfterMethod
    public void afterTest() throws IOException {
        login.logout();
    }

}
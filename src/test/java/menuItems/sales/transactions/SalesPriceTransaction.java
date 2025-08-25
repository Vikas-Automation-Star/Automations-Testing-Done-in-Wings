package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesPrices;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class SalesPriceTransaction {
    WindowsDriver driver;
    AppLogin login = new AppLogin();

    private static final String TEMP_API_SALES_PRICE="./output/temp_api_request_bodies/salesPrice.json";
    private static final String API_RESPONSE_SALES_PRICE="./output/api_responses/salesPrice.json";
    private static final String OUTPUT_FILE_SALES_PRICE="./src/main/resources/menuItems/Sales/Transactions/477030 - Sales Prices-AC_Output.xls";

    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/477030 - Sales Prices-AC.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.login();
    }

    @Test
    public void salesPrice() throws IOException, ParseException, InterruptedException {
        SalesPrices pricesAndDiscount = new SalesPrices(driver, dataFile);
        pricesAndDiscount.salesPrices(TEMP_API_SALES_PRICE,API_RESPONSE_SALES_PRICE,OUTPUT_FILE_SALES_PRICE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        login.logout();
    }
}
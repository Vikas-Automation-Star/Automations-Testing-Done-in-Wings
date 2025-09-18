package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchasePrice;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestPurchasePrices {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_PURCHASE_PRICES="./output/temp_api_request_bodies/purchasePrices.json";
    private static final String API_RESPONSE_PURCHASE_PRICES="./output/api_responses/purchasePrices.json";
    private static final String OUTPUT_FILE_PURCHASE_PRICES="./src/main/resources/menuItems/purchase/transactions/477029 - Purchase Prices-AC_PPU_8_Output.xls";

    String file = "./src/main/resources/menuItems/purchase/transactions/477029 - Purchase Prices-AC_PPU_8.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void PurchasePrices() throws Exception {
        PurchasePrice pp = new PurchasePrice(driver, file);
        pp.purchasePrice(TEMP_API_PURCHASE_PRICES,API_RESPONSE_PURCHASE_PRICES,OUTPUT_FILE_PURCHASE_PRICES);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

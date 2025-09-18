package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseQuotation;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestPurchaseQuotations {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_PURCHASE_QUOTATIONS="./output/temp_api_request_bodies/purchaseQuotations.json";
    private static final String API_RESPONSE_PURCHASE_QUOTATIONS="./output/api_responses/purchaseQuotations.json";
    private static final String OUTPUT_FILE_PURCHASE_QUOTATIONS="./src/main/resources/menuItems/purchase/transactions/469187 - Purchase Quotations-AC_PQ_1_Output.xls";

    String file = "./src/main/resources/menuItems/purchase/transactions/469187 - Purchase Quotations-AC_PQ_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void purchaseQuotations() throws Exception {
        PurchaseQuotation pq = new PurchaseQuotation(driver, file);
        pq.purchaseQuotation(TEMP_API_PURCHASE_QUOTATIONS,API_RESPONSE_PURCHASE_QUOTATIONS,OUTPUT_FILE_PURCHASE_QUOTATIONS);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
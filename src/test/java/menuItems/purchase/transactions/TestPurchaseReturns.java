package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseReturns;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestPurchaseReturns {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_PURCHASE_RETURNS="./output/temp_api_request_bodies/purchaseReturns.json";
    private static final String API_RESPONSE_PURCHASE_RETURNS="./output/api_responses/purchaseReturns.json";
    private static final String OUTPUT_FILE_PURCHASE_RETURNS="./src/main/resources/menuItems/purchase/transactions/478090 - Purchase Returns-AC_PRT_1_Output.xls";

    String file = "./src/main/resources/menuItems/purchase/transactions/478090 - Purchase Returns-AC_PRT_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void purchaseReturns() throws Exception {
        PurchaseReturns purchaseReturns=new PurchaseReturns(driver,file);
        purchaseReturns.purchaseReturns(TEMP_API_BODY_PURCHASE_RETURNS,API_RESPONSE_PURCHASE_RETURNS,OUTPUT_FILE_PURCHASE_RETURNS);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
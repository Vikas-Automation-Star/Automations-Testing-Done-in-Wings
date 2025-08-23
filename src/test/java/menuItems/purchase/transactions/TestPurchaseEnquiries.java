package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseEnquiries;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPurchaseEnquiries {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();


    private static final String TEMP_API_BODY_PURCHASE_ENQUIRY="./output/temp_api_request_bodies/purchaseEnquiries.json";
    private static final String API_RESPONSE_PURCHASE_ENQUIRY="./output/api_responses/purchaseEnquiries.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/purchase/transactions/461617 - Purchase Enquiries-AC_PE_3.xlsx";

    String dataFile = "./src/main/resources/menuItems/purchase/transactions/461617 - Purchase Enquiries-AC_PE_3.xlsx";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver= appLogin.login();
    }

    @Test
    public void purchaseEnquiries() throws Exception {
        PurchaseEnquiries pe = new PurchaseEnquiries(driver, dataFile);
        pe.purchaseEnquires(TEMP_API_BODY_PURCHASE_ENQUIRY,API_RESPONSE_PURCHASE_ENQUIRY,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

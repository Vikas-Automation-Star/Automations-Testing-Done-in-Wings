package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseEnquiries;
import com.wings.pages.purchase.transactions.PurchaseEnquiriesCancellation;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestPurchaseEnquiriesCancellation {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_PURCHASE_ENQUIRY="./output/temp_api_request_bodies/purchaseEnquiries.json";
    private static final String API_RESPONSE_PURCHASE_ENQUIRY="./output/api_responses/purchaseEnquiries.json";
    private static final String OUTPUT_FILE1="./src/main/resources/menuItems/purchase/transactions/461617 - Purchase Enquiries-AC_PE_3_Output.xls";


    private static final String TEMP_API_BODY_PURCHASE_ENQUIRY_CANCELLATIONS="./output/temp_api_request_bodies/purchaseEnquiriesCancellations.json";
    private static final String API_RESPONSE_PURCHASE_ENQUIRY_CANCELLATIONS="./output/api_responses/purchaseEnquiriesCancellations.json";
    private static final String OUTPUT_FILE2="./src/main/resources/menuItems/purchase/transactions/461400 - Purchase Enquiries Cancellation-AC_PEC_1_Output.xlsx";


    String dataFile = "./src/main/resources/menuItems/purchase/transactions/461617 - Purchase Enquiries-AC_PE_3.xls";
    String dataFile1 = "./src/main/resources/menuItems/purchase/transactions/461400 - Purchase Enquiries Cancellation-AC_PEC_1.xlsx";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver= appLogin.login();
    }

    @Test
    public void purchaseEnquiriesCancellation() throws Exception {
//        PurchaseEnquiries purchaseEnquiries = new PurchaseEnquiries(driver, dataFile);
//        String enquiriesVoucher=purchaseEnquiries.purchaseEnquires(TEMP_API_BODY_PURCHASE_ENQUIRY,API_RESPONSE_PURCHASE_ENQUIRY,OUTPUT_FILE1);
//
//        appLogin.logout();
//        driver= appLogin.login();

        PurchaseEnquiriesCancellation enquiriesCancellation = new PurchaseEnquiriesCancellation(driver, dataFile1);
        enquiriesCancellation.purchaseEnquiriesCancellation("PE 9",TEMP_API_BODY_PURCHASE_ENQUIRY_CANCELLATIONS,API_RESPONSE_PURCHASE_ENQUIRY_CANCELLATIONS,OUTPUT_FILE2);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

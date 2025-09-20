package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseEnquiries;
import com.wings.pages.purchase.transactions.PurchaseOrdersAgainstQuotation;
import com.wings.pages.purchase.transactions.PurchaseQuotationsAgainstEnquiries;
import com.wings.pages.purchase.transactions.PurchaseVouchersAgainstOrder;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPurchaseVouchersAgainstOrders {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_PURCHASE_ENQUIRY="./output/temp_api_request_bodies/purchaseEnquiries.json";
    private static final String API_RESPONSE_PURCHASE_ENQUIRY="./output/api_responses/purchaseEnquiries.json";
    private static final String OUTPUT_FILE1="./src/main/resources/menuItems/purchase/transactions/461617 - Purchase Enquiries-AC_PE_3_Output.xls";

    private static final String TEMP_API_BODY_PURCHASE_QUOTATIONS_AGAINST_ENQUIRY="./output/temp_api_request_bodies/purchaseQuotationsAgainstEnquiries.json";
    private static final String API_RESPONSE_PURCHASE_QUOTATIONS_AGAINST_ENQUIRY="./output/api_responses/purchaseQuotationsAgainstEnquiries.json";
    private static final String OUTPUT_FILE2="./src/main/resources/menuItems/purchase/transactions/461887 - Purchase Quotations against Enquiries-AC_PQAPE_1_Output.xlsx";

    private static final String TEMP_API_BODY_PURCHASE_ORDERS_AGAINST_QUOTATIONS="./output/temp_api_request_bodies/PurchaseOrdersAgainstQuotations.json";
    private static final String API_RESPONSE_PURCHASE_ORDERS_AGAINST_QUOTATIONS="./output/api_responses/PurchaseOrdersAgainstQuotations.json";
    private static final String OUTPUT_FILE3="./src/main/resources/menuItems/purchase/transactions/480465 - Purchase Orders against Quotations-AC_POAQ 1_Output.xls";


    private static final String TEMP_API_BODY_PURCHASE_VOUCHERS_AGAINST_ORDERS="./output/temp_api_request_bodies/PurchaseVouchersAgainstOrders.json";
    private static final String API_RESPONSE_PURCHASE_VOUCHERS_AGAINST_ORDERS="./output/api_responses/PurchaseVouchersAgainstOrders.json";
    private static final String OUTPUT_FILE4="./src/main/resources/menuItems/purchase/transactions/479082 - Purchase Vouchers against Orders-AC_PVAO_1_Output.xlsx";

    String dataFile = "./src/main/resources/menuItems/purchase/transactions/461617 - Purchase Enquiries-AC_PE_3.xls";
    String dataFile1 = "./src/main/resources/menuItems/purchase/transactions/461887 - Purchase Quotations against Enquiries-AC_PQAPE_1.xlsx";
    String dataFile2 = "./src/main/resources/menuItems/purchase/transactions/480465 - Purchase Orders against Quotations-AC_POAQ 1.xls";
    String dataFile3 = "./src/main/resources/menuItems/purchase/transactions/479082 - Purchase Vouchers against Orders-AC_PVAO_1.xlsx";


    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver= appLogin.login();
    }

    @Test
    public void purchaseVouchersAgainstOrders() throws Exception {
//        PurchaseEnquiries purchaseEnquiries = new PurchaseEnquiries(driver, dataFile);
//        String pe=purchaseEnquiries.purchaseEnquires(TEMP_API_BODY_PURCHASE_ENQUIRY,API_RESPONSE_PURCHASE_ENQUIRY,OUTPUT_FILE1);
//
//        appLogin.logout();
//        driver= appLogin.login();
//
//        PurchaseQuotationsAgainstEnquiries quotationsAgainstEnquiries = new PurchaseQuotationsAgainstEnquiries(driver, dataFile1);
//        String PQAE =quotationsAgainstEnquiries.purchaseQuotationsAgainstEnquiry(pe,TEMP_API_BODY_PURCHASE_QUOTATIONS_AGAINST_ENQUIRY,API_RESPONSE_PURCHASE_QUOTATIONS_AGAINST_ENQUIRY,OUTPUT_FILE2);
//
//        appLogin.logout();
//        driver= appLogin.login();
//
//        PurchaseOrdersAgainstQuotation POAQ = new PurchaseOrdersAgainstQuotation(driver, dataFile2);
//        String poaq= POAQ.purchaseOrdersAgainstQuotation(PQAE,TEMP_API_BODY_PURCHASE_ORDERS_AGAINST_QUOTATIONS,API_RESPONSE_PURCHASE_ORDERS_AGAINST_QUOTATIONS,OUTPUT_FILE3);
//
//        appLogin.logout();
//        driver= appLogin.login();

        PurchaseVouchersAgainstOrder pvao = new PurchaseVouchersAgainstOrder(driver, dataFile3);
        String PVAPO=pvao.purchaseVouchersAgainstOrder("POAQ 2",TEMP_API_BODY_PURCHASE_VOUCHERS_AGAINST_ORDERS,API_RESPONSE_PURCHASE_VOUCHERS_AGAINST_ORDERS,OUTPUT_FILE4);

    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}

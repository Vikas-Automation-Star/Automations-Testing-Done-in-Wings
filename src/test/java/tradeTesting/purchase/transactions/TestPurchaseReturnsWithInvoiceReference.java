package tradeTesting.purchase.transactions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestPurchaseReturnsWithInvoiceReference {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_PurchaseVouchers="./output/tradeOutputs/temp_api_request_bodies/PurchaseVouchers.json";
    private static final String API_RESPONSE_PurchaseVouchers="./output/tradeOutputs/api_responses/PurchaseVouchers.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/purchase/transactions/491716 - Purchase Vouchers-Trd_PV_3_CR_2_CP_1_Output.xls";


    private static final String TEMP_API_BODY_PURCHASE_RETURNS_WITH_INVOICE_REFERENCE="./output/temp_api_request_bodies/PurchaseReturnsWithInvoiceReferences.json";
    private static final String API_RESPONSE_PURCHASE_RETURNS_WITH_INVOICE_REFERENCE="./output/api_responses/PurchaseReturnsWithInvoiceReferences.json";
    private static final String OUTPUT_FILE5="./src/main/resources/tradeAutomation/purchase/transactions/493149 - PurchaseReturnsWithInvoiceReference-Trade_PRWIR_4_PV_9_Output.xls";

    String dataFile = "./src/main/resources/tradeAutomation/purchase/transactions/491716 - Purchase Vouchers-Trd_PV_3_CR_2_CP_1.xls";
    String dataFile1 = "./src/main/resources/tradeAutomation/purchase/transactions/493149 - PurchaseReturnsWithInvoiceReference-Trade_PRWIR_4_PV_9.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void purchaseReturnsWithInvoicesReferences() throws Exception {
//        PurchaseVouchers purchaseVouchers1=new PurchaseVouchers(driver,dataFile);
//        String vouchers= purchaseVouchers1.purchaseVouchers("CR 3","CP 2","","","");
//
//        appLogin.logout();
//        driver= appLogin.tradeLogin();

        PurchaseReturnsWithInvoiceReference invoiceReference=new PurchaseReturnsWithInvoiceReference(driver,dataFile1);
        invoiceReference.purchaseReturnsWithInvoiceReference("PV 9","CCR 3");
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }

}

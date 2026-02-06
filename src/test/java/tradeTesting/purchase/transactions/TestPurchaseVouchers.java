package tradeTesting.purchase.transactions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.finance.transactions.receipts.CashReceipts;

import java.io.IOException;

public class TestPurchaseVouchers {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_CASH_RECEIPTS = "./output/temp_api_request_bodies/CashReceipts.json";
    private static final String API_RESPONSE_CASH_RECEIPTS = "./output/api_responses/CashReceipts.json";
    private static final String OUTPUT_FILE1 = "./src/main/resources/tradeAutomation/finance/transactions/491894 - Cash Receipts-AC_CR_2_PVAO_2_SIAO_3_Output.xls";

    private static final String TEMP_API_BODY_PurchaseVouchers="./output/tradeOutputs/temp_api_request_bodies/PurchaseVouchers.json";
    private static final String API_RESPONSE_PurchaseVouchers="./output/tradeOutputs/api_responses/PurchaseVouchers.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/purchase/transactions/491716 - Purchase Vouchers-Trd_PV_3_CR_2_CP_1_Output.xls";

    String dataFile = "./src/main/resources/tradeAutomation/purchase/transactions/491716 - Purchase Vouchers-Trd_PV_3_CR_2_CP_1.xls";
    String dataFile1 = "./src/main/resources/tradeAutomation/finance/transactions/491894 - Cash Receipts-AC_CR_2_PVAO_2_SIAO_3.xls";

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException {
        driver= appLogin.tradeLogin();
    }

    @Test
    public void purchaseVoucher() throws Exception {
//        CashReceipts cashReceipts=new CashReceipts(driver,dataFile1);
//        cashReceipts.cashReceipt("PVAO 2");
//
//        appLogin.logout();
//        driver= appLogin.tradeLogin();

        PurchaseVouchers purchaseVouchers=new PurchaseVouchers(driver,dataFile);
        purchaseVouchers.purchaseVouchers("CR 3","CP 2","","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}

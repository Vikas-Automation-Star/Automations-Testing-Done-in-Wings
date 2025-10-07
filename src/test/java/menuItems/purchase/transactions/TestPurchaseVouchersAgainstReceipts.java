package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.MaterialReceipt;
import com.wings.pages.purchase.transactions.PurchaseVouchersAgainstReceipt;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestPurchaseVouchersAgainstReceipts {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_MATERIAL_RECEIPTS="./output/temp_api_request_bodies/materialReceipt.json";
    private static final String API_RESPONSE_MATERIAL_RECEIPTS="./output/api_responses/materialReceipt.json";
    private static final String OUTPUT_FILE_MATERIAL_RECEIPTS="./src/main/resources/menuItems/purchase/transactions/473342 - Material Receipts-AC_MR_1_Output.xls";

    private static final String TEMP_API_BODY_VOUCHER_AGAINST_RECEIPT="./output/temp_api_request_bodies/purchaseVoucherAgainstReceipts.json";
    private static final String API_RESPONSE_VOUCHER_AGAINST_RECEIPT="./output/api_responses/purchaseVoucherAgainstReceipts.json";
    private static final String OUTPUT_FILE_VOUCHER_AGAINST_RECEIPT="./src/main/resources/menuItems/purchase/transactions/479085 - Purchase Vouchers against Receipts-AC_PVAMR_1_MR_1_Output.xls";

    String file = "./src/main/resources/menuItems/purchase/transactions/473342 - Material Receipts-AC_MR_1.xls";
    String file1 = "./src/main/resources/menuItems/purchase/transactions/479085 - Purchase Vouchers against Receipts-AC_PVAMR_1_MR_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void purchaseVouchersAgainstTheReceipts() throws Exception {
//        MaterialReceipt materialReceipt=new MaterialReceipt(driver,file);
//        String receiptVoucher =materialReceipt.materialReceipt(TEMP_API_BODY_MATERIAL_RECEIPTS,API_RESPONSE_MATERIAL_RECEIPTS,OUTPUT_FILE_MATERIAL_RECEIPTS);
//
//        appLogin.logout();
//        driver=appLogin.login();

        PurchaseVouchersAgainstReceipt vouchersAgainstReceipt=new PurchaseVouchersAgainstReceipt(driver,file1);
        vouchersAgainstReceipt.purchaseVouchersAgainstReceipt("MR 2",TEMP_API_BODY_VOUCHER_AGAINST_RECEIPT,API_RESPONSE_VOUCHER_AGAINST_RECEIPT,OUTPUT_FILE_VOUCHER_AGAINST_RECEIPT);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

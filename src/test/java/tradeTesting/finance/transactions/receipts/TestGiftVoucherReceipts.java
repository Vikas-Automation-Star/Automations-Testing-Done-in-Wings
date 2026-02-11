package tradeTesting.finance.transactions.receipts;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestGiftVoucherReceipts {    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_JOURNAL_ENTRIES="./output/temp_api_request_bodies/GiftVoucherReceipts.json";
    private static final String API_RESPONSE_JOURNAL_ENTRIES="./output/api_responses/GiftVoucherReceipts.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/finance/transactions/487996 - Gift Voucher Receipts-Trd_GVR_3_SI_2_SIAO_3_Output.xls";

    String dataFile = "./src/main/resources/tradeAutomation/finance/transactions/487996 - Gift Voucher Receipts-Trd_GVR_3_SI_2_SIAO_3.xls";


    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void giftVoucherReceipts() throws Exception {
//        PurchaseVouchers vouchers=new PurchaseVouchers(driver,dataFile);
//        vouchers.purchaseVouchers();
//
//        appLogin.logout();
//        driver= appLogin.login();

        GiftVoucherReceipts voucherReceipts=new GiftVoucherReceipts(driver,dataFile);
        voucherReceipts.giftVoucherReceipts("SIAO 3","","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}

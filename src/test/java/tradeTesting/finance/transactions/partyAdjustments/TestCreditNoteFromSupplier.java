package tradeTesting.finance.transactions.partyAdjustments;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;

public class TestCreditNoteFromSupplier {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_PURCHASE_VOUCHERS="./output/temp_api_request_bodies/PurchaseVouchers.json";
    private static final String API_RESPONSE_PURCHASE_VOUCHERS="./output/api_responses/PurchaseVouchers.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9_Output.xls";

    private static final String TEMP_API_BODY_CREDIT_NOTE_FROM_SUPPLIER="./output/temp_api_request_bodies/CreditNoteFromSupplier.json";
    private static final String API_RESPONSE_CREDIT_NOTE_FROM_SUPPLIER="./output/api_responses/CreditNoteFromSupplier.json";
    private static final String OUTPUT_FILE1="./src/main/resources/tradeAutomation/finance/transactions/496359 - Credit Note from Suppliers-AC_CNFS_2_PRWIR_3_Output.xls";

    String file = "./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9.xls";
    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/496359 - Credit Note from Suppliers-AC_CNFS_2_PRWIR_3.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void creditNoteFromSupplier() throws Exception {
//        PurchaseVoucher voucher = new PurchaseVoucher(driver, file);
//        String purchaseVoucher=voucher.purchaseVoucher(TEMP_API_BODY_PURCHASE_VOUCHERS,API_RESPONSE_PURCHASE_VOUCHERS,OUTPUT_FILE);

//        appLogin.logout();
//        driver= appLogin.tradeLogin();

        CreditNoteFromSupplier noteFromSupplier = new CreditNoteFromSupplier(driver, dataFile);
        noteFromSupplier.creditNoteFromSupplier("PV 1","PRWIR 3","","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}

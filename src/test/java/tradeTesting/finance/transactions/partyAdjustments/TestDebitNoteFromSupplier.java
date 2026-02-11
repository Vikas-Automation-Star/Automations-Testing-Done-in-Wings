package tradeTesting.finance.transactions.partyAdjustments;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestDebitNoteFromSupplier {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_DEBIT_NOTE_FROM_SUPPLIER="./output/temp_api_request_bodies/DebitNoteFromSupplier.json";
    private static final String API_RESPONSE_DEBIT_NOTE_FROM_SUPPLIER="./output/api_responses/DebitNoteFromSupplier.json";
    private static final String OUTPUT_FILE1="./src/main/resources/tradeAutomation/finance/transactions/496706 - Debit Note from Suppliers-AC_DNFS_2_PRWIR_3_Output.xls";

    private static final String TEMP_API_BODY_PURCHASE_VOUCHERS="./output/temp_api_request_bodies/PurchaseVouchers.json";
    private static final String API_RESPONSE_PURCHASE_VOUCHERS="./output/api_responses/PurchaseVouchers.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9_Output.xls";

    String file = "./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9.xls";
    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/496706 - Debit Note from Suppliers-AC_DNFS_2_PRWIR_3.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void debitNoteFromSupplier() throws Exception {
//        PurchaseVoucher pv = new PurchaseVoucher(driver, file);
//        String purchaseVoucher=pv.purchaseVoucher(TEMP_API_BODY_PURCHASE_VOUCHERS,API_RESPONSE_PURCHASE_VOUCHERS,OUTPUT_FILE);
//
//        appLogin.logout();
//        driver= appLogin.tradeLogin();

        DebitNoteFromSupplier noteFromSuppliers = new DebitNoteFromSupplier(driver, dataFile);
        noteFromSuppliers.debitNoteFromSupplier("PRWIR 1","PRWIR 3","","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

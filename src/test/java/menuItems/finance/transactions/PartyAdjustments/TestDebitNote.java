package menuItems.finance.transactions.PartyAdjustments;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.PartyAdjustments.DebitNote;
import com.wings.pages.purchase.transactions.PurchaseVoucher;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestDebitNote {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_PURCHASE_VOUCHERS="./output/temp_api_request_bodies/PurchaseVouchers.json";
    private static final String API_RESPONSE_PURCHASE_VOUCHERS="./output/api_responses/PurchaseVouchers.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9_Output.xls";

    private static final String TEMP_API_BODY_DEBIT_NOTE="./output/temp_api_request_bodies/debitNote.json";
    private static final String API_RESPONSE_DEBIT_NOTE="./output/api_responses/debitNote.json";
    private static final String OUTPUT_FILE1="./src/main/resources/menuItems/finance/transaction/475827 - Debit Note-AC_DN_2_Output.xls";

    String file = "./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9.xls";
    String dataFile="./src/main/resources/menuItems/finance/transaction/475827 - Debit Note-AC_DN_2.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void debitNote() throws Exception {
        PurchaseVoucher po = new PurchaseVoucher(driver, file);
        String purchaseVoucher=po.purchaseVoucher(TEMP_API_BODY_PURCHASE_VOUCHERS,API_RESPONSE_PURCHASE_VOUCHERS,OUTPUT_FILE);

        appLogin.logout();
        driver= appLogin.login();

        DebitNote debitNote = new DebitNote(driver, dataFile);
        debitNote.debitNote(purchaseVoucher,TEMP_API_BODY_DEBIT_NOTE,API_RESPONSE_DEBIT_NOTE,OUTPUT_FILE1);
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
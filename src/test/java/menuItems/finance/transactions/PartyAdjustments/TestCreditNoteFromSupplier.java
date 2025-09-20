package menuItems.finance.transactions.PartyAdjustments;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.PartyAdjustments.CreditNoteFromSupplier;
import com.wings.pages.purchase.transactions.PurchaseVoucher;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestCreditNoteFromSupplier {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_PURCHASE_VOUCHERS="./output/temp_api_request_bodies/PurchaseVouchers.json";
    private static final String API_RESPONSE_PURCHASE_VOUCHERS="./output/api_responses/PurchaseVouchers.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9_Output.xls";

    private static final String TEMP_API_BODY_CREDIT_NOTE_FROM_SUPPLIER="./output/temp_api_request_bodies/creditNoteFromSupplier.json";
    private static final String API_RESPONSE_CREDIT_NOTE_FROM_SUPPLIER="./output/api_responses/creditNoteFromSupplier.json";
    private static final String OUTPUT_FILE1="./src/main/resources/menuItems/finance/transaction/475833 - Credit Note from Suppliers-AC_CNFS_1_Output.xls";

    String file = "./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9.xls";
    String dataFile="./src/main/resources/menuItems/finance/transaction/475833 - Credit Note from Suppliers-AC_CNFS_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void creditNoteFromSupplier() throws Exception {
        PurchaseVoucher po = new PurchaseVoucher(driver, file);
        String purchaseVoucher=po.purchaseVoucher(TEMP_API_BODY_PURCHASE_VOUCHERS,API_RESPONSE_PURCHASE_VOUCHERS,OUTPUT_FILE);

        appLogin.logout();
        driver= appLogin.login();

        CreditNoteFromSupplier noteFromSupplier = new CreditNoteFromSupplier(driver, dataFile);
        noteFromSupplier.creditNoteFromSupplier(purchaseVoucher,TEMP_API_BODY_CREDIT_NOTE_FROM_SUPPLIER,API_RESPONSE_CREDIT_NOTE_FROM_SUPPLIER,OUTPUT_FILE1);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
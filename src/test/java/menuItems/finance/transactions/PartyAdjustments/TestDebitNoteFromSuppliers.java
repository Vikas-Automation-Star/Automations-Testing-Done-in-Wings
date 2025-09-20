package menuItems.finance.transactions.PartyAdjustments;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.PartyAdjustments.DebitNoteFromSuppliers;
import com.wings.pages.purchase.transactions.PurchaseVoucher;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestDebitNoteFromSuppliers {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_DEBIT_NOTE_FROM_SUPPLIER="./output/temp_api_request_bodies/debitNoteFromSupplier.json";
    private static final String API_RESPONSE_DEBIT_NOTE_FROM_SUPPLIER="./output/api_responses/debitNoteFromSupplier.json";
    private static final String OUTPUT_FILE1="./src/main/resources/menuItems/finance/transaction/460743 - Debit Note from Suppliers-AC_DNFS_1_Output.xls";

    private static final String TEMP_API_BODY_PURCHASE_VOUCHERS="./output/temp_api_request_bodies/PurchaseVouchers.json";
    private static final String API_RESPONSE_PURCHASE_VOUCHERS="./output/api_responses/PurchaseVouchers.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9_Output.xls";

    String file = "./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9.xls";
    String dataFile="./src/main/resources/menuItems/finance/transaction/460743 - Debit Note from Suppliers-AC_DNFS_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void debitNoteFromSupplier() throws Exception {
        PurchaseVoucher po = new PurchaseVoucher(driver, file);
        String purchaseVoucher=po.purchaseVoucher(TEMP_API_BODY_PURCHASE_VOUCHERS,API_RESPONSE_PURCHASE_VOUCHERS,OUTPUT_FILE);

        appLogin.logout();
        driver= appLogin.login();

        DebitNoteFromSuppliers noteFromSuppliers = new DebitNoteFromSuppliers(driver, dataFile);
        noteFromSuppliers.debitNoteFromSupplier(purchaseVoucher,TEMP_API_BODY_DEBIT_NOTE_FROM_SUPPLIER,API_RESPONSE_DEBIT_NOTE_FROM_SUPPLIER,OUTPUT_FILE1);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}

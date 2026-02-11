package tradeTesting.finance.transactions.partyAdjustments;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.finance.transactions.receipts.ReceiptsFromParties;

import java.io.IOException;

public class TestDebitNote {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_PURCHASE_VOUCHERS="./output/temp_api_request_bodies/PurchaseVouchers.json";
    private static final String API_RESPONSE_PURCHASE_VOUCHERS="./output/api_responses/PurchaseVouchers.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9_Output.xls";

    private static final String TEMP_API_BODY_DEBIT_NOTE="./output/temp_api_request_bodies/DebitNote.json";
    private static final String API_RESPONSE_DEBIT_NOTE="./output/api_responses/DebitNote.json";
    private static final String OUTPUT_FILE1="./src/main/resources/tradeAutomation/finance/transactions/475827 - Debit Note-AC_DN_1_Output.xls";

    String dataFile = "./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9.xls";
    String dataFile1="./src/main/resources/tradeAutomation/finance/transactions/475827 - Debit Note-AC_DN_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void debitNote() throws Exception {
//        ReceiptsFromParties parties=new ReceiptsFromParties(driver,dataFile1);
//        parties.receiptFromParties("");

//        appLogin.logout();
//        driver= appLogin.tradeLogin();

        DebitNote debitNote=new DebitNote(driver,dataFile1);
        debitNote.debitNote("SRWR 1","","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

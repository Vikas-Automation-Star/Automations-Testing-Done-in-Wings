package tradeTesting.finance.transactions.partyAdjustments;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestProductWiseDebitNote {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_ProductWiseDebitNote="./output/temp_api_request_bodies/ProductWiseDebitNote.json";
    private static final String API_RESPONSE_ProductWiseDebitNote="./output/api_responses/ProductWiseDebitNote.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/finance/transactions/495163 - Product Wise Debit Note-Trd_PWDN_2_PV_7_Output.xls";

    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/495163 - Product Wise Debit Note-Trd_PWDN_2_PV_7.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void debitNoteFromSupplier() throws Exception {
        ProductWiseDebitNote wiseDebitNote=new ProductWiseDebitNote(driver,dataFile);
        wiseDebitNote.productWiseDebitNote("PVAO 1","PV 7","","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

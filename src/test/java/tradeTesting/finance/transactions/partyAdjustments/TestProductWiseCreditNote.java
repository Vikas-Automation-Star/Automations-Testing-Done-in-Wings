package tradeTesting.finance.transactions.partyAdjustments;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestProductWiseCreditNote {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_ProductWiseCreditNote="./output/temp_api_request_bodies/ProductWiseCreditNote.json";
    private static final String API_RESPONSE_ProductWiseCreditNote="./output/api_responses/ProductWiseCreditNote.json";
    private static final String OUTPUT_FILE="./src/main/resources/tradeAutomation/finance/transactions/494586 - Product Wise Credit Note-Trd_PWCN_2_Output.xls";

    String dataFile="./src/main/resources/tradeAutomation/finance/transactions/494586 - Product Wise Credit Note-Trd_PWCN_2.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void debitNoteFromSupplier() throws Exception {
        ProductWiseCreditNote wiseCreditNote =new ProductWiseCreditNote(driver,dataFile);
        wiseCreditNote.productWiseCreditNote("SIAO 1","","","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}

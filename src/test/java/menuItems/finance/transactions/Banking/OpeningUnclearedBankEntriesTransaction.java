package menuItems.finance.transactions.Banking;

import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Banking.OpeningUnclearedBankEntries;
import java.awt.*;
import java.io.IOException;

public class OpeningUnclearedBankEntriesTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_OPENING_UNCLEARED_BANKENTRIES ="./output/temp_api_request_bodies/OpeningUnclearedBankEntries.json";
    private static final String API_RESPONSE_OPENING_UNCLEARED_BANKENTRIES ="./output/api_responses/OpeningUnclearedBankEntries.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/finance/transaction/458165 - Opening Uncleared Bank Entries-AC_OUCBE_1_Output.xls";

    String dataFile = "./src/main/resources/menuItems/finance/transaction/458165 - Opening Uncleared Bank Entries-AC_OUCBE_1.xls";

    @BeforeTest
    public void beforeTest() throws Exception {
        driver = appLogin.login();
    }

    @Test
    public void openingUnclearedBankEntries() throws Exception {
        OpeningUnclearedBankEntries unclearedBankEntries = new OpeningUnclearedBankEntries(driver, dataFile);
        unclearedBankEntries.unclearedBankEntries(TEMP_API_BODY_OPENING_UNCLEARED_BANKENTRIES, API_RESPONSE_OPENING_UNCLEARED_BANKENTRIES,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
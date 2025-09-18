package menuItems.finance.transactions.OpeningBalances;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.OpeningBalances.PartyOpeningBalances;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestPartyOpeningBalances {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    private static final String TEMP_API_BODY_PARTY_OPENING_BALANCES="./output/temp_api_request_bodies/partyOpeningBalances.json";
    private static final String API_RESPONSE_PARTY_OPENING_BALANCES="./output/api_responses/partyOpeningBalances.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/finance/transaction/471552 - Party Opening Balances-AC_POB_1_Output.xls";
    String dataFile = "./src/main/resources/menuItems/finance/transaction/471552 - Party Opening Balances-AC_POB_1.xls";

    @BeforeTest
    public void beforeTest() throws Exception {
        driver = appLogin.login();
    }

    @Test
    public void partyOpeningBalance() throws Exception {
        PartyOpeningBalances partyOpeningBalances = new PartyOpeningBalances(driver, dataFile);
        partyOpeningBalances.partyOpeningBalance(TEMP_API_BODY_PARTY_OPENING_BALANCES,API_RESPONSE_PARTY_OPENING_BALANCES,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
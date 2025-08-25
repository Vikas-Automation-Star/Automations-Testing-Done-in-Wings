package menuItems.finance.transactions.OpeningBalances;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.OpeningBalances.OpeningBalance;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class OpeningBalancesTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    private static final String TEMP_API_OPENING_BALANCES="./output/temp_api_request_bodies/openingBalances.json";
    private static final String API_RESPONSE_OPENING_BALANCES="./output/api_responses/openingBalances.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/finance/transaction/446452 - Opening Balances-AC_OB_3_Output.xls";
    String dataFile = "./src/main/resources/menuItems/finance/transaction/446452 - Opening Balances-AC_OB_3.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void openingBalance() throws Exception {
        OpeningBalance openingBalance = new OpeningBalance(driver, dataFile);
        openingBalance.openingBalance(TEMP_API_OPENING_BALANCES,API_RESPONSE_OPENING_BALANCES,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
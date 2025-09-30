package menuItems.Taxes.transactions.TCS;

import com.wings.pages.taxes.transactions.TCS.TCSOpeningBalances;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestTCSOpeningBalances {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    private static final String TEMP_API_TCS_OPENING_BALANCES = "./output/temp_api_request_bodies/tcsOpeningBalances.json";
    private static final String API_RESPONSE_TCS_OPENING_BALANCES = "./output/api_responses/tcsOpeningBalances.json";
    private static final String OUTPUT_FILE_TCS_OPENING_BALANCES = "./src/main/resources/menuItems/Taxes/transactions/TCS/458331 - TCS Opening Balances-AC_TCSOP_1_Output.xls";

    String file = "./src/main/resources/menuItems/Taxes/transactions/TCS/458331 - TCS Opening Balances-AC_TCSOP_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void tcsOpeningBalancesTransaction() throws Exception {
        TCSOpeningBalances tcsOpeningBalances=new TCSOpeningBalances(driver,file);
        tcsOpeningBalances.tcsOpeningBalances(TEMP_API_TCS_OPENING_BALANCES,API_RESPONSE_TCS_OPENING_BALANCES,OUTPUT_FILE_TCS_OPENING_BALANCES);

    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
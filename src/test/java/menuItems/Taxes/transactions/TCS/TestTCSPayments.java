package menuItems.Taxes.transactions.TCS;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.transactions.TCS.TCSOpeningBalances;
import com.wings.pages.taxes.transactions.TCS.TCSPayments;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestTCSPayments {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_TCS_OPENING_BALANCES = "./output/temp_api_request_bodies/tcsOpeningBalances.json";
    private static final String API_RESPONSE_TCS_OPENING_BALANCES = "./output/api_responses/tcsOpeningBalances.json";
    private static final String OUTPUT_FILE_TCS_OPENING_BALANCES = "./src/main/resources/menuItems/Taxes/transactions/TCS/458331 - TCS Opening Balances-AC_TCSOP_1_Output.xls";

    private static final String TEMP_API_TCS_PAYMENTS="./output/temp_api_request_bodies/tcsPayments.json";
    private static final String API_RESPONSE_TCS_PAYMENTS="./output/api_responses/tcsPayments.json";
    private static final String OUTPUT_FILE_TCS_PAYMENTS="./src/main/resources/menuItems/Taxes/transactions/TCS/458333 - TCS Payments-AC_TCS_1_Output.xls";

    String file = "./src/main/resources/menuItems/Taxes/transactions/TCS/458331 - TCS Opening Balances-AC_TCSOP_1.xls";
    String file1 = "./src/main/resources/menuItems/Taxes/transactions/TCS/458333 - TCS Payments-AC_TCS_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void tcsPaymentsTransaction() throws Exception {
        TCSOpeningBalances tcsOpeningBalances=new TCSOpeningBalances(driver,file);
        String openingBalances =tcsOpeningBalances.tcsOpeningBalances(TEMP_API_TCS_OPENING_BALANCES,API_RESPONSE_TCS_OPENING_BALANCES,OUTPUT_FILE_TCS_OPENING_BALANCES);

        appLogin.logout();
        driver = appLogin.login();

        TCSPayments tcsPayments=new TCSPayments(driver,file1);
        tcsPayments.tcsPayments(openingBalances,TEMP_API_TCS_PAYMENTS,API_RESPONSE_TCS_PAYMENTS,OUTPUT_FILE_TCS_PAYMENTS);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

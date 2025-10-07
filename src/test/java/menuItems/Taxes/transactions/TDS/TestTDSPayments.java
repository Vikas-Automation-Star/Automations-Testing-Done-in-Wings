package menuItems.Taxes.transactions.TDS;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.transactions.TDS.TDSOpeningBalances;
import com.wings.pages.taxes.transactions.TDS.TDSPayments;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestTDSPayments {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_TDS_OPENING_BALANCES="./output/temp_api_request_bodies/tdsOpeningBalances.json";
    private static final String API_RESPONSE_TDS_OPENING_BALANCES="./output/api_responses/tdsOpeningBalances.json";
    private static final String OUTPUT_FILE_TDS_OPENING_BALANCES="./src/main/resources/menuItems/Taxes/transactions/TDS/458696 - TDS Opening Balances-AC_TOP_1_Output.xls";

    private static final String TEMP_API_TDS_PAYMENTS="./output/temp_api_request_bodies/tdsOpeningBalances.json";
    private static final String API_RESPONSE_TDS_PAYMENTS="./output/api_responses/tdsOpeningBalances.json";
    private static final String OUTPUT_FILE_TDS_PAYMENTS="./src/main/resources/menuItems/Taxes/transactions/TDS/458696 - TDS Opening Balances-AC_TOP_1_Output.xls";

    String file = "./src/main/resources/menuItems/Taxes/transactions/TDS/458334 - TDS Payments-AC_TP_1.xls";
    String file1 = "./src/main/resources/menuItems/Taxes/transactions/TDS/458696 - TDS Opening Balances-AC_TOP_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void tdsPaymentsTransaction() throws Exception {
        TDSOpeningBalances tdsOpeningBalances=new TDSOpeningBalances(driver,file1);
        String openingBalanceVoucher=tdsOpeningBalances.tdsOpeningBalances(TEMP_API_TDS_OPENING_BALANCES,API_RESPONSE_TDS_OPENING_BALANCES,OUTPUT_FILE_TDS_OPENING_BALANCES);

        appLogin.logout();
        driver = appLogin.login();

        TDSPayments tdsPayments=new TDSPayments(driver,file);
        tdsPayments.tdsPayments(openingBalanceVoucher,TEMP_API_TDS_PAYMENTS,API_RESPONSE_TDS_PAYMENTS,OUTPUT_FILE_TDS_PAYMENTS);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

package menuItems.finance.transactions.Payments;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Payments.CashTransfer;
import java.awt.*;
import java.io.IOException;

public class TestCashTransfer {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    private static final String TEMP_API_BODY_CASH_TRANSFER="./output/temp_api_request_bodies/cashTransfer.json";
    private static final String API_RESPONSE_CASH_TRANSFER ="./output/api_responses/cashTransfer.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/finance/transaction/454215 - Cash Transfers-AC_CT_1_Output.xls";

    String dataFile = "./src/main/resources/menuItems/finance/transaction/454215 - Cash Transfers-AC_CT_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void cashTransfer() throws Exception {
        CashTransfer cashTransfer = new CashTransfer(driver, dataFile);
        cashTransfer.cashTransfer(TEMP_API_BODY_CASH_TRANSFER,API_RESPONSE_CASH_TRANSFER,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
package menuItems.finance.transactions.Banking;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Banking.CashDepositsAndWithdrawls;
import java.io.IOException;

public class CashDepositsAndWithdrawalsTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    private static final String TEMP_API_BODY_CASHDEPOSIT_WITHDRAWAL="./output/temp_api_request_bodies/cashDepositAndWithdrawal.json";
    private static final String API_RESPONSE_CASHDEPOSIT_WITHDRAWAL="./output/api_responses/cashDepositAndWithdrawal.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/finance/transaction/458329 - Cash Deposits and withdrawals-AC_CE_1_Output.xls";
    String dataFile = "./src/main/resources/menuItems/finance/transaction/458329 - Cash Deposits and withdrawals-AC_CE_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void cashDepositandWithdrawl() throws Exception {
        CashDepositsAndWithdrawls depositsAndWithdrawls = new CashDepositsAndWithdrawls(driver, dataFile);
        depositsAndWithdrawls.depositAndWithdrawal(TEMP_API_BODY_CASHDEPOSIT_WITHDRAWAL,API_RESPONSE_CASHDEPOSIT_WITHDRAWAL,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
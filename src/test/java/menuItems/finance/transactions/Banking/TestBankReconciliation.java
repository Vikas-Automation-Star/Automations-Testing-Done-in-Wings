package menuItems.finance.transactions.Banking;

import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Banking.BankReconciliation;

import java.io.IOException;

public class TestBankReconciliation {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_BANK_RECONCILIATION="./output/temp_api_request_bodies/bankReconciliation.json";
    private static final String API_RESPONSE_BANK_RECONCILIATION="./output/api_responses/bankReconciliation.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/finance/transaction/439444 - Bank Reconciliation-AC_BNKRECO_1_Output.xls";

    String dataFile = "./src/main/resources/menuItems/finance/transaction/439444 - Bank Reconciliation-AC_BNKRECO_1.xls";

    @BeforeTest
    public void beforeTest() throws Exception {
        driver = appLogin.login();
    }

    @Test
    public void bankReconciliation() throws Exception {
        BankReconciliation reconciliation = new BankReconciliation(driver, dataFile);
        reconciliation.bankReconciliation(TEMP_API_BODY_BANK_RECONCILIATION,API_RESPONSE_BANK_RECONCILIATION,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
package menuItems.finance.transactions.Banking;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Banking.DepositPostDatedCheques;
import java.io.IOException;

public class DepositPostDatedChequesTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_DEPOSIT_POSTDATED_CHEQUES="./output/temp_api_request_bodies/depositPostDatedCheques.json";
    private static final String API_RESPONSE_DEPOSIT_POSTDATED_CHEQUES="./output/api_responses/depositPostDatedCheques.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/finance/transaction/458330 - Deposit Post Dated Cheques-AC_DPDC_1_Output.xls";

    String dataFile = "./src/main/resources/menuItems/finance/transaction/458330 - Deposit Post Dated Cheques-AC_DPDC_1.xls";

    @BeforeTest
    public void beforeTest() throws Exception {
        driver = appLogin.login();
    }

    @Test
    public void postDatedCheques() throws Exception {
        DepositPostDatedCheques postDatedCheques = new DepositPostDatedCheques(driver, dataFile);
        postDatedCheques.postDatedChques(TEMP_API_BODY_DEPOSIT_POSTDATED_CHEQUES,API_RESPONSE_DEPOSIT_POSTDATED_CHEQUES,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
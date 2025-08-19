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
    String dataFile = "./src/main/resources/menuItems/finance/transaction/458330 - Deposit Post Dated Cheques-AC_DPDC_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.login();
    }

    @Test
    public void postDatedCheques() throws IOException, ParseException, InterruptedException {
        DepositPostDatedCheques postDatedCheques = new DepositPostDatedCheques(driver, dataFile);
        postDatedCheques.postDatedChques();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
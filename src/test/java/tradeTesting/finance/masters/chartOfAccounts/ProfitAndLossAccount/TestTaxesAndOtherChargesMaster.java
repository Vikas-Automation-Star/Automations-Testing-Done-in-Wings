package tradeTesting.finance.masters.chartOfAccounts.ProfitAndLossAccount;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.finance.masters.ChartOfAccounts.ProfitAndLossAccount.CreateTaxesAndOtherChargesMaster;

import java.awt.*;
import java.io.IOException;

public class TestTaxesAndOtherChargesMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String datafile="./src/main/resources/tradeAutomation/finance/masters/taxesAndOtherChargesMaster.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testTaxesAndOtherChargesMaster() throws IOException, ParseException, InterruptedException, AWTException {
        CreateTaxesAndOtherChargesMaster taxesAndOtherChargesMaster=new CreateTaxesAndOtherChargesMaster(driver,datafile);
        taxesAndOtherChargesMaster.createTaxesAndOtherChargesMaster();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

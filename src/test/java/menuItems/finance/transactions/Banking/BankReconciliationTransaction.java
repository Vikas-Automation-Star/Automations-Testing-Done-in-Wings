package menuItems.finance.transactions.Banking;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Banking.BankReconciliation;
import java.awt.*;
import java.io.IOException;

public class BankReconciliationTransaction {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/bankReconciliation.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void bankReconciliation() throws IOException, ParseException, InterruptedException, AWTException {
        BankReconciliation reconciliation=new BankReconciliation(driver,dataFile);
        reconciliation.bankReconciliation();
    }

//    @AfterTest
//    public void afterTest(){
//        appLogin.logout();
//    }
}

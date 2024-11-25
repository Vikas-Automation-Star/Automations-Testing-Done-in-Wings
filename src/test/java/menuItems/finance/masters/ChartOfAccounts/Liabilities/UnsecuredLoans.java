package menuItems.finance.masters.ChartOfAccounts.Liabilities;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.ChartOfAccounts.Liabilities.UnsecuredLoansMaster;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class UnsecuredLoans {

        WindowsDriver driver;
        AppLogin login=new AppLogin();
        String dataFile="./src/main/resources/menuItems/finance/masters/UnsecuredLoans.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver=login.launchSingleUserApp();
            login.singleUserLogin();
        }

        @Test
        public void UnsecuredLoans() throws InterruptedException, IOException, ParseException, AWTException {
            UnsecuredLoansMaster unecuredLoansMaster=new UnsecuredLoansMaster(driver,dataFile);
            unecuredLoansMaster.unSecuredLoans();
        }

        @AfterTest
        public void afterTest(){
            login.logout();
        }
    }
package menuItems.finance.masters.ChartOfAccounts.Assests;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.ChartOfAccounts.Assests.InvestmentsMaster;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class Investments {

        WindowsDriver driver;
        AppLogin login=new AppLogin();
        String dataFile="./src/main/resources/menuItems/finance/masters/investments.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver=login.launchSingleUserApp();
            login.singleUserLogin();
        }

        @Test
        public void investmentsMaster() throws InterruptedException, IOException, ParseException, AWTException {
            InvestmentsMaster investmentsMaster=new InvestmentsMaster(driver,dataFile);
            investmentsMaster.investments();
        }

        @AfterTest
        public void afterTest(){
            login.logout();
        }
    }
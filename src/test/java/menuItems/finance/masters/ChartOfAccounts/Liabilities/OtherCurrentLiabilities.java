package menuItems.finance.masters.ChartOfAccounts.Liabilities;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.ChartOfAccounts.Liabilities.OtherCurrentLiabilitiesMaster;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class OtherCurrentLiabilities {

        WindowsDriver driver;
        AppLogin login=new AppLogin();
        String dataFile="./src/main/resources/menuItems/finance/masters/otherCurrentLiabilities.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver=login.launchSingleUserApp();
            login.singleUserLogin();
        }

        @Test
        public void otherCurrentLiabilities() throws InterruptedException, IOException, ParseException, AWTException {
            OtherCurrentLiabilitiesMaster currentLiabilitiesMaster=new OtherCurrentLiabilitiesMaster(driver,dataFile);
            currentLiabilitiesMaster.otherCurrentLiabilities();
        }

        @AfterTest
        public void afterTest(){
            login.logout();
        }
    }

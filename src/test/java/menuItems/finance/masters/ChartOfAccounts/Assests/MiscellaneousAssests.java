package menuItems.finance.masters.ChartOfAccounts.Assests;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.ChartOfAccounts.Assests.MiscellaneousAssestsMaster;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class MiscellaneousAssests {

        WindowsDriver driver;
        AppLogin login=new AppLogin();
        String dataFile="./src/main/resources/menuItems/finance/masters/miscellaneousAssests.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver=login.launchSingleUserApp();
            login.singleUserLogin();
        }

        @Test
        public void miscellaneousAssest() throws InterruptedException, IOException, ParseException, AWTException {
            MiscellaneousAssestsMaster miscellaneousAssestsMaster=new MiscellaneousAssestsMaster(driver,dataFile);
            miscellaneousAssestsMaster.miscellaneousAssests();
        }

        @AfterTest
        public void afterTest(){
            login.logout();
        }
    }

package menuItems.finance.masters.ChartOfAccounts;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.ChartOfAccounts.SundryDebtorsMaster;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class SundryDebtors {
        WindowsDriver driver;
        AppLogin login=new AppLogin();
        String dataFile="./src/main/resources/menuItems/finance/masters/sundryDebtors.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver=login.launchSingleUserApp();
            login.singleUserLogin();
        }

        @Test
        public void sundryDebtors() throws InterruptedException, IOException, ParseException, AWTException {
            SundryDebtorsMaster debtorsMaster=new SundryDebtorsMaster(driver,dataFile);
            debtorsMaster.sundryDebtors();
        }

        @AfterTest
        public void afterTest(){
            login.logout();
        }
    }

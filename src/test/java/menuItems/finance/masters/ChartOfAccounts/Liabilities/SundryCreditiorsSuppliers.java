package menuItems.finance.masters.ChartOfAccounts.Liabilities;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.ChartOfAccounts.Liabilities.SundryCreditorsSuppliersMaster;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class SundryCreditiorsSuppliers {

        WindowsDriver driver;
        AppLogin login=new AppLogin();
        String dataFile="./src/main/resources/menuItems/finance/masters/sundryCreditors.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver=login.launchSingleUserApp();
            login.singleUserLogin();
        }

        @Test
        public void sundryCreditors() throws InterruptedException, IOException, ParseException, AWTException {
            SundryCreditorsSuppliersMaster creditorsSuppliersMaster=new SundryCreditorsSuppliersMaster(driver,dataFile);
            creditorsSuppliersMaster.sundryCreditors();
        }

        @AfterTest
        public void afterTest(){
            login.logout();
        }
    }

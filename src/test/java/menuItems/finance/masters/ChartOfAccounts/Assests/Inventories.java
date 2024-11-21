package menuItems.finance.masters.ChartOfAccounts.Assests;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.ChartOfAccounts.InventoriesMaster;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class Inventories {
        WindowsDriver driver;
        AppLogin login=new AppLogin();
        String dataFile="./src/main/resources/menuItems/finance/masters/inventories.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver=login.launchSingleUserApp();
            login.singleUserLogin();
        }

        @Test
        public void inventoriesMaster() throws InterruptedException, IOException, ParseException, AWTException {
            InventoriesMaster inventoriesMaster=new InventoriesMaster(driver,dataFile);
            inventoriesMaster.inventories();
        }

        @AfterTest
        public void afterTest(){
            login.logout();
        }
    }

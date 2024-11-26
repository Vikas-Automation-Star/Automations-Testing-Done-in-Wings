package menuItems.finance.masters.ChartOfAccounts.ProfitAndLoss;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.ChartOfAccounts.ProfitAndLoss.OpeningAndClosingStocksMaster;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class OpeningAndClosingStock {
        WindowsDriver driver;
        AppLogin login=new AppLogin();
        String dataFile="./src/main/resources/menuItems/finance/masters/openCloseStock.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver=login.launchSingleUserApp();
            login.singleUserLogin();
        }

        @Test
        public void openingClosingStocks() throws InterruptedException, IOException, ParseException, AWTException {
            OpeningAndClosingStocksMaster stocksMaster=new OpeningAndClosingStocksMaster(driver,dataFile);
            stocksMaster.openingClosingStock();
        }

        @AfterTest
        public void afterTest(){
            login.logout();
        }
    }
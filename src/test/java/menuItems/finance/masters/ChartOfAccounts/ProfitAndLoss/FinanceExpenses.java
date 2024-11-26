package menuItems.finance.masters.ChartOfAccounts.ProfitAndLoss;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.ChartOfAccounts.ProfitAndLoss.FinanceExpensesMaster;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class FinanceExpenses {
        WindowsDriver driver;
        AppLogin login=new AppLogin();
        String dataFile="./src/main/resources/menuItems/finance/masters/financeExpenses.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver=login.launchSingleUserApp();
            login.singleUserLogin();
        }

        @Test
        public void openingClosingStocks() throws InterruptedException, IOException, ParseException, AWTException {
            FinanceExpensesMaster financeExpensesMaster=new FinanceExpensesMaster(driver,dataFile);
            financeExpensesMaster.financeExpenses();
        }

        @AfterTest
        public void afterTest(){
            login.logout();
        }
    }

package menuItems.finance.masters.ChartOfAccounts.Assests;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.ChartOfAccounts.BankMaster;

import java.awt.*;
import java.io.IOException;

public class Bank{
    WindowsDriver driver;
    AppLogin login=new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/masters/bank.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=login.launchSingleUserApp();
        login.singleUserLogin();
    }

    @Test
    public void bankMaster() throws InterruptedException, IOException, ParseException, AWTException {
        BankMaster master=new BankMaster(driver,dataFile);
        master.bank();
    }

    @AfterTest
    public void afterTest(){
        login.logout();
    }
}

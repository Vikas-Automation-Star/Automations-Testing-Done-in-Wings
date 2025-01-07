package menuItems.finance.masters.ManageCurrencies;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.ManageCurrencies.ManageCurrencies;
import java.io.IOException;

public class ManageCurrencyMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/masters/manageCurrency.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void manageCurrency() throws InterruptedException, IOException, ParseException {
        ManageCurrencies currencies=new ManageCurrencies(driver,dataFile);
        currencies.manageCurrency();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}

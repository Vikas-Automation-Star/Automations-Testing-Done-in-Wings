package menuItems.finance.transactions.OpeningBalances;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.OpeningBalances.ManualStockValuation;
import java.awt.*;
import java.io.IOException;

public class ManualStockValuationTransaction {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/manualStockValuation.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test - Manual Stock Verification");
    }

    @Test
    public void manualStockValuation() throws IOException, ParseException, InterruptedException, AWTException {
        ManualStockValuation stockValuation=new ManualStockValuation(driver,dataFile);
        stockValuation.manualStockValuation();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
        Allure.step("After Test - Manual Stock Verification");
    }
}
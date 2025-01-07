package menuItems.inventory.transactions;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.transactions.StockConsumption;
import java.awt.*;
import java.io.IOException;

public class StockConsumptionTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file="./src/main/resources/menuItems/inventory/transactions/stockConsumption.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test - Stock Consumption");
    }

    @Test
    public void stockConsumption() throws InterruptedException, AWTException, IOException, ParseException {
        StockConsumption consumptionTrans=new StockConsumption(driver,file);
        consumptionTrans.stockConsumption();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
        Allure.step("After Test - Stock Consumption");
    }
}
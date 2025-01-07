package menuItems.inventory.transactions;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.transactions.OpeningStock;
import java.awt.*;
import java.io.IOException;

public class OpeningStockTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file="./src/main/resources/menuItems/inventory/transactions/openingStock.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test - Opening Stock");
    }

    @Test
    public void openStock() throws InterruptedException, AWTException, IOException, ParseException {
        OpeningStock stockTrans=new OpeningStock(driver,file);
        stockTrans.stockOpen();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
        Allure.step("After Test - Opening Stock");
    }
}
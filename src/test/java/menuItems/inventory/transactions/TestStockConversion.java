package menuItems.inventory.transactions;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.transactions.StockConversion;

import java.awt.*;
import java.io.IOException;

public class TestStockConversion {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String file = "./src/main/resources/menuItems/inventory/transactions/stockConversion.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(file,"stockConversation","userName"),common.getData(file,"stockConversation","password"));
    }
    @Test
    public void stockConversion() throws InterruptedException, AWTException, IOException, ParseException {
        StockConversion conversionTrans = new StockConversion(driver, file);
        conversionTrans.stockConversion();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
package menuItems.inventory.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.transactions.StockCreation;
import java.awt.*;
import java.io.IOException;

public class StockCreationTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file="./src/main/resources/menuItems/inventory/transactions/stockCreation.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void stockCreation() throws InterruptedException, AWTException, IOException, ParseException {
        StockCreation creationTrans=new StockCreation(driver,file);
        creationTrans.stockCreation();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

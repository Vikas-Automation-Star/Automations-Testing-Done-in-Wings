package menuItems.inventory.transactions;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.transactions.StockCreation;
import java.awt.*;
import java.io.IOException;

public class TestStockCreation {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String file = "./src/main/resources/menuItems/inventory/transactions/476947 - Stock Creation-AC_SCR_16.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = appLogin.login();
    }

    @Test
    public void stockCreation() throws InterruptedException, AWTException, IOException, ParseException {
        StockCreation creationTrans = new StockCreation(driver, file);
        creationTrans.stockCreation();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }

}
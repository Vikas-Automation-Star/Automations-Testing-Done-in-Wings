package tradeTesting.inventory.transctions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.inventory.transactions.GodownDamagesTrade;

import java.io.IOException;

public class TestGodownDamages {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String outputFile="./";
    String dataFile="./src/main/resources/tradeAutomation/inventory/transactions/487395 - Godown Damage Goods-Trd_GD_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testGodownDamages() throws Exception {
        GodownDamagesTrade godownDamagesTrade=new GodownDamagesTrade(driver,dataFile);
        godownDamagesTrade.godownDamagesTrade("","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

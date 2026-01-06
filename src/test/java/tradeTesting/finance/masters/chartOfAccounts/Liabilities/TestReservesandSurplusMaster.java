package tradeTesting.finance.masters.chartOfAccounts.Liabilities;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.finance.masters.ChartOfAccounts.Liabilities.CreateReservesAndSurplusMaster;
import java.awt.*;
import java.io.IOException;

public class TestReservesandSurplusMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="./src/main/resources/tradeAutomation/finance/masters/reservesAndSurplusMaster.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testReservesAndSurplusMaster() throws InterruptedException, IOException, ParseException, AWTException {
        CreateReservesAndSurplusMaster createReservesAndSurplusMaster=new CreateReservesAndSurplusMaster(driver,file);
        createReservesAndSurplusMaster.createReservesSurplusMasterTrade();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

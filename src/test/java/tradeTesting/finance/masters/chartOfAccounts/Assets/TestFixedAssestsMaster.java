package tradeTesting.finance.masters.chartOfAccounts.Assets;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.finance.masters.ChartOfAccounts.Assets.CreateFixedAssetsMaster;
import java.awt.*;
import java.io.IOException;

public class TestFixedAssestsMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="./src/main/resources/tradeAutomation/finance/masters/fixedAssetsMaster.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testFixedAssestsMaster() throws InterruptedException, IOException, ParseException, AWTException {
        CreateFixedAssetsMaster createFixedAssestsMaster=new CreateFixedAssetsMaster(driver,file);
        createFixedAssestsMaster.createFixedAssetsMasterTrade();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
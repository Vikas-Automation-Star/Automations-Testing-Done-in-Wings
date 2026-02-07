package tradeTesting.finance.masters;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestDrawnOnBankTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/masters/drawnOnBankMaster.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testDrawnOnBankTrade() throws IOException, ParseException, InterruptedException, AWTException {
        CreateDrawnOnBanksMaster drawnOnBanksMaster=new CreateDrawnOnBanksMaster(driver,dataFile);
        drawnOnBanksMaster.createDrawnOnBanksMaster();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
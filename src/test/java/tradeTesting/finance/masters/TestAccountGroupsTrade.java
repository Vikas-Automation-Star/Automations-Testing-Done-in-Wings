package tradeTesting.finance.masters;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestAccountGroupsTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/masters/accountGroupsMaster.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testAccountGroupsTrade() throws IOException, ParseException, InterruptedException, AWTException {
        CreateAccountGroupsMaster accountGroupsMaster=new CreateAccountGroupsMaster(driver,dataFile);
        accountGroupsMaster.createAccountGroupsMaster();

    }

    @AfterTest
    public void afterTest() throws IOException, ParseException, InterruptedException {
        appLogin.logout();
    }
}
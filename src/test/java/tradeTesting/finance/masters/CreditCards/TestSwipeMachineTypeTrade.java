package tradeTesting.finance.masters.CreditCards;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tradeTesting.finance.masters.creditCards.CreateSwipeMachineTypeMaster;

import java.io.IOException;

public class TestSwipeMachineTypeTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/finance/masters/swipeMachineTypeMaster.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testSwipeMachineTypeTrade() throws IOException, ParseException, InterruptedException {
        CreateSwipeMachineTypeMaster swipeMachineTypeMaster=new CreateSwipeMachineTypeMaster(driver,dataFile);
        swipeMachineTypeMaster.createSwipeMachineTypeMaster();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
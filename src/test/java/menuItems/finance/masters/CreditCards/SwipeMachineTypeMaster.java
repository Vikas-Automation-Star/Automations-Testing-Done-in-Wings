package menuItems.finance.masters.CreditCards;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.CreditCards.SwipeMachineType;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class SwipeMachineTypeMaster {
    WindowsDriver driver;
    AppLogin login = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/finance/masters/swipeTypeMachine.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = login.launchSingleUserApp();
        login.singleUserLogin();
    }

    @Test
    public void bankMaster() throws InterruptedException, IOException, ParseException, AWTException {
        SwipeMachineType machineType = new SwipeMachineType(driver, dataFile);
        machineType.createSwipeMachineType();
    }

    @AfterTest
    public void afterTest() throws IOException {
        login.logout();
    }

}

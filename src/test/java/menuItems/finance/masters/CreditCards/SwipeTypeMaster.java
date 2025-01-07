package menuItems.finance.masters.CreditCards;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.CreditCards.SwipeType;

import java.io.IOException;

public class SwipeTypeMaster {
    WindowsDriver driver;
    AppLogin login=new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/masters/swipetype.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=login.launchSingleUserApp();
        login.singleUserLogin();
    }

    @Test
    public void bankMaster() throws InterruptedException, IOException, ParseException {
        SwipeType swipeType=new SwipeType(driver,dataFile);
        swipeType.createSwipeType();
    }

    @AfterTest
    public void afterTest() throws IOException{
        login.logout();
    }
}

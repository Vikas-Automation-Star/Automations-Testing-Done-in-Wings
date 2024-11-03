package menuItems.finance.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.DrawnOnBank;
import java.awt.*;
import java.io.IOException;

public class DrawnOnBanksMaster {
    WindowsDriver driver;
    AppLogin login=new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/masters/drawnOnBank.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=login.launchSingleUserApp();
        login.singleUserLogin();
    }

    @Test
    public void bankDrawnMaster() throws InterruptedException, IOException, ParseException, AWTException {
        DrawnOnBank onBank=new DrawnOnBank(driver,dataFile);
        onBank.bankDraws();
    }

    @AfterTest
    public void afterTest(){
        login.logout();
    }
}

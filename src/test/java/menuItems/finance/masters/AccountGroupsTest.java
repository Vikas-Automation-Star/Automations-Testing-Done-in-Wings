package menuItems.finance.masters;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.masters.AccountGroups;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class AccountGroupsTest {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="src/main/resources/menuItems/finance/masters/accountGroups.json";

    @BeforeTest
    public void beforeTest() throws Exception {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void accountGroups() throws Exception, AWTException {
        AccountGroups accountGroups=new AccountGroups(driver,dataFile);
        accountGroups.accountGroups();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

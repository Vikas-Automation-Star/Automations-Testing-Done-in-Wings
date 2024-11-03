package menuItems.company.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.Company;
import com.wings.pages.company.masters.Reason;

import java.io.IOException;

public class createReason {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    Reason reason;
    String file="./src/main/resources/MenuItems/company/Masters/reasonData.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void newReason() throws IOException, ParseException, InterruptedException {
        reason=new Reason(driver,file);
        reason.findReason();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }

}

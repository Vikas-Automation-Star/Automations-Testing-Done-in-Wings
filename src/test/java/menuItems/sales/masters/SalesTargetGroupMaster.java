package menuItems.sales.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.masters.SalesTargetGroup;
import java.io.IOException;

public class SalesTargetGroupMaster {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file="./src/main/resources/menuItems/Sales/Masters/salesTarget.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void salesTarget() throws InterruptedException, IOException, ParseException {
        SalesTargetGroup targetGroup=new SalesTargetGroup(driver,file);
        targetGroup.salesTarget();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

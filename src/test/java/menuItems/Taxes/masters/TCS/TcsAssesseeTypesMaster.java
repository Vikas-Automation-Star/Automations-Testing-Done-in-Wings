package menuItems.Taxes.masters.TCS;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.masters.TCS.TcsAssesseeTypes;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TcsAssesseeTypesMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file ="./src/main/resources/MenuItems/Taxes/masters/TCS/TcsAssesseeTypes.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void tcsAssesseeTypesMaster() throws InterruptedException, IOException, ParseException, AWTException {
        TcsAssesseeTypes tst=new TcsAssesseeTypes(driver,file);
        tst.tcsAssesseeTypes();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

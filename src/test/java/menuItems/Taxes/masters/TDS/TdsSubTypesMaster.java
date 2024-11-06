package menuItems.Taxes.masters.TDS;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.masters.TDS.TdsSubTypes;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TdsSubTypesMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file ="./src/main/resources/MenuItems/Taxes/masters/TDS/TdsSubTypes.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void tdsSubTypesMaster() throws InterruptedException, IOException, ParseException, AWTException {
        TdsSubTypes tst=new TdsSubTypes(driver,file);
        tst.tdsSubTypes();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

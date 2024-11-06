package menuItems.Taxes.masters.TDS;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.masters.TDS.TdsTransactionNatures;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TdsTransactionNaturesMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file ="./src/main/resources/MenuItems/Taxes/masters/TDS/TdsTransactionNatures.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void tdsTransactionNaturesMaster() throws InterruptedException, IOException, ParseException, AWTException {
        TdsTransactionNatures ttn=new TdsTransactionNatures(driver,file);
        ttn.tdsTransactionNatures();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

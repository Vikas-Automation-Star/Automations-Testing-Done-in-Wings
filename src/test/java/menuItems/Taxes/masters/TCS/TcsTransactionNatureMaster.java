package menuItems.Taxes.masters.TCS;

import com.wings.pages.taxes.masters.TCS.TcsTransactionNature;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TcsTransactionNatureMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file ="./src/main/resources/MenuItems/Taxes/masters/TCS/TcsTransactionNature.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void tcsTransactionNatureMaster() throws InterruptedException, IOException, ParseException, AWTException {
        TcsTransactionNature ttn=new TcsTransactionNature(driver,file);
        ttn.tcsTransactionNature();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

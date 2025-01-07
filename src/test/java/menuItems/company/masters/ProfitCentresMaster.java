package menuItems.company.masters;

import com.wings.pages.company.masters.ProfitCentres;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class ProfitCentresMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    ProfitCentres profitCentres;
    String file="./src/main/resources/MenuItems/Company/Masters/ProfitCentres.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void profitCentres() throws IOException, ParseException, InterruptedException, AWTException {
        profitCentres =new ProfitCentres(driver,file);
        profitCentres.profitCentres();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}

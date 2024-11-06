package menuItems.company.masters;

import com.wings.pages.company.masters.TermType;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TermTypeMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="./src/main/resources/MenuItems/Company/Masters/TermTypeData.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void termType() throws IOException, ParseException, InterruptedException, AWTException {
        TermType termType=new TermType(driver,file);
        termType.termType();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }

}

package menuItems.company.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.Company;
import com.wings.pages.company.masters.Terms;

import java.awt.*;
import java.io.IOException;

public class createTerms {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    Terms terms;
    String file="./src/main/resources/MenuItems/company/Masters/termData.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void newTerms() throws IOException, ParseException, InterruptedException, AWTException {
        terms=new Terms(driver,file);
        terms.term();

    }
    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

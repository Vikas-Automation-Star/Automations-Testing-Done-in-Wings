package menuItems.company.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.Company;
import com.wings.pages.company.masters.TermTypes;

import java.io.IOException;

public class createTermType {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    TermTypes types;
    String file="./src/main/resources/MenuItems/company/Masters/termTypeData.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void newTermType() throws IOException, ParseException, InterruptedException {
        types=new TermTypes(driver,file);
        types.termType();

    }
    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

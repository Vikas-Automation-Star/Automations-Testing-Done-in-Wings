package menuItems.company.masters;

import com.wings.pages.company.masters.Departments;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class DepartmentsMaster {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Departments departments;
    String file = "./src/main/resources/MenuItems/Company/Masters/DepartmentData.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void departments() throws IOException, ParseException, InterruptedException, AWTException {
        departments=new Departments(driver,file);
        departments.departments();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }

}

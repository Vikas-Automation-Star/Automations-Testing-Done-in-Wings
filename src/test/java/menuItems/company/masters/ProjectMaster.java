package menuItems.company.masters;

import com.wings.pages.company.masters.Projects;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class ProjectMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    Projects project;
    String file="./src/main/resources/MenuItems/Company/Masters/ProjectData.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void createCostMaster() throws IOException, ParseException, InterruptedException, AWTException {
        project =new Projects(driver,file);
        project.project();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }

}

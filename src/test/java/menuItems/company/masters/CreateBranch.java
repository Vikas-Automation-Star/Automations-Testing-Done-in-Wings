package menuItems.company.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.Company;
import com.wings.pages.company.masters.Branch;

import java.awt.*;
import java.io.IOException;

public class CreateBranch {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    com.wings.pages.Company company;
    Branch newBranch;
    String file="./src/main/resources/branchData.json";


    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void newMaster() throws IOException, ParseException, InterruptedException, AWTException {
        newBranch=new Branch(driver,file);
        newBranch.branch();
    }
    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

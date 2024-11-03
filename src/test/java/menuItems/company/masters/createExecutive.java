package menuItems.company.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.Company;
import com.wings.pages.company.masters.Executive;

import java.io.IOException;

public class createExecutive {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="./src/main/resources/MenuItems/company/Masters/executiveData.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void newExec() throws IOException, ParseException, InterruptedException {
        Executive newExecutive =new Executive(driver,file);
        newExecutive.exec();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

package tradeTesting.company.masters;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestDivisions {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/tradeAutomation/company/masters/tradeDivisions.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void divisions() throws Exception {
        CreateDivisions divisions=new CreateDivisions(driver,file);
        divisions.divisions();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

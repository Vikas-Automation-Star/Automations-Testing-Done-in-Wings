package tradeTesting.sales.masters;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCombinationSchemes {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/tradeAutomation/sales/masters/tradeCombinationSchemes.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void combinationSchemes() throws Exception {
        CreateCombinationSchemes combinationSchemes=new CreateCombinationSchemes(driver,file);
        combinationSchemes.createCombinationSchemes();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

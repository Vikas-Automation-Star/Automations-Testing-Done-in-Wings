package tradeTesting.company.masters;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestTerms {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/tradeAutomation/company/masters/tradeTerms.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void terms() throws Exception {
        CreateTerms terms=new CreateTerms(driver,file);
        terms.terms();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

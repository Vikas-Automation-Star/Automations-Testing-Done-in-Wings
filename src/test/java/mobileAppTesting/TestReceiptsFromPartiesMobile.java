package mobileAppTesting;

import com.wings.pages.AppLogin;
import mobileTesing.ReceiptsFromPartiesMobile;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestReceiptsFromPartiesMobile {

    WebDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="src/main/resources/mobileTesting/receiptsFromParties.json";

    @BeforeTest
    public void setAppLogin() throws IOException, InterruptedException {
        driver= appLogin.mobileLogin();
    }

    @Test
    public void receiptsFromParties() throws InterruptedException, IOException, ParseException {
        ReceiptsFromPartiesMobile fromPartiesMobile=new ReceiptsFromPartiesMobile(driver,file);
        fromPartiesMobile.receiptsFromParties();
    }

    @AfterTest
    public void signOut() throws InterruptedException {
        appLogin.signOut();
    }

}

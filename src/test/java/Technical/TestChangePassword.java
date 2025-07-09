package Technical;

import com.wings.Technical.ChangePassword;
import com.wings.pages.AppLogin;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestChangePassword {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    Common common;
    String dataFile="./src/main/resources/Technical/technicalFeatures.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin("Super User",common.getData(dataFile,"changePassword","oldPassword"));
    }

    @Test
    public void changePassword() throws IOException, ParseException, InterruptedException {
        ChangePassword changePassword=new ChangePassword(driver,dataFile);
        changePassword.changePassword();

        appLogin.logout();

        //relogin
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin("Super User", common.getData(dataFile,"changePassword","newPassword"));
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

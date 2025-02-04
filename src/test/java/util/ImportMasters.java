package util;

import com.wings.pages.AppLogin;
import com.wings.utils.ImportingMasters;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class ImportMasters {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="src/main/resources/importMaster.json";
    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void Test() throws IOException, ParseException, InterruptedException {
        ImportingMasters im=new ImportingMasters(driver,dataFile);
        im.masterImport();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}

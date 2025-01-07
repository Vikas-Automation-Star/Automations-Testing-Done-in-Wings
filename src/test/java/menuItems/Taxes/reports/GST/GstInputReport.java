package menuItems.Taxes.reports.GST;;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.reports.GST.GstInputRepo;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class GstInputReport {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void gstInputReport() throws  InterruptedException {
        GstInputRepo report=new GstInputRepo(driver);
        report.gstInputRepo();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}

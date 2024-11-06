package menuItems.Taxes.reports.GST;;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.reports.GST.GstOutput;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class GstOutPutReport {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void gstOutPutReport() throws  InterruptedException {
        GstOutput report=new GstOutput(driver);
        report.gstOutput();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

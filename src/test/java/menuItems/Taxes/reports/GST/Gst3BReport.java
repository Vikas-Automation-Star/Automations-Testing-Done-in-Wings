package menuItems.Taxes.reports.GST;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.reports.GST.Gst3B;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class Gst3BReport {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file ="./src/main/resources/MenuItems/Taxes/reports/GST/Gst3B.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void gst3BReport() throws InterruptedException, IOException, ParseException {
        Gst3B report=new Gst3B(driver, file);
        report.gst3B();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

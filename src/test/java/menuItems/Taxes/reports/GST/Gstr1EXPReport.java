package menuItems.Taxes.reports.GST;;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.reports.GST.Gstr1EXP;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class Gstr1EXPReport {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void gstr1EXPReport() throws  InterruptedException {
        Gstr1EXP report=new Gstr1EXP(driver);
        report.gstr1EXP();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}

package menuItems.Taxes.reports.GST;;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.reports.GST.Gstr1B2CS;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class Gstr1B2CSReport {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void gstr1B2CSReport() throws  InterruptedException {
        Gstr1B2CS report=new Gstr1B2CS(driver);
        report.gstr1B2CS();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

package menuItems.Taxes.transactions.TDS;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.transactions.TDS.TdsOpeningBalences;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TdsOpeningBalencesTransaction {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file ="./src/main/resources/MenuItems/Taxes/transactions/TDS/TdsOpeningBalences.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void tdsOpeningBalencesTransaction() throws InterruptedException, IOException, ParseException, AWTException {
        TdsOpeningBalences tob=new TdsOpeningBalences(driver,file);
        tob.tdsOpeningBalences();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

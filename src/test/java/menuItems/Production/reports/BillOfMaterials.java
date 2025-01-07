package menuItems.Production.reports;

import com.wings.pages.production.reports.BillOfMaterial;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class BillOfMaterials {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();

    @BeforeTest
    public void BillOfMaterials() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void billOfMaterials() throws IOException, ParseException, InterruptedException, AWTException {
       BillOfMaterial bom=new BillOfMaterial(driver);
       bom.billOfMaterial();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}

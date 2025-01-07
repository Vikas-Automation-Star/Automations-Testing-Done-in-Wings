package menuItems.Production.reports;

//import com.wings.pages.production.reports.MaterialReturnsFromProduction;

import com.wings.pages.AppLogin;
import com.wings.pages.production.reports.MaterialReturnsFromProduction;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class MaterialReturnsFromProductionReport {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void materialReturnsFromProductionReport() throws  InterruptedException {
        MaterialReturnsFromProduction mrfp=new MaterialReturnsFromProduction(driver);
        mrfp.materialReturnsFromProduction();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}

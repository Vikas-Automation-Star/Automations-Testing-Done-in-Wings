package util;

import com.wings.pages.AppLogin;
import com.wings.utils.CompanyInitialisationGSTConfiguration;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class CompanyInitilizatonGSTconfig {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }
    @Test
    public void GSTConfigurations() throws InterruptedException, AWTException {
        CompanyInitialisationGSTConfiguration configuration=new CompanyInitialisationGSTConfiguration(driver);
        configuration.cgstConfiguration();
        configuration.sgstConfiguration();
        configuration.igstConfiguration();
        configuration.companyUnits();
        configuration.addOns();
        configuration.productionFlow();
        configuration.entryViewPrintSettings();
        configuration.inventoryFlow();
        configuration.financeFlow();
        configuration.generalSettings();
        configuration.taxesSettings();

    }
    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

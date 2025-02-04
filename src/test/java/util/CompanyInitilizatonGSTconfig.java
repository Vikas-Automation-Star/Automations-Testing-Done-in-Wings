package util;

import com.wings.pages.AppLogin;
import phase_1_TestCases.CompanyInitialisationGSTConfiguration;
import com.wings.utils.CreateCompany;
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
    CreateCompany company=new CreateCompany();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        company.login();
        company.logout();
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }
    @Test
    public void GSTConfigurations() throws InterruptedException, AWTException {
        CompanyInitialisationGSTConfiguration configuration=new CompanyInitialisationGSTConfiguration(driver);
        configuration.companyUnits();
        configuration.addOns();
        configuration.entryViewPrintSettings();
        configuration.SalesModuleConfiguration();
        configuration.purchaseModuleConfiguration();
        configuration.inventoryFlow();
        configuration.productionFlow();
        configuration.financeFlow();
        configuration.generalSettings();
        configuration.taxesSettings();
        configuration.cgstConfiguration();
        configuration.sgstConfiguration();
        configuration.igstConfiguration();
        configuration.cessConfiguration();
    }
    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

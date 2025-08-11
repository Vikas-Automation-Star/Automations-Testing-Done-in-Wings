package phase_1_TestCases;

import com.wings.pages.AppLogin;
import com.wings.pages.generalScripts.CreateCompany;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class CompanyInitilizatonGSTconfig {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    CreateCompany company = new CreateCompany();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
//        company.login();
//        company.logout();
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test(priority = 1)
    public void configureAddOns() throws InterruptedException, AWTException {
        CompanyInitialisationGSTConfiguration configuration = new CompanyInitialisationGSTConfiguration(driver);
        configuration.addOns();  // Configuring add-ons
    }

    @Test(priority = 2)
    public void configureEntryViewPrintSettings() throws InterruptedException, AWTException {
        CompanyInitialisationGSTConfiguration configuration = new CompanyInitialisationGSTConfiguration(driver);
        configuration.entryViewPrintSettings();  // Configuring entry view print settings
    }

    @Test(priority = 2)
    public void configureCompanyUnits() throws InterruptedException, AWTException {
        CompanyInitialisationGSTConfiguration configuration = new CompanyInitialisationGSTConfiguration(driver);
        configuration.companyUnits();  // Configuring company units
    }

    @Test(priority = 3)
    public void configureSalesModule() throws InterruptedException, AWTException {
        CompanyInitialisationGSTConfiguration configuration = new CompanyInitialisationGSTConfiguration(driver);
        configuration.SalesModuleConfiguration();  // Configuring sales module
    }

    @Test(priority = 4)
    public void configurePurchaseModule() throws InterruptedException, AWTException {
        CompanyInitialisationGSTConfiguration configuration = new CompanyInitialisationGSTConfiguration(driver);
        configuration.purchaseModuleConfiguration();  // Configuring purchase module
    }

    @Test(priority = 5)
    public void configureInventoryFlow() throws InterruptedException, AWTException {
        CompanyInitialisationGSTConfiguration configuration = new CompanyInitialisationGSTConfiguration(driver);
        configuration.inventoryFlow();  // Configuring inventory flow
    }

    @Test(priority = 6)
    public void configureProductionFlow() throws InterruptedException, AWTException {
        CompanyInitialisationGSTConfiguration configuration = new CompanyInitialisationGSTConfiguration(driver);
        configuration.productionFlow();  // Configuring production flow
    }

    @Test(priority = 7)
    public void configureFinanceFlow() throws InterruptedException, AWTException {
        CompanyInitialisationGSTConfiguration configuration = new CompanyInitialisationGSTConfiguration(driver);
        configuration.financeFlow();  // Configuring finance flow
    }

    @Test(priority = 8)
    public void configureGeneralSettings() throws InterruptedException, AWTException {
        CompanyInitialisationGSTConfiguration configuration = new CompanyInitialisationGSTConfiguration(driver);
        configuration.generalSettings();  // Configuring general settings
    }

    @Test(priority = 9)
    public void configureTaxesSettings() throws InterruptedException, AWTException {
        CompanyInitialisationGSTConfiguration configuration = new CompanyInitialisationGSTConfiguration(driver);
        configuration.taxesSettings();  // Configuring tax settings
    }

    @Test(priority = 10)
    public void configureCGST() throws InterruptedException, AWTException {
        CompanyInitialisationGSTConfiguration configuration = new CompanyInitialisationGSTConfiguration(driver);
        configuration.cgstConfiguration();  // Configuring CGST
    }

    @Test(priority = 11)
    public void configureSGST() throws InterruptedException, AWTException {
        CompanyInitialisationGSTConfiguration configuration = new CompanyInitialisationGSTConfiguration(driver);
        configuration.sgstConfiguration();  // Configuring SGST
    }

    @Test(priority = 12)
    public void configureIGST() throws InterruptedException, AWTException {
        CompanyInitialisationGSTConfiguration configuration = new CompanyInitialisationGSTConfiguration(driver);
        configuration.igstConfiguration();  // Configuring IGST
    }

    @Test(priority = 13)
    public void configureCESS() throws InterruptedException, AWTException {
        CompanyInitialisationGSTConfiguration configuration = new CompanyInitialisationGSTConfiguration(driver);
        configuration.cessConfiguration();  // Configuring cess
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();  // Log out after tests are done
    }
}

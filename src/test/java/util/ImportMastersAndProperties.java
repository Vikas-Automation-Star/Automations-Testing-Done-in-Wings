package util;

import com.wings.pages.AppLogin;
import com.wings.utils.ImportingMastersAndProperties;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class ImportMastersAndProperties {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "src/main/resources/importMaster.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test(priority = 1)
    public void importBranches() throws IOException, ParseException, InterruptedException {
        ImportingMastersAndProperties importing = new ImportingMastersAndProperties(driver, dataFile);
        importing.branchesMastersImporting("Branches");
    }

    @Test(priority = 2)
    public void importCustomers() throws IOException, ParseException, InterruptedException {
        ImportingMastersAndProperties importing = new ImportingMastersAndProperties(driver, dataFile);
        importing.customerMastersImport("Customers");
    }

    @Test (priority = 3)
    public void importSuppliers() throws IOException, ParseException, InterruptedException {
        ImportingMastersAndProperties importing = new ImportingMastersAndProperties(driver, dataFile);
        importing.suppliersMastersImport("Suppliers");
    }

    @Test(priority = 4)
    public void importProducts() throws IOException, ParseException, InterruptedException {
        ImportingMastersAndProperties importing = new ImportingMastersAndProperties(driver, dataFile);
        importing.productsMastersImport("Products");
    }

    @Test (priority = 5)
    public void importBankAccount() throws IOException, ParseException, InterruptedException {
        ImportingMastersAndProperties importing = new ImportingMastersAndProperties(driver, dataFile);
        importing.balanceSheetBankMastersImports("Balance Sheet");
    }

    @Test(priority = 6)
    public void importCashAccount() throws IOException, ParseException, InterruptedException {
        ImportingMastersAndProperties importing = new ImportingMastersAndProperties(driver, dataFile);
        importing.balanceSheetCashMastersImports("Balance Sheet");
    }

    @Test(priority = 7)
    public void importHsnCode() throws IOException, ParseException, InterruptedException {
        ImportingMastersAndProperties importing = new ImportingMastersAndProperties(driver, dataFile);
        importing.hsnCodesMastersImporting("HSN Codes");
    }

    @Test(priority = 8)
    public void ContactInformationGSTProperty() throws IOException, ParseException, InterruptedException {
        ImportingMastersAndProperties importing = new ImportingMastersAndProperties(driver, dataFile);
        importing.importContactInformationGSTProperty("Contact Information GST","Anjali Devi");
    }

    @Test(priority = 9)
    public void RegistrationGSTProperty() throws IOException, ParseException, InterruptedException {
        ImportingMastersAndProperties importing = new ImportingMastersAndProperties(driver, dataFile);
        importing.importRegistrationGSTProperty("Registration GST","Anjali Devi");
    }

    @Test(priority = 10)
    public void shippingAddressProperty() throws IOException, ParseException, InterruptedException {
        ImportingMastersAndProperties importing = new ImportingMastersAndProperties(driver, dataFile);
        importing.importShippingAddressProperty("Shipping Address GST","Anjali Devi");
    }

    @AfterTest
    public void afterTest() throws IOException {
         appLogin.logout();
    }
}

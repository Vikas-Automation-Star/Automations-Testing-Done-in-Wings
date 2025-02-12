package util;

import com.wings.pages.AppLogin;
import com.wings.utils.ImportingMasters;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class ImportMasters {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "src/main/resources/importMaster.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

//    @Test
    public void importBranches() throws IOException, ParseException, InterruptedException {
        ImportingMasters importing = new ImportingMasters(driver, dataFile);
        importing.branchesMastersImporting("Branches");
    }

//    @Test
    public void importSuppliers() throws IOException, ParseException, InterruptedException {
        ImportingMasters importing = new ImportingMasters(driver, dataFile);
        importing.suppliersMastersImport("Suppliers");
    }

//    @Test
    public void importCustomers() throws IOException, ParseException, InterruptedException {
        ImportingMasters importing = new ImportingMasters(driver, dataFile);
        importing.customerMastersImport("Customers");
    }

//    @Test
    public void importProducts() throws IOException, ParseException, InterruptedException {
        ImportingMasters importing = new ImportingMasters(driver, dataFile);
        importing.productsMastersImport("Products");
    }
//    @Test
    public void importHsnCode() throws IOException, ParseException, InterruptedException {
        ImportingMasters importing = new ImportingMasters(driver, dataFile);
        importing.hsnCodesMastersImporting("HSN Codes");
    }
//    @Test
    public void importBankAccount() throws IOException, ParseException, InterruptedException {
        ImportingMasters importing = new ImportingMasters(driver, dataFile);
        importing.balanceSheetBankMastersImports("Balance Sheet");
    }
//    @Test
    public void importCashAccount() throws IOException, ParseException, InterruptedException {
        ImportingMasters importing = new ImportingMasters(driver, dataFile);
        importing.balanceSheetCashMastersImports("Balance Sheet");
    }
//    @Test
    public void ContactInformationGSTProperty() throws IOException, ParseException, InterruptedException {
        ImportingMasters importing = new ImportingMasters(driver, dataFile);
        importing.importContactInformationGSTProperty("Contact Information GST","Anjali Devi");
    }
//    @Test
    public void RegistrationGSTProperty() throws IOException, ParseException, InterruptedException {
        ImportingMasters importing = new ImportingMasters(driver, dataFile);
        importing.importRegistrationGSTProperty("Registration GST","Anjali Devi");
    }
    @Test
    public void shippingAddressProperty() throws IOException, ParseException, InterruptedException {
        ImportingMasters importing = new ImportingMasters(driver, dataFile);
        importing.importShippingAddressProperty("Shipping Address GST","Anjali Devi");
    }

    @AfterTest
    public void afterTest() throws IOException {
//         appLogin.logout();
    }
}

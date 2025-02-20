package phase_1_TestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class ImportMastersAndProperties {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "src/main/resources/phase_1_List/importMaster.json";

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

    //    @Test(priority = 8)        //issue in the application accepting only for the excutive
//    public void addressAndContactInformationProperty() throws IOException, ParseException, InterruptedException {
//        ImportingMastersAndProperties importing = new ImportingMastersAndProperties(driver, dataFile);
//        importing.importAddressAndContactDetailsProperty("Address and Contact Details","Anjali Devi");
//    }

    @Test(priority = 8)
    public void BranchProperty() throws IOException, ParseException, InterruptedException {
        ImportingMastersAndProperties importing = new ImportingMastersAndProperties(driver, dataFile);
        importing.branchContactDetails("Branch Address Details","Anantapur");
    }

    @Test(priority = 9)
    public void customerProperty() throws IOException, ParseException, InterruptedException {
        ImportingMastersAndProperties importing = new ImportingMastersAndProperties(driver, dataFile);
        importing.importContactInformationGSTProperty("Contact Information GST","Anjali Devi");
        importing.importRegistrationGSTProperty("Registration GST","Anjali Devi");
        importing.importShippingAddressProperty("Shipping Address GST","Anjali Devi");
    }

    @Test(priority = 10)
    public void ProductProperty() throws IOException, ParseException, InterruptedException {
        ImportingMastersAndProperties importing = new ImportingMastersAndProperties(driver, dataFile);
        importing.importProductStandardPurchasePriceProperty("Standard Purchase Price", "Laptop");
        importing.importProductStandardSalesPriceProperty("Standard Sales Price","Laptop");
    }

    @Test(priority = 11)
    public void balanceSheetBankAccountProperty() throws IOException, ParseException, InterruptedException {
        ImportingMastersAndProperties importing = new ImportingMastersAndProperties(driver, dataFile);
        importing.importBankDetailsProperty("Bank Details","Axis Bank Account");
        importing.bankAddressAndContactDetails("Bank Address and Contact Details","Axis Bank Account");
    }

    @Test(priority = 12)
    public void hsnCodeProperty() throws IOException, ParseException, InterruptedException {
        ImportingMastersAndProperties importing = new ImportingMastersAndProperties(driver, dataFile);
        importing.importHSNCodesProperty("GST HSN Codes","Avinaya");
        importing.importHSNRoundOffProperty("HSN Round Off","Avinaya");
        importing.importSlabWiseGSTRates("Slab wise GST Rates","Avinaya");
        importing.importCESSGSTProductTaxCategories("CESS GST Product Tax Categories","Avinaya");
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}

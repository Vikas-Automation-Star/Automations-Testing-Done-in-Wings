package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class CreateLocations extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateLocations(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createLocations() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Inventory","Product","Locations");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Locations']/TreeItem[@Name='All Locations']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Location *']", common.getData(dataFile, "location") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Location *']").getText();
        sendData("xpath","//Edit[@Name='Branch *']",dataFile,"branch");
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        sendData("xpath","//Edit[@Name='Storage Bin Type *']",dataFile,"storageBin");
        sendData("xpath","//Edit[@Name='Stock Type *']",dataFile,"stockType");
        inputTextWithValidation("xpath", "//Edit[@Name='Address 1 *']", common.getData(dataFile, "address1"));
        inputTextWithValidation("xpath", "//Edit[@Name='Address 2']", common.getData(dataFile, "address2") );
        inputTextWithValidation("xpath", "//Edit[@Name='Address 3']", common.getData(dataFile, "address3") );
        inputTextWithValidation("xpath", "//Edit[@Name='City *']", common.getData(dataFile, "city") );
        inputTextWithValidation("xpath", "//Edit[@Name='GSTIN']", common.getData(dataFile, "gstin") );
        sendData("xpath","//Edit[@Name='State *']",dataFile,"state");
//        inputTextWithValidation("xpath", "//Edit[@Name='State Code *']", common.getData(dataFile, "") );
        inputTextWithValidation("xpath", "//Edit[@Name='ZIP/PostalCode']", common.getData(dataFile, "postalCode") );
        sendData("xpath","//Edit[@Name='Country']",dataFile,"country");

        common.clickElement("xpath", "//Pane[@Name='Address and Contact Details']/Button[@Name='...']");
        Thread.sleep(1000);
        super.inputTextWithValidation("xpath", "//Edit[@Name='Address 1']", common.getData(dataFile, "address1"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Address 2']", common.getData(dataFile, "address2"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Address 3']", common.getData(dataFile, "address3"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='City']", common.getData(dataFile, "city"));
        sendData("xpath", "//Edit[@Name='State']",dataFile, "state");
        Thread.sleep(1000);
        WebElement country = common.findWebElement("xpath", "//Edit[@Name='Country']");
        country.clear();
        country.sendKeys(common.getData(dataFile, "country"), Keys.ENTER);
        super.inputTextWithValidation("xpath", "//Edit[@Name='Zip']", common.getData(dataFile, "zip"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Telephones 1']", common.getData(dataFile, "Telephone1"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Telephones 2']", common.getData(dataFile, "Telephone2"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Telephones 3']", common.getData(dataFile, "Telephone3"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Telephones 4']", common.getData(dataFile, "Telephone4"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Fax']", common.getData(dataFile, "fax"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Email']", common.getData(dataFile, "email"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Website']", common.getData(dataFile, "website"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Contact Person']", common.getData(dataFile, "contactPersion"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Designation']", common.getData(dataFile, "contactPersonDesignation"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Telephone No']", common.getData(dataFile, "contactPersonTelephoneNo"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Mobile No']", common.getData(dataFile, "contactPersonMobileNo"));
        super.inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Email']", common.getData(dataFile, "contactPersonEmail"));
        common.clickElement("xpath", "//Button[@Name='Ok']");


        saveAfterMasterCreate();
        validateMastersAndInactive("Locations",master);
        Thread.sleep(1000);
    }
}

package tradeTesting.company.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class CreateTransporters extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateTransporters(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void transporters() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen2Steps("Company","Transporters");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Transporters']/TreeItem[@Name='All Transporters']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Transporter *']", common.getData(dataFile, "newTransporters") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Transporter *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        common.clickElement("xpath", "//*[@Name='Address and Contact Details']/Button[@Name='...']");
        Thread.sleep(1000);
        inputTextWithValidation("xpath", "//Edit[@Name='Address1']", common.getData(dataFile, "address1"));
        inputTextWithValidation("xpath", "//Edit[@Name='Address2']", common.getData(dataFile, "address2"));
        inputTextWithValidation("xpath", "//Edit[@Name='Address3']", common.getData(dataFile, "address3"));
        inputTextWithValidation("xpath", "//Edit[@Name='City']", common.getData(dataFile, "city"));
        inputTextWithValidation("xpath", "//Edit[@Name='State']", common.getData(dataFile, "state"));
        sendData("xpath","//Edit[@Name='Country']",dataFile,"country");
        inputTextWithValidation("xpath", "//Edit[@Name='Zip']", common.getData(dataFile, "zip"));
        inputTextWithValidation("xpath", "//Edit[@Name='Mobile No']", common.getData(dataFile, "mobileNum"));
        inputTextWithValidation("xpath", "//Edit[@Name='Telephones1']", common.getData(dataFile, "Telephone1"));
        inputTextWithValidation("xpath", "//Edit[@Name='Telephones2']", common.getData(dataFile, "Telephone2"));
        inputTextWithValidation("xpath", "//Edit[@Name='Telephones3']", common.getData(dataFile, "Telephone3"));
        inputTextWithValidation("xpath", "//Edit[@Name='Fax']", common.getData(dataFile, "fax"));
        inputTextWithValidation("xpath", "//Edit[@Name='Email']", common.getData(dataFile, "email"));
        inputTextWithValidation("xpath", "//Edit[@Name='Website']", common.getData(dataFile, "website"));
        inputTextWithValidation("xpath", "//Edit[@Name='Contact Person']", common.getData(dataFile, "contactPersion"));
        inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Designation']", common.getData(dataFile, "contactPersonDesignation"));
        inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Telephone No']", common.getData(dataFile, "contactPersonTelephoneNo"));
        inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Mobile No']", common.getData(dataFile, "contactPersonMobileNo"));
        inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Email']", common.getData(dataFile, "contactPersonEmail"));
        common.clickElement("xpath", "//Button[@Name='Ok']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//*[@Name='Registration']/Button[@Name='...']");
        WebElement roundOffType=common.findWebElement("xpath", "//Edit[@Name='Party Reg Type *']");
        roundOffType.click();
        roundOffType.sendKeys(common.getData(dataFile, "partyType"),Keys.DOWN, Keys.ENTER);
        inputTextWithValidation("xpath", "//Edit[@Name='GSTIN']", common.getData(dataFile, "gstin"));
        common.clickElement("xpath", "//Edit[@Name='PAN']");
        common.clickElement("xpath", "//Button[@Name='Verify GSTIN']");
        Thread.sleep(2500);
        common.clickElement("xpath", "//Button[@Name='Close']");
        common.clickElement("xpath", "//Button[@Name='Ok']");

        saveAfterMasterCreate();
        validateMastersAndInactive("Transporters",master);
        Thread.sleep(1000);
    }
}

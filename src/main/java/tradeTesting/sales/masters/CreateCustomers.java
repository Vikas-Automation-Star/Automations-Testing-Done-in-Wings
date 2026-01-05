package tradeTesting.sales.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.text.ParseException;

public class CreateCustomers extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateCustomers(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createCustomers() throws InterruptedException, IOException, ParseException, org.json.simple.parser.ParseException {
        navigateToMastersWhen2Steps("Sales","Customers");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Customers']/TreeItem[@Name='All Customers']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Account *']", common.getData(dataFile, "newCustomer") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Account *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Account Code']", common.getData(dataFile, "accountCode")+common.getRandom());
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        common.clickElement("xpath", "//Pane[@Name='Bank Details']/Button[@Name='...']");
        inputTextWithValidation("xpath","//Edit[@Name='Account No']",common.getData(dataFile,"accNum"));
        inputTextWithValidation("xpath","//Edit[@Name='Bank']",common.getData(dataFile, "bank"));
        inputTextWithValidation("xpath","//Edit[@Name='Bank Branch']",common.getData(dataFile, "bankBranch"));
        inputTextWithValidation("xpath","//Edit[@Name='IFSC Code']",common.getData(dataFile, "ifscCode"));
        common.clickElement("xpath", "//Button[@Name='Ok']");
        sendData("xpath","//Edit[@Name='Transport']",dataFile,"transport");
        sendData("xpath","//Edit[@Name='Transporter']",dataFile,"transporter");
        sendData("xpath","//Edit[@Name='Type Of Customer']",dataFile,"typeOfCustomer");
        inputTextWithValidation("xpath","//Edit[@Name='Aadhaar No']",common.getData(dataFile, "adharNum"));
        common.clickElement("xpath", "//Pane[@Name='Address and Contact Details']/Button[@Name='...']");
        Thread.sleep(1000);
        inputTextWithValidation("xpath", "//Edit[@Name='Address 1']", common.getData(dataFile, "address1"));
        inputTextWithValidation("xpath", "//Edit[@Name='Address 2']", common.getData(dataFile, "address2"));
        inputTextWithValidation("xpath", "//Edit[@Name='Address 3']", common.getData(dataFile, "address3"));
        inputTextWithValidation("xpath", "//Edit[@Name='City']", common.getData(dataFile, "city"));
        sendData("xpath","//Edit[@Name='State']",dataFile,"state");
        sendData("xpath","//Edit[@Name='Country']",dataFile,"country");
        inputTextWithValidation("xpath", "//Edit[@Name='Zip']", common.getData(dataFile, "zip"));
        inputTextWithValidation("xpath", "//Edit[@Name='Telephones 1']", common.getData(dataFile, "Telephone1"));
        inputTextWithValidation("xpath", "//Edit[@Name='Telephones 2']", common.getData(dataFile, "Telephone2"));
        inputTextWithValidation("xpath", "//Edit[@Name='Telephones 3']", common.getData(dataFile, "Telephone3"));
        inputTextWithValidation("xpath", "//Edit[@Name='Telephones 4']", common.getData(dataFile, "Telephone4"));
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
        roundOffType.sendKeys(common.getData(dataFile, "partyType"), Keys.DOWN, Keys.ENTER);
        inputTextWithValidation("xpath", "//Edit[@Name='GSTIN']", common.getData(dataFile, "gstin"));
        common.clickElement("xpath", "//Edit[@Name='PAN']");
        common.clickElement("xpath", "//Button[@Name='Verify GSTIN']");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Button[@Name='Close']");
        common.clickElement("xpath", "//Button[@Name='Ok']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//*[@Name='Contact Details']/Button[@Name='...']");
        inputTextWithValidation("xpath", "//Edit[@Name='Telephones 1']", common.getData(dataFile, "Telephone1"));
        inputTextWithValidation("xpath", "//Edit[@Name='Telephones 2']", common.getData(dataFile, "Telephone2"));
        inputTextWithValidation("xpath", "//Edit[@Name='Telephones 3']", common.getData(dataFile, "Telephone3"));
        inputTextWithValidation("xpath", "//Edit[@Name='Telephones 4']", common.getData(dataFile, "Telephone4"));
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
        common.sliderHandling("name", "Position", 0, 150);
        common.clickElement("xpath", "//CheckBox[@Name='Apply TDS']");
        sendData("xpath","//Edit[@Name='TDS Assessee Type']",dataFile,"applyTDS");
        common.clickElement("xpath", "//CheckBox[@Name='Apply TCS']");
        sendData("xpath","//Edit[@Name='TCS Assessee Type']",dataFile,"applyTCS");
        sendData("xpath","//Edit[@Name='Sales Executive']",dataFile,"salesExecutive");
        sendData("xpath","//Edit[@Name='Sales Price Type']",dataFile,"salesPriceType");
        sendData("xpath","//Edit[@Name='Sales Account']",dataFile,"salesAct");
        sendData("xpath","//Edit[@Name='ProductMarginCategory']",dataFile,"productMarginCategory");

        common.clickElement("xpath","//Text[@Name='Shipping Address GST']/following-sibling::Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='GSTIN Row 0, Not sorted.']", common.getData(dataFile, "gstin"));
        common.inputText("xpath", "//Edit[@Name='Address Name * Row 0, Not sorted.']", common.getData(dataFile, "shippingAdressName"));
        common.inputText("xpath", "//Edit[@Name='Address 1 * Row 0, Not sorted.']", common.getData(dataFile, "ShippingAdress1"));
        common.inputText("xpath", "//Edit[@Name='Address 2 Row 0, Not sorted.']", common.getData(dataFile, "ShippingAdress2"));
        common.inputText("xpath", "//Edit[@Name='Address 3 Row 0, Not sorted.']", common.getData(dataFile, "ShippingAdress3"));
        common.inputText("xpath", "//Edit[@Name='City * Row 0, Not sorted.']", common.getData(dataFile, "Shippingcity"));
        common.inputText("xpath", "//Edit[@Name='State * Row 0, Not sorted.']",common.getData(dataFile,"ShippingState"));
        Thread.sleep(2000);
//        verify this
        int offset = 300;
        WebElement slider = common.findWebElement("xpath", "//ScrollBar[@Name='Horizontal']/Thumb[@Name='Position']");
        Actions actions = new Actions(driver);
        actions.clickAndHold(slider).moveByOffset(offset, 0).release().perform();
        common.inputText("xpath", "//Edit[@Name='Zip/PostalCode * Row 0, Not sorted.']", common.getData(dataFile, "stateZipcode"));
        common.inputText("xpath", "//Edit[@Name='Country Row 0, Not sorted.']", common.getData(dataFile, "country"));
        common.inputText("xpath", "//Edit[@Name='Telephone No Row 0, Not sorted.']", common.getData(dataFile, "Telephone1"));
        common.inputText("xpath", "//Edit[@Name='Mobile No Row 0, Not sorted.']", common.getData(dataFile, "Telephone2"));
        common.clickElement("xpath", "//Button[@Name='Ok']");
        Thread.sleep(2000);

        common.sliderHandling("name", "Position", 0, 150);
        common.clickElement("xpath", "//Text[@Name='Consignor']/following-sibling::Button[@Name='...']");
//        sendData("xpath","//Edit[@Name='Applicable Consignor Row 0, Not sorted.']",dataFile,"consignor");
        common.clickElement("xpath", "//Button[@Name='Ok']");

        common.clickElement("xpath", "//Text[@Name='Applicable Product Groups And Routes']/following-sibling::Button[@Name='...']");
//        common.inputText("xpath", "//Edit[@Name='Product Group Row 0, Not sorted.']", common.getData(dataFile, "productGrp"));
//        common.inputText("xpath", "//Edit[@Name='Route Row 0, Not sorted.']", common.getData(dataFile, "route"));
        common.clickElement("xpath", "//Button[@Name='Ok']");

        saveAfterMasterCreate();
        validateMastersAndInactive("Customers",master);
        Thread.sleep(1000);
    }
}

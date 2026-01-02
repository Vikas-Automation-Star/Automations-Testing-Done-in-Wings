package tradeTesting.company.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class CreateBranches extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateBranches(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void branches() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen2Steps("Company","Branches");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Branches']/TreeItem[@Name='All Branches']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Branch *']", common.getData(dataFile, "newBranch") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Branch *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        sendData("xpath","//Edit[@Name='Stock Type *']",dataFile,"stockType");
        inputTextWithValidation("xpath", "//Edit[@Name='POS Customer Creation Code']", common.getData(dataFile, "posCustomerCreationCode"));
        common.clickElement("xpath", "//Pane[@Name='Address and Contact Details']/Button[@Name='...']");
        Thread.sleep(1000);
        inputTextWithValidation("xpath", "//Edit[@Name='Address 1 *']", common.getData(dataFile, "address1"));
        inputTextWithValidation("xpath", "//Edit[@Name='Address 2']", common.getData(dataFile, "address2"));
        inputTextWithValidation("xpath", "//Edit[@Name='Address 3']", common.getData(dataFile, "address3"));
        inputTextWithValidation("xpath", "//Edit[@Name='City *']", common.getData(dataFile, "city"));
        sendData("xpath","//Edit[@Name='State']",dataFile,"state");
        sendData("xpath","//Edit[@Name='Country']",dataFile,"country");
        inputTextWithValidation("xpath", "//Edit[@Name='Zip']", common.getData(dataFile, "zip"));
        inputTextWithValidation("xpath", "//Edit[@Name='Telephone 1']", common.getData(dataFile, "Telephone1"));
        inputTextWithValidation("xpath", "//Edit[@Name='Telephone 2']", common.getData(dataFile, "Telephone2"));
        inputTextWithValidation("xpath", "//Edit[@Name='Telephone 3']", common.getData(dataFile, "Telephone3"));
        inputTextWithValidation("xpath", "//Edit[@Name='Telephone 4']", common.getData(dataFile, "Telephone4"));
        inputTextWithValidation("xpath", "//Edit[@Name='Fax']", common.getData(dataFile, "fax"));
        inputTextWithValidation("xpath", "//Edit[@Name='Email']", common.getData(dataFile, "email"));
        inputTextWithValidation("xpath", "//Edit[@Name='Website']", common.getData(dataFile, "website"));
        inputTextWithValidation("xpath", "//Edit[@Name='Contact Person']", common.getData(dataFile, "contactPersion"));
        inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Designation']", common.getData(dataFile, "contactPersonDesignation"));
        inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Telephone No']", common.getData(dataFile, "contactPersonTelephoneNo"));
        inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Mobile No']", common.getData(dataFile, "contactPersonMobileNo"));
        inputTextWithValidation("xpath", "//Edit[@Name='Contact Person Email']", common.getData(dataFile, "contactPersonEmail"));
        common.clickElement("xpath", "//Button[@Name='Ok']");
        inputTextWithValidation("xpath", "//Edit[@Name='Voucher Series']", common.getData(dataFile, "voucherSeries"));
        sendData("xpath","//Edit[@Name='Bank Details']",dataFile,"bankDetails");
        sendData("xpath","//Edit[@Name='GST Registration']",dataFile,"gstRegistration");
        sendData("xpath","//Edit[@Name='Default Loyalty Scheme']",dataFile,"loyaltyShceme");
        sendData("xpath","//Edit[@Name='VATCST Transaction Type']",dataFile,"vatcstTransactionType");
        sendData("xpath","//Edit[@Name='Sales ST Trans Type']",dataFile,"salesSTTransType");
        sendData("xpath","//Edit[@Name='ReturnsVATCST Transaction Type']",dataFile,"returnsVATCSTTransactionType");
        common.clickElement("xpath", "//Pane[@Name='POS Configuration Details']/Button[@Name='...']");
        sendData("xpath","//Edit[@Name='Location *']",dataFile,"location");
        sendData("xpath","//Edit[@Name='POS Control Account *']",dataFile,"posControlAcct");
        sendData("xpath","//Edit[@Name='Sales Account *']",dataFile,"salesAcct");
        sendData("xpath","//Edit[@Name='Cash Account *']",dataFile,"cashAcct");
        sendData("xpath","//Edit[@Name='GV Control Account *']",dataFile,"gvtControlAcct");
        sendData("xpath","//Edit[@Name='Sales Return Account *']",dataFile,"salesReturnAcct");
        WebElement roundOffType=common.findWebElement("xpath", "//Edit[@Name='Round Off Type *']");
        roundOffType.click();
        roundOffType.sendKeys(common.getData(dataFile, "roundOffType"),Keys.DOWN, Keys.ENTER);
        sendData("xpath","//Edit[@Name='Round Off Account *']",dataFile,"roundOffAcct");
        sendData("xpath","//Edit[@Name='Price List *']",dataFile,"priceList");
        common.clickElement("xpath", "//Button[@Name='Ok']");
        Thread.sleep(2000);
        common.sliderHandling("name", "Position", 0, 150);
        Thread.sleep(2000);
        common.clickElement("xpath", "//*[@Name='Account Folios']/following-sibling::Button[@Name='...']");
        common.clickElement("xpath", "//Button[@Name='Ok']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//*[@Name='Default Division']/following-sibling::Button[@Name='...']");
        WebElement division= common.findWebElement("xpath", "//*[@Name='Row 0']/Edit[@Name='DefaultDivision Row 0, Not sorted.']");
        division.click();division.sendKeys(Keys.DOWN,Keys.DOWN,Keys.ENTER);
        common.clickElement("xpath", "//Button[@Name='Ok']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//*[@Name='Default Distribution']/following-sibling::Button[@Name='...']");
        WebElement distribution=common.findWebElement("xpath", "//*[@Name='Row 0']/Edit[@Name='DefaultDivision Row 0, Not sorted.']");
        distribution.click();distribution.sendKeys(Keys.DOWN,Keys.ENTER);
        common.clickElement("xpath", "//Button[@Name='Ok']");
        saveAfterMasterCreate();
        validateMastersAndInactive("Branches",master);
        Thread.sleep(1000);
    }
}

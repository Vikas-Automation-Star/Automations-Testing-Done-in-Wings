package tradeTesting.company.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class CreateSalesExecutives extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateSalesExecutives(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void salesExecutives() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen2Steps("Company","Sales Executives");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Sales Executives']/TreeItem[@Name='All Sales Executives']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Master *']", common.getData(dataFile, "newSalesExecutive") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Master *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Sales Executive Code']", common.getData(dataFile, "code") + common.getRandom());
        sendData("xpath","//Edit[@Name='Master Folder *']",dataFile,"masterFolder");
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
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
        sendData("xpath","//Edit[@Name='Branch *']",dataFile,"branch");
        inputTextWithValidation("xpath", "//Edit[@Name='Scheduled Calls for the Week']", common.getData(dataFile, "schedulesCallsPerWeek"));
        inputTextWithValidation("xpath", "//Edit[@Name='No Of Visits per Week']", common.getData(dataFile, "noOfVisitorsPerWeek"));
        inputTextWithValidation("xpath", "//Edit[@Name='Date Of Joining']", common.getData(dataFile, "dateOfJoining"));
        sendData("xpath","//Edit[@Name='Location']",dataFile,"location");
        sendData("xpath","//Edit[@Name='Bank Account *']",dataFile,"bankAcct");
        sendData("xpath","//Edit[@Name='Cash Account *']",dataFile,"cashAcct");
        Thread.sleep(1000);
        common.clickElement("xpath", "//*[@Name='Applicable Routes']/following-sibling::Button[@Name='...']");
        WebElement routes = common.findWebElement("xpath", "//*[@Name='Row 0']/Edit[@Name='Route Row 0, Not sorted.']");
        routes.sendKeys(common.getData(dataFile, "routes"),Keys.ENTER);
        common.clickElement("xpath", "//Button[@Name='Ok']");

        saveAfterMasterCreate();
        validateMastersAndInactive("Sales Executives",master);
        Thread.sleep(1000);
    }
}

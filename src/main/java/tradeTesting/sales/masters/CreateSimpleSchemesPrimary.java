package tradeTesting.sales.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class CreateSimpleSchemesPrimary extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateSimpleSchemesPrimary(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createSimpleSchemesPrimary() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Sales","Promotion","Simple Schemes - Primary");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Simple Schemes - Primary']/TreeItem[@Name='All Simple Schemes - Primary']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Master *']", common.getData(dataFile, "newSimpleSchemePrimary") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Master *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='From Date']", common.getData(dataFile, "fromDate"));
        inputTextWithValidation("xpath", "//Edit[@Name='To Date']", common.getData(dataFile, "toDate"));
        common.clickElement("xpath","//CheckBox[@Name='Fixed Slab']");
        common.clickElement("xpath","//CheckBox[@Name='All Products']");
        WebElement roundOffType=common.findWebElement("xpath", "//Edit[@Name='Scheme Type']");
        roundOffType.click();
        roundOffType.sendKeys(common.getData(dataFile, "schemeType"),Keys.DOWN, Keys.ENTER);
        sendData("xpath","//Edit[@Name='Unit *']",dataFile,"units");
        common.clickElement("xpath","//Button[@Name='Choose Products']");
        Thread.sleep(1000);
        common.clickElement("xpath","//RadioButton[@Name='Choose Products']");
        common.clickElement("xpath","//Button[@Name='OK']");
        common.clickElement("xpath","//*[contains(@Name,'Row')]/CheckBox[@Name='Select Row 1']");
        common.clickElement("xpath","//*[contains(@Name,'Row')]/CheckBox[@Name='Select Row 2']");
        common.clickElement("xpath","//Button[@Name='OK']");
        common.clickElement("xpath","//Text[@Name='Slabs']/following-sibling::Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='Eligibility Criteria * Row 0, Not sorted.']", common.getData(dataFile, "eligibilityCriteria"));
        common.inputText("xpath", "//Edit[@Name='Min Limit * Row 0, Not sorted.']", common.getData(dataFile, "minLimit"));
        common.inputText("xpath", "//Edit[@Name='For Every * Row 0, Not sorted.']", common.getData(dataFile, "forEvery"));
        common.inputText("xpath", "//Edit[@Name='Discount Basis * Row 0, Not sorted.']", common.getData(dataFile, "discountBasis"));
        common.inputText("xpath", "//Edit[@Name='Discount Row 0, Not sorted.']", common.getData(dataFile, "discount"));
//        common.clickElement("xpath","//CheckBox[@Name='Same Item Free Row 0']");
        common.inputText("xpath", "//Edit[@Name='Free Product Row 0, Not sorted.']", common.getData(dataFile, "freeProduct"));
        common.inputText("xpath", "//Edit[@Name='Free Product Batch Row 0, Not sorted.']", common.getData(dataFile, "freeProductBatch"));
        common.inputText("xpath", "//Edit[@Name='Unit Row 0, Not sorted.']", common.getData(dataFile, "units"));
        common.inputText("xpath", "//Edit[@Name='Free Units Row 0, Not sorted.']", common.getData(dataFile, "freeUnits"));
        Thread.sleep(1000);
        common.clickElement("xpath","//Button[@Name='Ok']");


        saveAfterMasterCreate();
        validateMastersAndInactive("Simple Schemes - Primary",master);
        Thread.sleep(1000);
    }
}

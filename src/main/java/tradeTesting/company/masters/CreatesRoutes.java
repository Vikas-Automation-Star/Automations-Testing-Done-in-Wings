package tradeTesting.company.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class CreatesRoutes extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreatesRoutes(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void routes() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen2Steps("Company","Routes");
        Thread.sleep(1000);
        createMaster("xpath", "//TreeItem[@Name='Routes']/TreeItem[@Name='All Routes']");
        Thread.sleep(1000);
        common.inputText("xpath", "//Edit[@Name='New Route *']", common.getData(dataFile, "route") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Route *']").getText();
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
//        common.clickElement("xpath", "//CheckBox[@Name='Approve Route']");
        common.clickElement("xpath", "//*[@Name='Applicable Route Details']/following-sibling::Button[@Name='...']");
        common.clickElement("xpath", "//Button[@Name='Ok']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//*[@Name='Applicable Area']/following-sibling::Button[@Name='...']");
        common.clickElement("xpath", "//Button[@Name='Ok']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//*[@Name='Applicable Delivery Days']/following-sibling::Button[@Name='...']");
        WebElement division= common.findWebElement("xpath", "//*[@Name='Row 0']/Edit[@Name='Day of Week Row 0, Not sorted.']");
        division.click();division.sendKeys(common.getData(dataFile, "applicableDeliveryDates"),Keys.ENTER);
        common.clickElement("xpath", "//Button[@Name='Ok']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//*[@Name='Applicable Customer Route Details']/following-sibling::Button[@Name='...']");
        WebElement startingCustomers = common.findWebElement("xpath", "//*[@Name='Row 0']/Edit[@Name='Starting Customer Row 0, Not sorted.']");
        startingCustomers.click();
        startingCustomers.sendKeys(common.getData(dataFile, "startingCustomers"),Keys.ENTER);
        WebElement endingCustomers = common.findWebElement("xpath", "//*[@Name='Row 0']/Edit[@Name='Ending Customer Row 0, Not sorted.']");
        endingCustomers.click();
        endingCustomers.sendKeys(common.getData(dataFile, "endingCustomers"),Keys.ENTER);
        common.clickElement("xpath", "//Button[@Name='Ok']");
        Thread.sleep(1000);
        saveAfterMasterCreate();
        validateMastersAndInactive("Routes",master);
        Thread.sleep(1500);

    }
}

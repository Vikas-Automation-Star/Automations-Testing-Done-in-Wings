package tradeTesting.company.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class CreateDivisions extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateDivisions(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void divisions() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen2Steps("Company","Divisions");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Divisions']/TreeItem[@Name='All Divisions']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Division *']", common.getData(dataFile, "newDivisions") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Division *']").getText();
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        Thread.sleep(1000);
        common.clickElement("xpath", "//*[@Name='Margin Percentage']/following-sibling::Button[@Name='...']");
        WebElement marginPercentage = common.findWebElement("xpath","//Edit[@Name='Margin Percentage * Row 0, Not sorted.']");
        marginPercentage.click();
        marginPercentage.sendKeys(common.getData(dataFile, "marginPercentage"));
        common.clickElement("xpath","//Edit[@Name='With Effective From Row 0, Not sorted.']");
        common.clickElement("xpath", "//Button[@Name='Ok']");

        saveAfterMasterCreate();
        validateMastersAndInactive("Divisions",master);
        Thread.sleep(1000);
    }
}

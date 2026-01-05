package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class CreateUnitsOfMeasure extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateUnitsOfMeasure(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createUnitsOfMeasure() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Inventory","Product","Units of Measure");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Units of Measure']/TreeItem[@Name='All Units of Measure']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Units Of Measure *']", common.getData(dataFile, "newUnitOfMeasure") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Units Of Measure *']").getText();
        WebElement uaq =common.findWebElement("xpath", "//Edit[@Name='UQC *']");
        uaq.click();
        uaq.sendKeys(common.getData(dataFile, "uaq"), Keys.DOWN, Keys.ENTER);
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        inputTextWithValidation("xpath", "//Edit[@Name='Conversion Factor']", common.getData(dataFile, "conversationFactor"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Units of Measure",master);
        Thread.sleep(1000);
    }
}

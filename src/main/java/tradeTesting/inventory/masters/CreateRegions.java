package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateRegions extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateRegions(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createRegions() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen4Steps("Inventory","Customer Attributes","Geographical Details","Regions");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Regions']/TreeItem[@Name='All Regions']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Region *']", common.getData(dataFile, "newRegion") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Region *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Regions",master);
        Thread.sleep(1000);
    }
}

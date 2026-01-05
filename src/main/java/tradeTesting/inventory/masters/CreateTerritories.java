package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateTerritories extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateTerritories(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createTerritories() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen4Steps("Inventory","Customer Attributes","Geographical Details","Territories");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Territories']/TreeItem[@Name='All Territories']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Territory *']", common.getData(dataFile, "newTerritories") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Territory *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Territories",master);
        Thread.sleep(1000);
    }
}

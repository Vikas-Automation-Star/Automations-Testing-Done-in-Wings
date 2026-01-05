package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreatePackSizes extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreatePackSizes(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createPackSizes() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen4Steps("Inventory","Product","Attributes","Pack Sizes");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Pack Sizes']/TreeItem[@Name='All Pack Sizes']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Pack Size *']", common.getData(dataFile, "packSize") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Pack Size *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Pack Sizes",master);
        Thread.sleep(1000);
    }
}

package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateFormate extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateFormate(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createFormate() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen4Steps("Inventory","Product","Attributes","Pack Formats");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Pack Formats']/TreeItem[@Name='All Pack Formats']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Pack Format *']", common.getData(dataFile, "newPackFormat") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Pack Format *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Pack Formats",master);
        Thread.sleep(1000);
    }
}

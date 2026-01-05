package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateStorageBins extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateStorageBins(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createStorageBins() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Inventory","Product","Storage Bins");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Storage Bins']/TreeItem[@Name='All Storage Bins']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Storage Bin *']", common.getData(dataFile, "storageBin") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Storage Bin *']").getText();
        sendData("xpath","//Edit[@Name='Location *']",dataFile,"location");
        sendData("xpath","//Edit[@Name='Storage Bin Type *']",dataFile,"storageBinType");
        sendData("xpath","//Edit[@Name='Stock Type *']",dataFile,"stockType");
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Storage Bins",master);
        Thread.sleep(1000);
    }
}

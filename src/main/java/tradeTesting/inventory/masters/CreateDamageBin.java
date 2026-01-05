package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateDamageBin extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateDamageBin(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createDamageBin() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Inventory","Product","Damage Bin");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Damage Bin']/TreeItem[@Name='All Damage Bin']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Damage Bin *']", common.getData(dataFile, "newDamageBin") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Damage Bin *']").getText();
        sendData("xpath","//Edit[@Name='Location *']",dataFile,"location");
        sendData("xpath","//Edit[@Name='Storage Bin Type *']",dataFile,"storageBinType");
        sendData("xpath","//Edit[@Name='Stock Type *']",dataFile,"stockType");
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        sendData("xpath","//Edit[@Name='Storage Bin Type']",dataFile,"storageBinType");
        sendData("xpath","//Edit[@Name='Location']",dataFile,"location");

        saveAfterMasterCreate();
        validateMastersAndInactive("Damage Bin",master);
        Thread.sleep(1000);
    }
}

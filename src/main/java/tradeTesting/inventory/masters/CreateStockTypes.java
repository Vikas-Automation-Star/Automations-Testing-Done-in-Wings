package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateStockTypes extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateStockTypes(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createStockTypes() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Inventory","Product","Stock Types");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Stock Types']/TreeItem[@Name='All Stock Types']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Stock Type *']", common.getData(dataFile, "newStockType") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Stock Type *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Stock Types",master);
        Thread.sleep(1000);
    }
}

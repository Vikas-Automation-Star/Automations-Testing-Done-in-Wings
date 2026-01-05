package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateBrandExtensions extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateBrandExtensions(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createBrandExtensions() throws InterruptedException, ParseException, IOException {
        navigateToMastersWhen4Steps("Inventory","Product","Attributes","Brand Extensions");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Brand Extensions']/TreeItem[@Name='All Brand Extensions']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Master *']", common.getData(dataFile, "newBrandExtensions") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Master *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Brand Extensions",master);
        Thread.sleep(1000);
    }
}

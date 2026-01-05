package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateOutletTypes extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateOutletTypes(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createOutletTypes() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Inventory","Customer Attributes","Outlet Types");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Outlet Types']/TreeItem[@Name='All Outlet Types']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Outlet Type *']", common.getData(dataFile, "newOutlet") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Outlet Type *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Outlet Types",master);
        Thread.sleep(1000);
    }
}

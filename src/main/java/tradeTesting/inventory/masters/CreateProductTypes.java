package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateProductTypes extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateProductTypes(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createProductTypes() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen4Steps("Inventory","Product","Attributes","Product Types");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Product Types']/TreeItem[@Name='All Product Types']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Product Type *']", common.getData(dataFile, "newProductType") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Product Type *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Product Types",master);
        Thread.sleep(1000);
    }
}

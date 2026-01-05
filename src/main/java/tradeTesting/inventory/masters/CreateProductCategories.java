package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateProductCategories extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateProductCategories(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createProductCategories() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen4Steps("Inventory","Product","Attributes","Product Categories");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Product Categories']/TreeItem[@Name='All Product Categories']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Product Category *']", common.getData(dataFile, "productCategory") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Product Category *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='New Product Category Code']", common.getData(dataFile, "pcCode"));
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Product Categories",master);
        Thread.sleep(1000);
    }
}

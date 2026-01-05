package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateProductMarginCategories extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateProductMarginCategories(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createProductMarginCategories() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen2Steps("Inventory","Product Margin Categories");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Product Margin Categories']/TreeItem[@Name='All Product Margin Categories']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Product Margin Category *']", common.getData(dataFile, "newProductMarginCategory") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Product Margin Category *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Product Margin Categories",master);
        Thread.sleep(1000);
    }
}

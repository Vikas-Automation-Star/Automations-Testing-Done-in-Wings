package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateProductVariants extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateProductVariants(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createProductVariants() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen4Steps("Inventory","Product","Attributes","Product Variants");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Product Variants']/TreeItem[@Name='All Product Variants']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Master *']", common.getData(dataFile, "newProductVariant") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Master *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Product Variants",master);
        Thread.sleep(1000);
    }
}

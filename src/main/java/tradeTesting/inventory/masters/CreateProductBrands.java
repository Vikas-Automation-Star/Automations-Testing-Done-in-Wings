package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateProductBrands extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateProductBrands(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createProductBrands() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen4Steps("Inventory","Product","Attributes","Product Brands");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Product Brands']/TreeItem[@Name='All Product Brands']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Product Brand *']", common.getData(dataFile, "newProductBrand") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Product Brand *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Product Brands",master);
        Thread.sleep(1000);
    }
}

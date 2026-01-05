package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateCustomerCategories extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateCustomerCategories(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createCustomerCategories() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Inventory","Customer Attributes","Customer Categories");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Customer Categories']/TreeItem[@Name='All Customer Categories']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Customer Category *']", common.getData(dataFile, "newCustomerCategories") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Customer Category *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Customer Categories",master);
        Thread.sleep(1000);
    }
}

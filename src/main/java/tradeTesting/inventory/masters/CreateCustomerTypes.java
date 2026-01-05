package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateCustomerTypes extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateCustomerTypes(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createCustomerTypes() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Inventory","Customer Attributes","Customer Types");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Customer Types']/TreeItem[@Name='All Customer Types']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Customer Type *']", common.getData(dataFile, "newCustomerType") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Customer Type *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Customer Types",master);
        Thread.sleep(1000);
    }
}

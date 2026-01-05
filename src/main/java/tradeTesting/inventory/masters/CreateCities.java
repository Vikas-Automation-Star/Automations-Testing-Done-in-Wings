package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateCities extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateCities(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createCities() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen4Steps("Inventory","Customer Attributes","Geographical Details","Cities");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Cities']/TreeItem[@Name='All Cities']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New City *']", common.getData(dataFile, "newCity") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New City *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Cities",master);
        Thread.sleep(1000);
    }
}

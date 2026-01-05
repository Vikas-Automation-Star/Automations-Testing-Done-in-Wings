package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateIncomeGroups extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateIncomeGroups(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createIncomeGroups() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Inventory","Customer Attributes","Income Groups");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Income Groups']/TreeItem[@Name='All Income Groups']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Income Group *']", common.getData(dataFile, "newIncomeGroups") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Income Group *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Income Groups",master);
        Thread.sleep(1000);
    }
}

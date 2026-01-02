package tradeTesting.company.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateDepartments extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateDepartments(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void departments() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen2Steps("Company","Departments");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Departments']/TreeItem[@Name='All Departments']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Department *']", common.getData(dataFile, "newDepartments") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Department *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Departments",master);
        Thread.sleep(1000);
    }
}

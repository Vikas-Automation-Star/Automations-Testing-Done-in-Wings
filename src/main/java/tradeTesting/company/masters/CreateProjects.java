package tradeTesting.company.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateProjects extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateProjects(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void projects() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen2Steps("Company","Projects");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Projects']/TreeItem[@Name='All Projects']");
        Thread.sleep(2000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Project *']", common.getData(dataFile, "newProject") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Project *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Projects",master);
        Thread.sleep(1000);
    }
}

package tradeTesting.company.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateTermTypes extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateTermTypes(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void termTypes() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Company","Terms","Term Types");
        Thread.sleep(1000);
        createMaster("xpath", "//TreeItem[@Name='Term Types']/TreeItem[@Name='All Term Types']");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New Term Type *']", common.getData(dataFile, "newTermType") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Term Type *']").getText();
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        saveAfterMasterCreate();
        validateMastersAndInactive("Term Types",master);
        Thread.sleep(1000);
    }
}

package tradeTesting.company.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateDistributions extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateDistributions(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void distributions() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen2Steps("Company","Distributions");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Distributions']/TreeItem[@Name='All Distributions']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Master *']", common.getData(dataFile, "newDistributions") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Master *']").getText();
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));


        saveAfterMasterCreate();
        validateMastersAndInactive("Distributions",master);
        Thread.sleep(1000);
    }
}

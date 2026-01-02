package tradeTesting.company.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateReasons extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateReasons(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void reasons() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen2Steps("Company","Reasons");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Reasons']/TreeItem[@Name='All Reasons']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Reason *']", common.getData(dataFile, "newReason") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Reason *']").getText();
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Reasons",master);
        Thread.sleep(1000);
    }
}

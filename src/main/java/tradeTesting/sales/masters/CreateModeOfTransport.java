package tradeTesting.sales.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateModeOfTransport extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateModeOfTransport(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createModeOfTransport() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Sales","Transport","Modes Of Transport");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Modes Of Transport']/TreeItem[@Name='All Modes Of Transport']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Modes Of Transport *']", common.getData(dataFile, "newModeOfTransports") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Modes Of Transport *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Modes Of Transport",master);
        Thread.sleep(1000);
    }
}

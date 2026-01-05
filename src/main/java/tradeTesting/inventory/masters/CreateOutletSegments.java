package tradeTesting.inventory.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateOutletSegments extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateOutletSegments(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createOutletSegments() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Inventory","Customer Attributes","Outlet Segments");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Outlet Segments']/TreeItem[@Name='All Outlet Segments']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Outlet Segment *']", common.getData(dataFile, "outLetSegments") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Outlet Segment *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Outlet Segments",master);
        Thread.sleep(1000);
    }
}

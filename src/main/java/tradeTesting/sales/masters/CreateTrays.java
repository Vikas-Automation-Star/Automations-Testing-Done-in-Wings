package tradeTesting.sales.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateTrays extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateTrays(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createTrays() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Sales","Tray Management","Trays");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Trays']/TreeItem[@Name='All Trays']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Trays *']", common.getData(dataFile, "newTrays") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Trays *']").getText();
        common.clickElement("xpath","//*[@Name='Tray Capacity']/Button[@Name='...']");
        inputTextWithValidation("xpath", "//Edit[@Name='Capacity In Kgs *']", common.getData(dataFile, "capacityInKg's"));
        common.clickElement("xpath","//Button[@Name='Ok']");
        common.clickElement("xpath","//*[@Name='Tray Weight']/Button[@Name='...']");
        inputTextWithValidation("xpath", "//Edit[@Name='Weight *']", common.getData(dataFile, "weight"));
        common.clickElement("xpath","//Button[@Name='Ok']");


        saveAfterMasterCreate();
        validateMastersAndInactive("Trays",master);
        Thread.sleep(1000);
    }
}

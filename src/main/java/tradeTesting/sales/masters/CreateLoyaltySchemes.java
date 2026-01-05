package tradeTesting.sales.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateLoyaltySchemes extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateLoyaltySchemes(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createLoyaltySchemes() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Sales","Loyalty Programs","Loyalty Schemes");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Loyalty Schemes']/TreeItem[@Name='All Loyalty Schemes']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Loyalty Schemes *']", common.getData(dataFile, "newLoyaltySchemes") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Loyalty Schemes *']").getText();
        sendData("xpath","//Edit[@Name='Master Folder *']",dataFile,"masterNode");
        inputTextWithValidation("xpath", "//Edit[@Name='From Date *']", common.getData(dataFile, "fromDate"));
        inputTextWithValidation("xpath", "//Edit[@Name='To Date *']", common.getData(dataFile, "toDate"));
        inputTextWithValidation("xpath", "//Edit[@Name='Lower Limit *']", common.getData(dataFile, "lowerLimit"));
        inputTextWithValidation("xpath", "//Edit[@Name='For Every Bill Value *']", common.getData(dataFile, "forEveryBillValues"));
        inputTextWithValidation("xpath", "//Edit[@Name='Points *']", common.getData(dataFile, "points"));
        common.clickElement("xpath","//Text[@Name='Loyalty Redumption Points']/following-sibling ::Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='Lower Limit * Row 0, Not sorted.']", common.getData(dataFile, "lowerLimit"));
        common.inputText("xpath", "//Edit[@Name='Upper Limit * Row 0, Not sorted.']", common.getData(dataFile, "upperLimit"));
        common.inputText("xpath", "//Edit[@Name='Discount * Row 0, Not sorted.']", common.getData(dataFile, "discount"));
        common.clickElement("xpath","//Button[@Name='Ok']");

        saveAfterMasterCreate();
        validateMastersAndInactive("Loyalty Schemes",master);
        Thread.sleep(1000);
    }
}

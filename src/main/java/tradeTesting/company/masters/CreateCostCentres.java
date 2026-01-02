package tradeTesting.company.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class CreateCostCentres extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateCostCentres(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void costCentres() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen2Steps("Company","Cost Centres");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Cost Centres']/TreeItem[@Name='All Cost Centres']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Cost Centre *']", common.getData(dataFile, "newCostCentre") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Cost Centre *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));


        saveAfterMasterCreate();
        validateMastersAndInactive("Cost Centres",master);
        Thread.sleep(1000);
    }
}

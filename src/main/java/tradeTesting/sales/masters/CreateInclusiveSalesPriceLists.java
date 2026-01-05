package tradeTesting.sales.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateInclusiveSalesPriceLists extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateInclusiveSalesPriceLists(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createInclusiveSalesPriceLists() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Sales","Prices and Discounts","Inclusive Sales Price Lists");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Inclusive Sales Price Lists']/TreeItem[@Name='All Inclusive Sales Price Lists']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Inclusive Sales Price List *']", common.getData(dataFile, "newInclusiveSalesPriceLists") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Inclusive Sales Price List *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Inclusive Sales Price Lists",master);
        Thread.sleep(1000);
    }
}

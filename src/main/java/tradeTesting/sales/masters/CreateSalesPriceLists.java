package tradeTesting.sales.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class CreateSalesPriceLists extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateSalesPriceLists(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createSalesPriceLists() throws InterruptedException, ParseException, IOException {
        navigateToMastersWhen3Steps("Sales","Prices and Discounts","Sales Price Lists");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Sales Price Lists']/TreeItem[@Name='All Sales Price Lists']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Sales Price List *']", common.getData(dataFile, "newSalesPriceLists") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Sales Price List *']").getText();
        common.clickElement("xpath","//CheckBox[@Name='Inclusive Tax']");
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Sales Price Lists",master);
        Thread.sleep(1000);
    }
}

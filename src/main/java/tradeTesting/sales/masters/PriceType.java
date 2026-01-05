package tradeTesting.sales.masters;

import com.wings.Technical.MasterConfig;
import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class PriceType extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PriceType(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void priceType() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen2Steps("Sales","Price Types");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Price Types']/TreeItem[@Name='All Price Types']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Price Type *']", common.getData(dataFile, "newPriceType") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Price Type *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Price Types",master);
        Thread.sleep(1000);
    }
}

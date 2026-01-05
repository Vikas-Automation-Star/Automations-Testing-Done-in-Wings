package tradeTesting.purchase.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreatePurchasePriceLists extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreatePurchasePriceLists(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void purchasePriceLists() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Purchase","Price List","Purchase Price Lists");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Purchase Price Lists']/TreeItem[@Name='All Purchase Price Lists']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Purchase Price List *']", common.getData(dataFile, "newPriceLists") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Purchase Price List *']").getText();
        common.clickElement("xpath","//CheckBox[@Name='Inclusive Tax']");
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));


        saveAfterMasterCreate();
        validateMastersAndInactive("Purchase Price Lists",master);
        Thread.sleep(1000);
    }
}

package tradeTesting.purchase.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateInclusivePurchasePriceLists extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateInclusivePurchasePriceLists(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void inclusivePurchasePriceLists() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Purchase","Price List","Inclusive Purchase Price Lists");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Inclusive Purchase Price Lists']/TreeItem[@Name='All Inclusive Purchase Price Lists']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Inclusive Purchase Price List *']", common.getData(dataFile, "newInclusivePriceLists") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Inclusive Purchase Price List *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));


        saveAfterMasterCreate();
        validateMastersAndInactive("Inclusive Purchase Price Lists",master);
        Thread.sleep(1000);
    }
}

package tradeTesting.sales.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateTypesOfCustomer extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateTypesOfCustomer(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createTypesOfCustomer() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen2Steps("Sales","Types of Customer");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Types of Customer']/TreeItem[@Name='All Types of Customer']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Type Of Customer *']", common.getData(dataFile, "newTypeOfCustomer") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Type Of Customer *']").getText();
//        common.clickElement("xpath","//CheckBox[@Name='Include Branch Short Code']");
        inputTextWithValidation("xpath", "//Edit[@Name='Prefix *']", common.getData(dataFile, "prefix"));
        inputTextWithValidation("xpath", "//Edit[@Name='No Of Digits *']", common.getData(dataFile, "noOfDigits"));
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Types of Customer",master);
        Thread.sleep(1000);
    }
}

package tradeTesting.company.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateTerms extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateTerms(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void terms() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Company","Terms","Terms");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Terms']/TreeItem[@Name='All Terms']");
        Thread.sleep(2000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Term *']", common.getData(dataFile, "newTermType") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Term *']").getText();
        sendData("xpath","//Edit[@Name='Term Type *']",dataFile,"termType");
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));


        saveAfterMasterCreate();
        validateMastersAndInactive("Terms",master);
        Thread.sleep(1000);
    }
}

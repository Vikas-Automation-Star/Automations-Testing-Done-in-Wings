package tradeTesting.company.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateProfitCentres extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateProfitCentres(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void profitCentres() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen2Steps("Company","Profit Centres");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Profit Centres']/TreeItem[@Name='All Profit Centres']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Profit Centre *']", common.getData(dataFile, "newProfitCentre") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Profit Centre *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));



        saveAfterMasterCreate();
        validateMastersAndInactive("Profit Centres",master);
        Thread.sleep(1000);
    }
}

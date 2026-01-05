package tradeTesting.configure;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreateVoucherTypes extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateVoucherTypes(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createVoucherTypes() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen2Steps("Configure","Voucher Types");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Voucher Types']/TreeItem[@Name='All Voucher Types']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Voucher Type *']", common.getData(dataFile, "newVoucherType") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Voucher Type *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        inputTextWithValidation("xpath", "//Edit[@Name='Voucher Series']", common.getData(dataFile, "voucherSeries"));

        saveAfterMasterCreate();
        validateMastersAndInactive("Voucher Types",master);
        Thread.sleep(1000);
    }
}

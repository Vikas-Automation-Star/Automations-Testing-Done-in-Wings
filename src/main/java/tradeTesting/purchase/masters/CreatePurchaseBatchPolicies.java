package tradeTesting.purchase.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class CreatePurchaseBatchPolicies extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreatePurchaseBatchPolicies(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void purchaseBatchPolicies() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Purchase","Batch Policy","Purchase Batch Policies");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Purchase Batch Policies']/TreeItem[@Name='All Purchase Batch Policies']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Purchase Batch Policy *']", common.getData(dataFile, "newBatchPolicy") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Purchase Batch Policy *']").getText();
        sendData("xpath","//Edit[@Name='Batch Policy Type *']",dataFile,"batchPolicyType");
        inputTextWithValidation("xpath", "//Edit[@Name='Seperator']", common.getData(dataFile, "separator"));
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        common.clickElement("xpath","//Text[@Name='Purchase Batch Policy']/following-sibling::Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='Include Option * Row 0, Not sorted.']", common.getData(dataFile, "includeOption"));
        common.inputText("xpath", "//Edit[@Name='Policy Sequence * Row 0, Not sorted.']", common.getData(dataFile, "policySequence"));
        common.inputText("xpath", "//Edit[@Name='Policy Option Format * Row 0, Not sorted.']", common.getData(dataFile, "policyOptionFormat"));
        common.inputText("xpath", "//Edit[@Name='Policy Text Row 0, Not sorted.']", common.getData(dataFile, "policyText"));
        common.clickElement("xpath","//Button[@Name='Ok']");

        saveAfterMasterCreate();
        validateMastersAndInactive("Purchase Batch Policies",master);
        Thread.sleep(1000);
    }
}

package com.wings.pages.purchase.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import java.io.IOException;


public class PriceList extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public PriceList(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createPriceList() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Purchase","Price List","Purchase Price Lists");
        Thread.sleep(2000);
        super.createMaster("xpath", "//TreeItem[@Name='Purchase Price Lists']/TreeItem[@Name='All Purchase Price Lists']");
        Thread.sleep(1000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Purchase Price List *']", common.getData(dataFile, "PurchasePriceList") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Purchase Price List *']").getText();
        super.inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        super.saveAfterMasterCreate();
        validateMastersAndInactive("Purchase Price Lists","All Purchase Price Lists",master,"Purchase Price Lists");
    }
}

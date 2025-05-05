package com.wings.pages.inventory.masters.product.attributes;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class ProductStyles extends Masters {

    WindowsDriver driver;
    Common common;
    String filepath;

    public ProductStyles(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(driver);
        filepath = file;
    }

    public void productStyles() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen4Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"), common.getData(filepath,"thirdMenu"), common.getData(filepath,"fourthMenu") );
        createMaster("xpath", "//TreeItem[@Name='Product Styles']/TreeItem[@Name='All Product Styles']");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New Product Style *']", common.getData(filepath, "newAccount") + common.getRandom());
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(filepath,"description"));
        saveMaster();
        closeMaster(common.getData(filepath,"menuItem"));
        //validate
        navigateToMastersWhen4Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"), common.getData(filepath,"thirdMenu"), common.getData(filepath,"fourthMenu") );
        validateAndInactivate(common.getData(filepath,"menuItem"), common.getData(filepath,"newAccount") );
    }
}
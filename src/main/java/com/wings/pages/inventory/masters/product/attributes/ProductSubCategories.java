package com.wings.pages.inventory.masters.product.attributes;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;

public class ProductSubCategories extends Masters {
    WindowsDriver driver;
    Common common;
    String filepath;

    public ProductSubCategories(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(driver);
        filepath = file;
    }

    public void productSubCategories() throws InterruptedException, AWTException, IOException, ParseException {
        navigateToMastersWhen4Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"), common.getData(filepath,"thirdMenu"), common.getData(filepath,"fourthMenu") );
        createMaster("xpath", "//TreeItem[@Name='Product Sub Categories']/TreeItem[@Name='All Product Sub Categories']");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New Product Sub Category *']", common.getData(filepath, "newAccount") + common.getRandom());
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(filepath,"description"));
        saveMaster();
        closeMaster(common.getData(filepath,"menuItem"));
        //validate
        navigateToMastersWhen4Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"), common.getData(filepath,"thirdMenu"), common.getData(filepath,"fourthMenu") );
        validateAndInactivate(common.getData(filepath,"menuItem"), common.getData(filepath,"newAccount") );
    }
}

package com.wings.pages.inventory.masters.product.attributes;

import com.wings.pages.Masters;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ProductBrands extends Masters {
    WindowsDriver driver;
    Common common;
    String filepath;

    public ProductBrands(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(driver);
        filepath = file;
    }

    public void productBrands() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen4Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"), common.getData(filepath,"thirdMenu"), common.getData(filepath,"fourthMenu") );
        createMaster("xpath", "//TreeItem[@Name='Product Brands']/TreeItem[@Name='All Product Brands']");
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='New Product Brand *']", common.getData(filepath, "newAccount") + common.getRandom());
        common.inputText("xpath", "//Edit[@Name='Description']", common.getData(filepath,"description"));
        saveMaster();
        closeMaster(common.getData(filepath,"menuItem"));
        //validate
        navigateToMastersWhen4Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"), common.getData(filepath,"thirdMenu"), common.getData(filepath,"fourthMenu") );
        validateAndInactivate(common.getData(filepath,"menuItem"), common.getData(filepath,"newAccount") );
    }
}

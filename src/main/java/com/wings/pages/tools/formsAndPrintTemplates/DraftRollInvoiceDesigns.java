package com.wings.pages.tools.formsAndPrintTemplates;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class DraftRollInvoiceDesigns extends Masters {

        WindowsDriver driver;
        Common common;
        String filepath;

        public DraftRollInvoiceDesigns(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(driver);
            filepath = file;
        }

        public void draftRollInvoiceDesigns() throws InterruptedException, IOException, ParseException {
            navigateToMastersWhen4Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"), common.getData(filepath,"thirdMenu"), common.getData(filepath,"fourthMenu") );
            createMaster("xpath", "//TreeItem[@Name='Draft Roll Invoice Designs']/TreeItem[@Name='All Draft Roll Invoice Designs']");
            Thread.sleep(2000);
            common.inputText("xpath", "//Edit[@Name='New Draft Roll Invoice Design *']", common.getData(filepath, "newAccount") + common.getRandom());
            common.inputText("xpath", "//Edit[@Name='Description']", common.getData(filepath,"description"));
            saveMaster();
            closeMaster(common.getData(filepath,"menuItem"));
            //validate
            navigateToMastersWhen4Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"), common.getData(filepath,"thirdMenu"), common.getData(filepath,"fourthMenu") );
            validateAndInactivate(common.getData(filepath,"menuItem"), common.getData(filepath,"newAccount") );
        }
    }
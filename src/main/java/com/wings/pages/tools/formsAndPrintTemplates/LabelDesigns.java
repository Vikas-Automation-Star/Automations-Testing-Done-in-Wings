package com.wings.pages.tools.formsAndPrintTemplates;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class LabelDesigns extends Masters {

        WindowsDriver driver;
        Common common;
        String filepath;

        public LabelDesigns(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(driver);
            filepath = file;
        }

        public void labelDesigns() throws InterruptedException, IOException, ParseException {
            navigateToMastersWhen3Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"), common.getData(filepath,"thirdMenu"));
            createMaster("xpath", "//TreeItem[@Name='Label Designs']/TreeItem[@Name='All Label Designs']");
            Thread.sleep(2000);
            common.inputText("xpath", "//Edit[@Name='Create New Label Design *']", common.getData(filepath, "newAccount") + common.getRandom());
            common.inputText("xpath", "//Edit[@Name='Description']", common.getData(filepath,"description"));
            saveMaster();
            closeMaster(common.getData(filepath,"menuItem"));
            //validate
            navigateToMastersWhen3Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"), common.getData(filepath,"thirdMenu"));
            validateAndInactivate(common.getData(filepath,"menuItem"), common.getData(filepath,"newAccount") );
        }
    }
package com.wings.pages.configure.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class DocumentTypes extends Masters {

        WindowsDriver driver;
        Common common;
        String filepath;

        public DocumentTypes(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(driver);
            filepath = file;
        }

        public void documentTypes() throws InterruptedException, IOException, ParseException {
            navigateToMastersWhen2Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"));
            createMaster("xpath", "//TreeItem[@Name='Document Types']/TreeItem[@Name='All Document Types']");
            Thread.sleep(2000);
            common.inputText("xpath", "//Edit[@Name='New Document Type *']", common.getData(filepath, "newAccount") + common.getRandom());
            common.inputText("xpath", "//Edit[@Name='Description']", common.getData(filepath,"description"));
            saveMaster();
            closeMaster(common.getData(filepath,"menuItem"));
            //validate
            navigateToMastersWhen2Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"));
            validateAndInactivate(common.getData(filepath,"menuItem"), common.getData(filepath,"newAccount") );
        }
    }
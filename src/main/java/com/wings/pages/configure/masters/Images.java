package com.wings.pages.configure.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class Images extends Masters{

        WindowsDriver driver;
        Common common;
        String filepath;

        public Images(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(driver);
            filepath = file;
        }

        public void images() throws InterruptedException, IOException, ParseException {
            navigateToMastersWhen3Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"),common.getData(filepath,"thirdMenu"));
            createMaster("xpath", "//TreeItem[@Name='Images']/TreeItem[@Name='All Images']");
            Thread.sleep(2000);
            common.inputText("xpath", "//Edit[@Name='New Image *']", common.getData(filepath, "newAccount") + common.getRandom());
            common.inputText("xpath", "//Edit[@Name='Description']", common.getData(filepath,"description"));
            saveMaster();
            closeMaster(common.getData(filepath,"menuItem"));
            //validate
            navigateToMastersWhen3Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"),common.getData(filepath,"thirdMenu"));
            validateAndInactivate(common.getData(filepath,"menuItem"), common.getData(filepath,"newAccount") );
        }
    }
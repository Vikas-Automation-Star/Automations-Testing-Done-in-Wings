package com.wings.pages.tools.formsAndPrintTemplates.dotMatrixPrinters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class DraftPageLayout extends Masters {

        WindowsDriver driver;
        Common common;
        String filepath;

        public DraftPageLayout(WindowsDriver driver, String file) {
            super(driver);
            this.driver = driver;
            common = new Common(driver);
            filepath = file;
        }

        public void draftPageLayout() throws InterruptedException, IOException, ParseException {
            navigateToMastersWhen4Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"), common.getData(filepath,"thirdMenu"), common.getData(filepath,"fourthMenu") );
            createMaster("xpath", "//TreeItem[@Name='Draft Page Layout']/TreeItem[@Name='All Draft Page Layout']");
            Thread.sleep(2000);
            common.inputText("xpath", "//Edit[@Name='New Draft Page Layout *']", common.getData(filepath, "newAccount") + common.getRandom());
            saveMaster();
            closeMaster(common.getData(filepath,"menuItem"));
            //validate
            navigateToMastersWhen4Steps(common.getData(filepath,"menu"), common.getData(filepath,"secondMenu"), common.getData(filepath,"thirdMenu"), common.getData(filepath,"fourthMenu") );
            validateAndInactivate(common.getData(filepath,"menuItem"), common.getData(filepath,"newAccount") );
        }
    }
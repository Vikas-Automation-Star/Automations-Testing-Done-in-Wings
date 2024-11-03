package com.wings.pages.sales.reports;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import com.wings.pages.Report;
import com.wings.utils.Common;
import java.io.IOException;

public class TargetVarianceReportsExecWiseReportCode extends Report {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public TargetVarianceReportsExecWiseReportCode(WindowsDriver driver, String file){
        super(driver);
        this.driver=driver;
        common=new Common(this.driver);
        dataFile=file;
    }

    public void TargetVarianceReport() throws InterruptedException, IOException, ParseException{
        common.clickElement("name", "Sales");
        common.clickElement("name", "Targets");
        common.clickElement("name", "Target Variance Report Executive Wise");
        Thread.sleep(1000);
        common.inputText("xpath","//Edit[@Name='From  Year Month[YYYYMM]']",common.getData(dataFile, "from"));
        common.inputText("xpath","//Edit[@Name='To  Year Month[YYYYMM]']", common.getData(dataFile, "to"));
        common.clickElement("xpath","//Pane/Button[@Name='Submit']");

        super.closeReport("Target Variance Report Executive Wise");

    }
}

package com.wings.pages.taxes.reports.GST;

import com.wings.pages.Report;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Gst3B extends Report {
    WindowsDriver driver;
    Common common;
    String datFile;

    public Gst3B(WindowsDriver driver,String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        datFile=file;
    }
    public void gst3B() throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Taxes");
        common.clickElement("name", "GST");
        common.clickElement("name", "GSTR3");
        common.clickElement("name", "GSTR 3B");
        Thread.sleep(2000);
        common.clickElement("xpath", "//Button[@Name='Select']");
        common.clickElement("xpath", "//CheckBox[@Name='Select Row 0']");
        common.clickElement("xpath","//Button[@Name='Ok']");
        common.inputText("xpath","//Edit[@Name='Month']", common.getData(datFile,"month"));
        common.inputText("xpath","//Edit[@Name='Year']", common.getData(datFile,"year"));
        common.clickElement("xpath", "//Button[@Name='View Report']");
        Thread.sleep(5000);
//        common.clickElement("xpath", "//Button[@Name='Submit']");
        super.bulkVerifyReport("");
        super.closeReport("GSTR 3B");
        Thread.sleep(2000);
    }
}


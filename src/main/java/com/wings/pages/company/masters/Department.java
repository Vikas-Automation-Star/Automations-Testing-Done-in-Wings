package com.wings.pages.company.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Department extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public Department(WindowsDriver driver, String file){
        super(driver);
        this.driver=driver;
        common=new Common(this.driver);
        dataFile=file;
    }
    public void departments() throws InterruptedException, IOException, ParseException {
        common.clickElement("name", "Company");
        common.clickElement("name", "Departments");
        Thread.sleep(1000);
        super.createMaster("xpath","//TreeItem[@Name='Departments']/TreeItem[@Name='All Departments']");
        Thread.sleep(1500);
        common.inputText("xpath","//Edit[@Name='New Department *']",common.getData(dataFile,"newDepartment")+common.getRandom());
        common.inputText("xpath","//Edit[@Name='Description']",common.getData(dataFile,"description"));
        super.saveAfterMasterCreate();
        super.closeMaster("Departments");
        Thread.sleep(2000);

    }

}

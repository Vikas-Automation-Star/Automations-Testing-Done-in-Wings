package com.wings.pages.company.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class CompanyProperties extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CompanyProperties(WindowsDriver driver, String file){
        super(driver);
        this.driver=driver;
        common=new Common(this.driver);
        dataFile=file;
    }

    public void companyProperty() throws IOException, ParseException, InterruptedException, AWTException {
        common.clickElement("name", "Company");
        common.clickElement("name", "Company Properties");
        Thread.sleep(2000);
        super.inputTextWithValidation("xpath","//Edit[@Name='Registration Certificate No']",common.getData(dataFile,"Registration certificateNo")+common.getRandom());
        super.inputTextWithValidation("xpath","//Edit[@Name='PAN No']",common.getData(dataFile,"PAN NO"));
        common.clickElement("xpath","//Button[@Name='...']");
        Thread.sleep(1000);
        super.inputTextWithValidation("xpath","//Edit[@Name='Company']",common.getData(dataFile,"comapny"));
        super.inputTextWithValidation("xpath","//Edit[@Name='Address 1']",common.getData(dataFile,"address1"));
        super.inputTextWithValidation("xpath","//Edit[@Name='Address 2']",common.getData(dataFile,"address2"));
        super.inputTextWithValidation("xpath","//Edit[@Name='Address 3']",common.getData(dataFile,"address3"));
        super.inputTextWithValidation("xpath","//Edit[@Name='City']",common.getData(dataFile,"city"));
        WebElement country= common.findWebElement("xpath","//Edit[@Name='Country']/Button[@Name='Open']");
        country.click();
        country.sendKeys(common.getData(dataFile,"country"), Keys.ENTER);
        super.inputTextWithValidation("xpath","//Edit[@Name='Zip']",common.getData(dataFile,"zip"));
        super.inputTextWithValidation("xpath","//Edit[@Name='Telephones 1']",common.getData(dataFile,"telephone1"));
        super.inputTextWithValidation("xpath","//Edit[@Name='Telephones 2']",common.getData(dataFile,"telephone2"));
        super.inputTextWithValidation("xpath","//Edit[@Name='Telephones 3']",common.getData(dataFile,"telephone3"));
        super.inputTextWithValidation("xpath","//Edit[@Name='Telephones 4']",common.getData(dataFile,"telephone4"));
        super.inputTextWithValidation("xpath","//Edit[@Name='Fax']",common.getData(dataFile,"fax"));
        super.inputTextWithValidation("xpath","//Edit[@Name='Email']",common.getData(dataFile,"email"));
        super.inputTextWithValidation("xpath","//Edit[@Name='Website']",common.getData(dataFile,"website"));
        common.clickElement("xpath","//Button[@Name='Ok']");
        Thread.sleep(1000);
        common.clickElement("xpath","//Edit[@Name='Stock Valuation Method']/Button[@Name='Open']");
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        super.inputTextWithValidation("xpath","//Edit[@Name='Short Code']", common.getData(dataFile,"shortCode")+common.getRandom());
        Thread.sleep(1000);
        super.closeMaster("Companies");
    }

}

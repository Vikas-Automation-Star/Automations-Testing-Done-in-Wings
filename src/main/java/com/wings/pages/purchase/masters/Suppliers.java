package com.wings.pages.purchase.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.wings.utils.Common;
import java.awt.*;
import java.io.IOException;

public class Suppliers {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public Suppliers(WindowsDriver driver,String file){
        this.driver=driver;
        common=new Common(this.driver);
        dataFile=file;
    }

    public void suppliers() throws InterruptedException {
        common.clickElement("name","Purchase");
        common.clickElement("name","Suppliers");
        WebElement allCustomer = common.findWebElement("xpath", "//TreeItem[@Name='Suppliers']/TreeItem[@Name='All Suppliers']");
        Actions actions = new Actions(driver);
        actions.contextClick(allCustomer).perform();
        common.clickElement("name", "New Master");
    }
        public void newSupplier() throws IOException, ParseException, AWTException, InterruptedException {
            common.inputText("xpath","//Edit[@Name='New Supplier *']", common.getData(dataFile,"newSupplier")+common.getRandom());
            common.inputText("xpath","//Edit[@Name='Supplier Code']",""+common.getRandom());
            common.inputText("xpath","//Edit[@Name='Description']", common.getData(dataFile,"description"));
            common.clickElement("xpath","//Pane[@Name='BankMaster Details']/Button[@Name='...']");
            //manageCurrency details
            common.inputText("xpath","//Edit[@Name='Account No']", common.getData(dataFile,"AccNo"));
            common.inputText("xpath","//Edit[@Name='BankMaster']", common.getData(dataFile,"BankMaster"));
            common.inputText("xpath","//Edit[@Name='BankMaster Branch']", common.getData(dataFile,"BankBranch"));
            common.inputText("xpath","//Edit[@Name='IFSC Code']", common.getData(dataFile,"IFSC"));
            common.clickElement("name","Ok");


//            common.clickElement("xpath","//Edit[@Name='Transport']/Button[@Name='Open'  ]");
//            Robot robot=new Robot();
//            robot.keyPress(KeyEvent.VK_DOWN);
//            robot.keyRelease(KeyEvent.VK_DOWN);
//            robot.keyPress(KeyEvent.VK_DOWN);
//            robot.keyRelease(KeyEvent.VK_DOWN);
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
//
//            WebElement typeOfCust= common.findWebElement("xpath","//Edit[@Name='Type Of Customer']");
//            typeOfCust.click();
//            typeOfCust.sendKeys(Keys.TAB,common.getData(dataFile,"aadhar"),Keys.TAB,Keys.TAB,Keys.ENTER);
//
//            common.inputText("xpath","//Window[@Name='Registration']/Pane/Pane/Edit[@Name='Party Reg Type *']","registered");
//            Robot robot1=new Robot();
//            robot1.keyPress(KeyEvent.VK_DOWN);
//            robot1.keyRelease(KeyEvent.VK_DOWN);
//            robot1.keyPress(KeyEvent.VK_ENTER);
//            robot1.keyRelease(KeyEvent.VK_ENTER);
//            WebElement gst= common.findWebElement("xpath","//Edit[@Name='GSTIN']");
//            gst.sendKeys(common.getData(dataFile,"gst"),Keys.TAB,common.getData(dataFile,"pan"),Keys.TAB,Keys.TAB,common.getData(dataFile,"LName"),Keys.TAB,common.getData(dataFile,"Trade name"),Keys.TAB,common.getData(dataFile,"Add1"),Keys.TAB,common.getData(dataFile,"Add2"),Keys.TAB,common.getData(dataFile,"Add3"),Keys.TAB,common.getData(dataFile,"City"));
//            common.inputText("xpath","//Edit[@Name='ZIP *']", common.getData(dataFile,"Zip1"));
//            common.clickElement("name","Ok");
//            Thread.sleep(3000);
//
//
//            common.clickElement("xpath","//Pane/Pane[@Name='Contact Details']/Button[@Name='...']");
//
//            WebElement telephone=common.findWebElement("xpath","//Edit[@Name='Telephones 1']");
//            telephone.sendKeys( common.getData(dataFile,"tel1"),Keys.TAB, common.getData(dataFile,"tel2"),Keys.TAB, common.getData(dataFile,"tel3"),Keys.TAB,common.getData(dataFile,"tel4"),Keys.TAB, common.getData(dataFile,"fax"),Keys.TAB, common.getData(dataFile,"email"),Keys.TAB, common.getData(dataFile,"website"),Keys.TAB, common.getData(dataFile,"cp"),Keys.TAB, common.getData(dataFile,"cpd"),Keys.TAB, common.getData(dataFile,"cptn"),Keys.TAB, common.getData(dataFile,"cpmn"), Keys.TAB, common.getData(dataFile,"cpe"),Keys.TAB,Keys.ENTER);
//            //TDS
//            WebElement tds= common.findWebElement("xpath","//CheckBox[@Name='Apply TDS']");
//            tds.click();
//            tds.sendKeys(Keys.TAB,Keys.TAB,Keys.SPACE,Keys.TAB,Keys.TAB,Keys.ENTER, common.getData(dataFile,"gst"),Keys.TAB, common.getData(dataFile,"addressName"),Keys.TAB, common.getData(dataFile,"Add1"),Keys.TAB, common.getData(dataFile,"Add2"),Keys.TAB, common.getData(dataFile,"Add3"),Keys.TAB, common.getData(dataFile,"City"),Keys.TAB, common.getData(dataFile,"state"),Keys.ENTER,common.getData(dataFile,"Zip1"),Keys.TAB, common.getData(dataFile,"country"),Keys.TAB, common.getData(dataFile,"tel1"),Keys.TAB,common.getData(dataFile,"cpmn"));
//            common.clickElement("name","Ok");
//
//            common.clickElement("xpath","//Pane/Button[@Name='Save']");
//            Thread.sleep(1000);
//            common.clickElement("xpath","//Window/Button[@Name='OK']");
//            Thread.sleep(1000);
//            common.clickElement("xpath","//Pane/Button[@Name='Cancel']");

//            common.clickElement("xpath", "//TabItem[@Name='Suppliers']/Button[@Name='Close']");
    }
}

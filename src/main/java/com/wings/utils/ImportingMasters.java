package com.wings.utils;

import com.wings.pages.Transaction;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImportingMasters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public ImportingMasters(WindowsDriver driver,String file) {
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void masterImport() throws InterruptedException, IOException, ParseException {
        common.clickElement("xpath","//MenuItem[@Name='Tools']");
        common.clickElement("xpath","//MenuItem[@Name='Import Data']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        common.clickElement("xpath","//RadioButton[@Name='Masters']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        Thread.sleep(1500);
        WebElement fetchMaster=common.findWebElement("xpath","//Pane/ComboBox[@Name=concat('Specify data type and click on ', \"'\", 'Download File', \"'\", ' button to download template to prepare your data.')]/*[@Name=concat('Specify data type and click on ', \"'\", 'Download File', \"'\", ' button to download template to prepare your data.')]\n");
        fetchMaster.click();
        fetchMaster.sendKeys(common.getData(dataFile,"branch"), Keys.DOWN,Keys.ENTER);
        common.clickElement("xpath","//RadioButton[@Name=\"I don't have a file\"]");
        common.clickElement("xpath","//Pane[@Name='Data Import Centre']/Pane[2]/Edit");
        common.clickElement("xpath","//Window[@Name='Message']/Button[@Name='OK']");
        System.out.println("file downloaded"+new String(Character.toChars(0x1F602)));
        common.clickElement("xpath","//RadioButton[@Name='I have a file']");
        common.clickElement("xpath","//Button[@Name='Browse']");
        common.clickElement("xpath","//ListItem[@Name='Import_Branches.CSV']/Edit[@Name='Name']");
        Thread.sleep(1000);
        WebElement clickOpen=common.findWebElement("xpath","//ComboBox[@Name='Files of type:']/Button[@Name='Open']");
        clickOpen.sendKeys(Keys.TAB,Keys.ENTER);
        common.clickElement("xpath","//Button[@Name='Next >']");

    }
}

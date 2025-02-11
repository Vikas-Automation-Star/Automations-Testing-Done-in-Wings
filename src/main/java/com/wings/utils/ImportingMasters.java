package com.wings.utils;


import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ImportingMasters {
    WindowsDriver driver;
    Common common;
    String dataFile;
    String masterData, masterDataValidate;

    public ImportingMasters(WindowsDriver driver, String file) {
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void suppliersMastersImport(String selectMaster) throws InterruptedException, IOException, ParseException {
        common.clickElement("xpath", "//MenuItem[@Name='Tools']");
        common.clickElement("xpath", "//MenuItem[@Name='Import Data']");
        common.clickElement("xpath", "//Button[@Name='Next >']");
        common.clickElement("xpath", "//RadioButton[@Name='Masters']");
        common.clickElement("xpath", "//Button[@Name='Next >']");
        Thread.sleep(1500);
        WebElement fetchMaster = common.findWebElement("xpath", "//Pane/ComboBox[@Name=concat('Specify data type and click on ', \"'\", 'Download File', \"'\", ' button to download template to prepare your data.')]/*[@Name=concat('Specify data type and click on ', \"'\", 'Download File', \"'\", ' button to download template to prepare your data.')]\n");
        fetchMaster.click();
        fetchMaster.sendKeys(selectMaster);//, Keys.DOWN, Keys.DOWN,Keys.ENTER);
//        common.clickElement("xpath","//RadioButton[@Name=\"I don't have a file\"]");
//        common.clickElement("xpath","//Pane[@Name='Data Import Centre']/Pane[2]/Edit");
//        common.clickElement("xpath","//Window[@Name='Message']/Button[@Name='OK']");
//        System.out.println("file downloaded"+new String(Character.toChars(0x1F602)));
//           WebElement enterData=common.findWebElement("xpath","//Pane/Edit");
//        enterData.sendKeys(common.getProperty("filePath"));
        WebElement focus = common.findWebElement("xpath", "//RadioButton[@Name='I have a file']");
        if (focus.isSelected() == true) {
            System.out.println("element clicked");
        } else {
            focus.click();
        }
        common.clickElement("xpath", "//Button[@Name='Browse']");
        WebElement enter = common.findWebElement("xpath", "//Edit[@Name='File name:']");
        enter.click();
        enter.sendKeys(common.getData(dataFile, "supplierPath"));
        WebElement clickOpen = common.findWebElement("xpath", "//ComboBox[@Name='Files of type:']/Button[@Name='Open']");
        clickOpen.sendKeys(Keys.TAB, Keys.ENTER);
        common.clickElement("xpath", "//Button[@Name='Next >']");
        common.clickElement("xpath", "//Button[@Name='Next >']");
        Thread.sleep(1000);
        common.sliderHandling("xpath", "//Pane/Table[@Name='ImportSuppliers']/ScrollBar[@Name='Vertical']/*[@Name='Position']", 0, -50);
        List<WebElement> fetch = common.findWebElements("xpath", "//Table[@Name='ImportSuppliers']/*[starts-with(@Name,'Row')]/*[contains(@Name,'Supplier * Row')]");
        System.out.println("Size of fetching: " + fetch.size());
        for (int i = 0; i < fetch.size() - 1; i++) {
            WebElement element1 = fetch.get(i);
            System.out.println("Element Text: " + element1.getText());
        }
        List<String> list1Texts = fetch.stream().map(WebElement::getText).filter(text -> !text.isEmpty()).map(String::trim).collect(Collectors.toList());
        list1Texts.remove(list1Texts.size() - 1);
        System.out.println("list1TextsData :" + list1Texts);
        common.clickElement("xpath", "//Button[@Name='Next >']");
        WebElement validate = common.findWebElement("xpath", "//Pane/*[@Name='No errors found when importing data']");
        if (validate.getText().equals("No errors found when importing data")) {
            System.out.println("no mistakes in the MasterData :" + new String(Character.toChars(0x1F602)));
            common.clickElement("xpath", "//Button[@Name='Next >']");
            WebElement validate1 = common.findWebElement("xpath", "//Window[@Name='Import Data']/*/*[@Name='Data Import Centre']/Text[1]");
            System.out.println("conformationText :" + validate1.getText());
            if (validate1.getText().equals("Saved Successfully")) {
                System.out.println("Master data imported successfully :" + new String(Character.toChars(0x1F601)));
            } else if (!validate1.getText().equals("Saved Successfully")) {
                WebElement errorValidate = common.findWebElement("xpath", "//Window[@Name='Import Data']/*/*[@Name='Data Import Centre']/Text[1]");
                System.out.println("conformationText2: " + errorValidate.getText());
                if (errorValidate.getText().contains("Some errors are found")) {
                    System.out.println("You made a mistake in the Masters import, which might be due to identical names or existing names already being present or No data");
                }
            } else {
                System.out.println("some errors occur in the importing masters check again");
            }
        }
        common.clickElement("xpath", "//Button[@Name='Next >']");
        common.clickElement("xpath", "//Button[@Name='Finish']");
        common.clickElement("xpath", "//MenuItem[@Name='Purchase']");
        common.clickElement("xpath", "//MenuItem[@Name='Suppliers']");
        common.clickElement("xpath", "//TreeItem[@Name='Suppliers']/TreeItem[@Name='All Suppliers']");
        List<WebElement> fetching = common.findWebElements("xpath", "//Pane/List/ListItem");
        System.out.println("Size of fetching: " + fetching.size());
        for (WebElement v : fetching) {
            masterDataValidate = v.getText();
            System.out.println("masterDataValidate :" + masterDataValidate);
        }
        List<String> list2Texts = fetching.stream().map(WebElement::getText).filter(text -> !text.isEmpty()).map(String::trim).collect(Collectors.toList());
        System.out.println("list2TextsData :" + list2Texts);
        Collections.sort(list2Texts);
        // Compare the two lists
        for (String text1 : list1Texts) {
            if (list2Texts.contains(text1)) {
                System.out.println("Match found for: " + text1);
                System.out.println("Data Imported Successfully "+new String(Character.toChars(0x1F601)));

            } else {
                System.out.println("No match for: " + text1);
            }
        }
//        if (list2Texts.containsAll(list1Texts) && list1Texts.containsAll(list2Texts)) {
//            System.out.println("Data Imported Successfully");
//        } else {
//            System.out.println("The lists have different sizes.");
//            Assert.fail("The lists are not the same Names.");
//        }
    }

    public void productsMastersImport(String selectMaster) throws InterruptedException, IOException, ParseException {
        common.clickElement("xpath", "//MenuItem[@Name='Tools']");
        common.clickElement("xpath", "//MenuItem[@Name='Import Data']");
        common.clickElement("xpath", "//Button[@Name='Next >']");
        common.clickElement("xpath", "//RadioButton[@Name='Masters']");
        common.clickElement("xpath", "//Button[@Name='Next >']");
        Thread.sleep(1500);
        WebElement fetchMaster = common.findWebElement("xpath", "//Pane/ComboBox[@Name=concat('Specify data type and click on ', \"'\", 'Download File', \"'\", ' button to download template to prepare your data.')]/*[@Name=concat('Specify data type and click on ', \"'\", 'Download File', \"'\", ' button to download template to prepare your data.')]\n");
        fetchMaster.click();
        fetchMaster.sendKeys(selectMaster, Keys.DOWN, Keys.ENTER);
//        common.clickElement("xpath","//RadioButton[@Name=\"I don't have a file\"]");
//        common.clickElement("xpath","//Pane[@Name='Data Import Centre']/Pane[2]/Edit");
//        common.clickElement("xpath","//Window[@Name='Message']/Button[@Name='OK']");
//        System.out.println("file downloaded"+new String(Character.toChars(0x1F602)));
//        WebElement enterData=common.findWebElement("xpath","//Pane/Edit");
//        enterData.sendKeys(common.getProperty("filePath"));
        WebElement focus = common.findWebElement("xpath", "//RadioButton[@Name='I have a file']");
        if (focus.isSelected() == true) {
            System.out.println("element clicked");
        } else {
            focus.click();
        }
        common.clickElement("xpath", "//Button[@Name='Browse']");
        WebElement enter = common.findWebElement("xpath", "//Edit[@Name='File name:']");
        enter.click();
        enter.sendKeys(common.getData(dataFile, "productsPath"));
        WebElement clickOpen = common.findWebElement("xpath", "//ComboBox[@Name='Files of type:']/Button[@Name='Open']");
        clickOpen.sendKeys(Keys.TAB, Keys.ENTER);
        common.clickElement("xpath", "//Button[@Name='Next >']");
        common.clickElement("xpath", "//Button[@Name='Next >']");
        Thread.sleep(1000);
        common.sliderHandling("xpath", "//Pane/Table[@Name='ImportProducts']/ScrollBar[@Name='Vertical']/*[@Name='Position']", 0, -50);
        List<WebElement> fetchProducts = common.findWebElements("xpath", "//Table[@Name='ImportProducts']/*[starts-with(@Name,'Row')]/*[contains(@Name,'Product * Row')]");
        System.out.println("Size of fetching: " + fetchProducts.size());
        for (int i = 0; i < fetchProducts.size() - 1; i++) {
            WebElement masterData = fetchProducts.get(i);
            System.out.println("Element Text: " + masterData.getText());
        }
        List<String> list1Texts = fetchProducts.stream().map(WebElement::getText).filter(text -> !text.isEmpty()).map(String::trim).collect(Collectors.toList());
        list1Texts.remove(list1Texts.size() - 1);
        System.out.println("list1TextsData :" + list1Texts);
        common.clickElement("xpath", "//Button[@Name='Next >']");
        WebElement dataValidate = common.findWebElement("xpath", "//Window[@Name='Import Data']/*[@Name='write header text here']/*[@Name='Data Import Centre']/Text[1]");
        System.out.println("test :" + dataValidate.getText());
        if (dataValidate.getText().contains("Value not supplied to")) {
            System.out.println("You missed some data might be Name,Code,Node,Description");
        } else if (dataValidate.getText().contains("No errors found when importing data")) {
            System.out.println("you can move to Nest button");
        }
        common.clickElement("xpath", "//Button[@Name='Next >']");
        WebElement validate = common.findWebElement("xpath", "//Window[@Name='Import Data']/*[@Name='write header text here']/*[@Name='Data Import Centre']/Text[1]");
        if (validate.getText().contains("Some errors are found")) {
            Assert.fail("when You missed data might be Name,Code,Node,Description you will get this Errors" + " And We can't move to further also");
        } else if (validate.getText().equals("No errors found when importing data")) {
            System.out.println("no mistakes in the MasterData :" + new String(Character.toChars(0x1F602)));
            common.clickElement("xpath", "//Button[@Name='Next >']");
            WebElement validate1 = common.findWebElement("xpath", "//Window[@Name='Import Data']/*/*[@Name='Data Import Centre']/Text[1]");
            System.out.println("conformationText :" + validate1.getText());
            if (validate1.getText().equals("Saved Successfully")) {
                System.out.println("Master data imported successfully Great Job");
            } else if (!validate1.getText().equals("Saved Successfully")) {
                WebElement errorValidate = common.findWebElement("xpath", "//Window[@Name='Import Data']/*/*[@Name='Data Import Centre']/Text[1]");
                System.out.println("conformationText2: " + errorValidate.getText());
                if (errorValidate.getText().contains("Some errors are found")) {
                    System.out.println("You made a mistake in the Masters import, which might be due to identical names or existing names already being present");
                }
            }
        }
        common.clickElement("xpath", "//Button[@Name='Next >']");
        common.clickElement("xpath", "//Button[@Name='Finish']");
        common.clickElement("xpath", "//MenuItem[@Name='Inventory']");
        common.clickElement("xpath", "//MenuItem[@Name='Product']");
        common.clickElement("xpath", "//MenuItem[@Name='Products']");
        common.clickElement("xpath", "//TreeItem[@Name='Products']/TreeItem[@Name='All Products']");
        List<WebElement> fetching = common.findWebElements("xpath", "//Pane/List/ListItem");
        System.out.println("Size of fetching: " + fetching.size());
        for (WebElement v : fetching) {
            String masterDataValidate = v.getText();
            System.out.println("masterDataValidate :" + masterDataValidate);
        }
        List<String> list2Texts = fetching.stream().map(WebElement::getText).filter(text -> !text.isEmpty()).map(String::trim).collect(Collectors.toList());
        System.out.println("list2TextsData :" + list2Texts);
        // Compare the two lists
        for (String text1 : list1Texts) {
            if (list2Texts.contains(text1)) {
                System.out.println("Match found for: " + text1);
                System.out.println("Data Imported Successfully "+new String(Character.toChars(0x1F601)));
            } else {
                System.out.println("No match for: " + text1);
            }
        }
//        if (list2Texts.containsAll(list1Texts) && list1Texts.containsAll(list2Texts)) {
//            System.out.println("Data Imported Successfully " + new String(Character.toChars(0x1F601)));
//        } else {
//            System.out.println("The lists have different sizes.");
//            Assert.fail("The lists are not the same Names.");
//        }
    }

    public void customerMastersImport(String selectMaster) throws InterruptedException, IOException, ParseException {
        common.clickElement("xpath","//MenuItem[@Name='Tools']");
        common.clickElement("xpath","//MenuItem[@Name='Import Data']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        common.clickElement("xpath","//RadioButton[@Name='Masters']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        Thread.sleep(1500);
        WebElement fetchMaster=common.findWebElement("xpath","//Pane/ComboBox[@Name=concat('Specify data type and click on ', \"'\", 'Download File', \"'\", ' button to download template to prepare your data.')]/*[@Name=concat('Specify data type and click on ', \"'\", 'Download File', \"'\", ' button to download template to prepare your data.')]\n");
        fetchMaster.click();
        fetchMaster.sendKeys(selectMaster,Keys.DOWN,Keys.ENTER);
//        common.clickElement("xpath","//RadioButton[@Name=\"I don't have a file\"]");
//        common.clickElement("xpath","//Pane[@Name='Data Import Centre']/Pane[2]/Edit");
//        common.clickElement("xpath","//Window[@Name='Message']/Button[@Name='OK']");
//        System.out.println("file downloaded"+new String(Character.toChars(0x1F602)));
//        WebElement enterData=common.findWebElement("xpath","//Pane/Edit");
//        enterData.sendKeys(common.getProperty("filePath"));
        WebElement focus = common.findWebElement("xpath", "//RadioButton[@Name='I have a file']");
        if(focus.isSelected()==true){
            System.out.println("element clicked");
        }else{
            focus.click();
        }
        common.clickElement("xpath","//Button[@Name='Browse']");
        WebElement enter=common.findWebElement("xpath","//Edit[@Name='File name:']");
        enter.click();
        enter.sendKeys(common.getData(dataFile,"customerPath"));
        WebElement clickOpen=common.findWebElement("xpath","//ComboBox[@Name='Files of type:']/Button[@Name='Open']");
        clickOpen.sendKeys(Keys.TAB,Keys.ENTER);
        common.clickElement("xpath","//Button[@Name='Next >']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        Thread.sleep(1000);
        common.sliderHandling("xpath","//Pane/Table[@Name='ImportCustomers']/ScrollBar[@Name='Vertical']/*[@Name='Position']",0,-50);
        List<WebElement> fetchcustomer =common.findWebElements("xpath","//Table[@Name='ImportCustomers']/*[starts-with(@Name,'Row')]/*[contains(@Name,'Customer * Row')]");
        System.out.println("Size of fetching: " + fetchcustomer.size());
        for (int i = 0; i < fetchcustomer.size()-1; i++) {
            WebElement element1 = fetchcustomer.get(i);
            System.out.println("Element Text: " + element1.getText());
        }
        List<String> list1Texts = fetchcustomer.stream().map(WebElement::getText).filter(text -> !text.isEmpty()).map(String::trim) .collect(Collectors.toList());
        list1Texts.remove(list1Texts.size()-1);
        System.out.println("list1TextsData :"+list1Texts);
        common.clickElement("xpath","//Button[@Name='Next >']");
        WebElement dataValidate=common.findWebElement("xpath","//Window[@Name='Import Data']/*[@Name='write header text here']/*[@Name='Data Import Centre']/Text[1]");
        System.out.println("test :"+dataValidate.getText());
        if (dataValidate.getText().contains("Value not supplied to")) {
            System.out.println("You missed some data might be Name,Code,Node,Description");
        } else if (dataValidate.getText().contains("No errors found when importing data")) {
            System.out.println("you can move to Nest button");
        }
        common.clickElement("xpath","//Button[@Name='Next >']");
        WebElement validate = common.findWebElement("xpath", "//Window[@Name='Import Data']/*[@Name='write header text here']/*[@Name='Data Import Centre']/Text[1]");
        if(validate.getText().contains("Some errors are found")){
            Assert.fail("when You missed data might be Name,Code,Node,Description you will get this Errors"+" And We can't move to further also");
        } else if (validate.getText().equals("No errors found when importing data")) {
            System.out.println("no mistakes in the MasterData :" + new String(Character.toChars(0x1F602)));
            common.clickElement("xpath", "//Button[@Name='Next >']");
            WebElement validate1 = common.findWebElement("xpath", "//Window[@Name='Import Data']/*/*[@Name='Data Import Centre']/Text[1]");
            System.out.println("conformationText :" + validate1.getText());
            if (validate1.getText().equals("Saved Successfully")) {
                System.out.println("Master data imported successfully Great Job");
            } else if (!validate1.getText().equals("Saved Successfully")) {
                WebElement errorValidate = common.findWebElement("xpath", "//Window[@Name='Import Data']/*/*[@Name='Data Import Centre']/Text[1]");
                System.out.println("conformationText2: " + errorValidate.getText());
                if (errorValidate.getText().contains("Some errors are found")) {
                    System.out.println("You made a mistake in the Masters import, which might be due to identical names or existing names already being present");
                }
            }
        }
        common.clickElement("xpath","//Button[@Name='Next >']");
        common.clickElement("xpath","//Button[@Name='Finish']");
        common.clickElement("xpath", "//MenuItem[@Name='Sales']");
        common.clickElement("xpath", "//MenuItem[@Name='Customers']");
        common.clickElement("xpath", "//TreeItem[@Name='Customers']/TreeItem[@Name='All Customers']");
        List<WebElement> fetching = common.findWebElements("xpath", "//Pane/List/ListItem");
        System.out.println("Size of fetching: " + fetching.size());
        for (WebElement v : fetching) {
            masterDataValidate = v.getText();
            System.out.println("masterDataValidate :" + masterDataValidate);
        }
        List<String> list2Texts = fetching.stream().map(WebElement::getText).filter(text -> !text.isEmpty()).map(String::trim).collect(Collectors.toList());
        System.out.println("list2TextsData :"+list2Texts);
        // Compare the two lists
        for (String text1 : list1Texts) {
            if (list2Texts.contains(text1)) {
                System.out.println("Match found for: " + text1);
                System.out.println("Data Imported Successfully "+new String(Character.toChars(0x1F601)));

            } else {
                System.out.println("No match for: " + text1);
            }
        }

//        if (list2Texts.containsAll(list1Texts) &&  list1Texts.containsAll(list2Texts)){
//            System.out.println("Data Imported Successfully "+new String(Character.toChars(0x1F601)));
//        }
//        else {
//            System.out.println("The lists have different sizes.");
//            Assert.fail("The lists are not the same Names.");
//        }

    }

    public void branchesMastersImporting(String selectMaster) throws InterruptedException, IOException, ParseException {
        common.clickElement("xpath","//MenuItem[@Name='Tools']");
        common.clickElement("xpath","//MenuItem[@Name='Import Data']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        common.clickElement("xpath","//RadioButton[@Name='Masters']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        Thread.sleep(1500);
        WebElement fetchMaster=common.findWebElement("xpath","//Pane/ComboBox[@Name=concat('Specify data type and click on ', \"'\", 'Download File', \"'\", ' button to download template to prepare your data.')]/*[@Name=concat('Specify data type and click on ', \"'\", 'Download File', \"'\", ' button to download template to prepare your data.')]\n");
        fetchMaster.click();
        fetchMaster.sendKeys(selectMaster,Keys.DOWN,Keys.ENTER);
//        common.clickElement("xpath","//RadioButton[@Name=\"I don't have a file\"]");
//        common.clickElement("xpath","//Pane[@Name='Data Import Centre']/Pane[2]/Edit");
//        common.clickElement("xpath","//Window[@Name='Message']/Button[@Name='OK']");
//        System.out.println("file downloaded"+new String(Character.toChars(0x1F602)));
//        WebElement enterData=common.findWebElement("xpath","//Pane/Edit");
//        enterData.sendKeys(common.getProperty("filePath"));
        WebElement focus = common.findWebElement("xpath", "//RadioButton[@Name='I have a file']");
        if(focus.isSelected()==true){
            System.out.println("element clicked");
        }else{
            focus.click();
        }
        common.clickElement("xpath","//Button[@Name='Browse']");
        WebElement enter=common.findWebElement("xpath","//Edit[@Name='File name:']");
        enter.click();
        enter.sendKeys(common.getData(dataFile,"branchesPath"));
        WebElement clickOpen=common.findWebElement("xpath","//ComboBox[@Name='Files of type:']/Button[@Name='Open']");
        clickOpen.sendKeys(Keys.TAB,Keys.ENTER);
        common.clickElement("xpath","//Button[@Name='Next >']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        Thread.sleep(1000);
        common.sliderHandling("xpath","//Pane/Table[@Name='Branches']/ScrollBar[@Name='Vertical']/*[@Name='Position']",0,-50);
        List<WebElement> fetchcBranches =common.findWebElements("xpath","//Table[@Name='Branches']/*[starts-with(@Name,'Row')]/*[contains(@Name,'Master * Row')]");
        System.out.println("Size of fetching: " + fetchcBranches.size());
        for (int i = 0; i < fetchcBranches.size()-1; i++) {
            WebElement element1 = fetchcBranches.get(i);
            System.out.println("Element Text: " + element1.getText());
        }
        List<String> list1Texts = fetchcBranches.stream().map(WebElement::getText).filter(text -> !text.isEmpty()).map(String::trim) .collect(Collectors.toList());
        list1Texts.remove(list1Texts.size()-1);
        System.out.println("list1TextsData :"+list1Texts);
        common.clickElement("xpath","//Button[@Name='Next >']");
        WebElement dataValidate=common.findWebElement("xpath","//Window[@Name='Import Data']/*[@Name='write header text here']/*[@Name='Data Import Centre']/Text[1]");
        System.out.println("test :"+dataValidate.getText());
        if (dataValidate.getText().contains("Value not supplied to")) {
            System.out.println("You missed some data might be Name,Code,Node,Description");
        } else if (dataValidate.getText().contains("No errors found when importing data")) {
            System.out.println("you can move to Nest button");
        }
        common.clickElement("xpath","//Button[@Name='Next >']");
        WebElement validate = common.findWebElement("xpath", "//Window[@Name='Import Data']/*[@Name='write header text here']/*[@Name='Data Import Centre']/Text[1]");
        if(validate.getText().contains("Some errors are found")){
            Assert.fail("when You missed data might be Name,Code,Node,Description you will get this Errors"+" And We can't move to further also");
        } else if (validate.getText().equals("No errors found when importing data")) {
            System.out.println("no mistakes in the MasterData :" + new String(Character.toChars(0x1F602)));
            common.clickElement("xpath", "//Button[@Name='Next >']");
            WebElement validate1 = common.findWebElement("xpath", "//Window[@Name='Import Data']/*/*[@Name='Data Import Centre']/Text[1]");
            System.out.println("conformationText :" + validate1.getText());
            if (validate1.getText().equals("Saved Successfully")) {
                System.out.println("Master data imported successfully Great Job");
            } else if (!validate1.getText().equals("Saved Successfully")) {
                WebElement errorValidate = common.findWebElement("xpath", "//Window[@Name='Import Data']/*/*[@Name='Data Import Centre']/Text[1]");
                System.out.println("conformationText2: " + errorValidate.getText());
                if (errorValidate.getText().contains("Some errors are found")) {
                    System.out.println("You made a mistake in the Masters import, which might be due to identical names or existing names already being present");
                }
            }
        }
        common.clickElement("xpath","//Button[@Name='Next >']");
        common.clickElement("xpath","//Button[@Name='Finish']");
        common.clickElement("xpath", "//MenuItem[@Name='Company']");
        common.clickElement("xpath", "//MenuItem[@Name='Branches']");
        common.clickElement("xpath", "//TreeItem[@Name='Branches']/TreeItem[@Name='All Branches']");
        List<WebElement> fetching = common.findWebElements("xpath", "//Pane/List/ListItem");
        System.out.println("Size of fetching: " + fetching.size());
        for (WebElement v : fetching) {
            masterDataValidate = v.getText();
            System.out.println("masterDataValidate :" + masterDataValidate);
        }
        List<String> list2Texts = fetching.stream().map(WebElement::getText).filter(text -> !text.isEmpty()).map(String::trim).collect(Collectors.toList());
        System.out.println("list2TextsData :"+list2Texts);
        // Compare the two lists
        for (String text1 : list1Texts) {
            if (list2Texts.contains(text1)) {
                System.out.println("Match found for: " + text1);
                System.out.println("Data Imported Successfully "+new String(Character.toChars(0x1F601)));

            } else {
                System.out.println("No match for: " + text1);
            }
        }
    }

    public void hsnCodesMastersImporting(String selectMaster) throws InterruptedException, IOException, ParseException {
        common.clickElement("xpath","//MenuItem[@Name='Tools']");
        common.clickElement("xpath","//MenuItem[@Name='Import Data']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        common.clickElement("xpath","//RadioButton[@Name='Masters']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        Thread.sleep(1500);
        WebElement fetchMaster=common.findWebElement("xpath","//Pane/ComboBox[@Name=concat('Specify data type and click on ', \"'\", 'Download File', \"'\", ' button to download template to prepare your data.')]/*[@Name=concat('Specify data type and click on ', \"'\", 'Download File', \"'\", ' button to download template to prepare your data.')]\n");
        fetchMaster.click();
        fetchMaster.sendKeys(selectMaster);
//        common.clickElement("xpath","//RadioButton[@Name=\"I don't have a file\"]");
//        common.clickElement("xpath","//Pane[@Name='Data Import Centre']/Pane[2]/Edit");
//        common.clickElement("xpath","//Window[@Name='Message']/Button[@Name='OK']");
//        System.out.println("file downloaded"+new String(Character.toChars(0x1F602)));
        WebElement focus = common.findWebElement("xpath", "//RadioButton[@Name='I have a file']");
        if(focus.isSelected()==true){
            System.out.println("element clicked");
        }else{
            focus.click();
        }
        common.clickElement("xpath","//Button[@Name='Browse']");
        WebElement enter=common.findWebElement("xpath","//Edit[@Name='File name:']");
        enter.click();
        enter.sendKeys(common.getData(dataFile,"hsnCodePath"));
        WebElement clickOpen=common.findWebElement("xpath","//ComboBox[@Name='Files of type:']/Button[@Name='Open']");
        clickOpen.sendKeys(Keys.TAB,Keys.ENTER);
        common.clickElement("xpath","//Button[@Name='Next >']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        Thread.sleep(1000);
        common.sliderHandling("xpath","//Pane/Table[@Name='ImportHSNCodes']/ScrollBar[@Name='Vertical']/*[@Name='Position']",0,-50);
        List<WebElement> fetchcHsn =common.findWebElements("xpath","//Table[@Name='ImportHSNCodes']/*[starts-with(@Name,'Row')]/*[contains(@Name,'HSN Code * Row')]");
        System.out.println("Size of fetching: " + fetchcHsn.size());
        for (int i = 0; i < fetchcHsn.size()-1; i++) {
            WebElement element1 = fetchcHsn.get(i);
            System.out.println("Element Text: " + element1.getText());
        }
        List<String> list1Texts = fetchcHsn.stream().map(WebElement::getText).filter(text -> !text.isEmpty()).map(String::trim) .collect(Collectors.toList());
        list1Texts.remove(list1Texts.size()-1);
        System.out.println("list1TextsData :"+list1Texts);
        common.clickElement("xpath","//Button[@Name='Next >']");
        WebElement dataValidate=common.findWebElement("xpath","//Window[@Name='Import Data']/*[@Name='write header text here']/*[@Name='Data Import Centre']/Text[1]");
        System.out.println("test :"+dataValidate.getText());
        if (dataValidate.getText().contains("Value not supplied to")) {
            System.out.println("You missed some data might be Name,Code,Node,Description");
        } else if (dataValidate.getText().contains("No errors found when importing data")) {
            System.out.println("you can move to Nest button");
        }
        common.clickElement("xpath","//Button[@Name='Next >']");
        WebElement validate = common.findWebElement("xpath", "//Window[@Name='Import Data']/*[@Name='write header text here']/*[@Name='Data Import Centre']/Text[1]");
        if(validate.getText().contains("Some errors are found")){
            Assert.fail("when You missed data might be Name,Code,Node,Description you will get this Errors"+" And We can't move to further also");
        } else if (validate.getText().equals("No errors found when importing data")) {
            System.out.println("no mistakes in the MasterData :" + new String(Character.toChars(0x1F602)));
            common.clickElement("xpath", "//Button[@Name='Next >']");
            WebElement validate1 = common.findWebElement("xpath", "//Window[@Name='Import Data']/*/*[@Name='Data Import Centre']/Text[1]");
            System.out.println("conformationText :" + validate1.getText());
            if (validate1.getText().equals("Saved Successfully")) {
                System.out.println("Master data imported successfully Great Job");
            } else if (!validate1.getText().equals("Saved Successfully")) {
                WebElement errorValidate = common.findWebElement("xpath", "//Window[@Name='Import Data']/*/*[@Name='Data Import Centre']/Text[1]");
                System.out.println("conformationText2: " + errorValidate.getText());
                if (errorValidate.getText().contains("Some errors are found")) {
                    System.out.println("You made a mistake in the Masters import, which might be due to identical names or existing names already being present");
                }
            }
        }
        common.clickElement("xpath","//Button[@Name='Next >']");
//        WebElement confirmSave = common.findWebElement("xpath", "//Window[@Name='Import Data']/*/*[@Name='Data Import Centre']");
//        System.out.println("conformationText :" + confirmSave.getText());
        common.clickElement("xpath","//Button[@Name='Finish']");
        common.clickElement("xpath", "//MenuItem[@Name='Taxes']");
        common.clickElement("xpath", "//MenuItem[@Name='GST']");
        common.clickElement("xpath", "//MenuItem[@Name='HSN Codes']");
        common.clickElement("xpath", "//TreeItem[@Name='HSN Codes']/TreeItem[@Name='All HSN Codes']");
        List<WebElement> fetching = common.findWebElements("xpath", "//Pane/List/ListItem");
        System.out.println("Size of fetching: " + fetching.size());
        for (WebElement v : fetching) {
            masterDataValidate = v.getText();
            System.out.println("masterDataValidate :" + masterDataValidate);
        }
        List<String> list2Texts = fetching.stream().map(WebElement::getText).filter(text -> !text.isEmpty()).map(String::trim).collect(Collectors.toList());
        System.out.println("list2TextsData :"+list2Texts);
        // Compare the two lists
        for (String text1 : list1Texts) {
            if (list2Texts.contains(text1)) {
                System.out.println("Match found for: " + text1);
                System.out.println("Data Imported Successfully "+new String(Character.toChars((0x1F355))));

            } else {
                System.out.println("No match for: " + text1);
            }
        }
    }

    public void balanceSheetBankMastersImports(String selectMaster) throws IOException, ParseException, InterruptedException {
        common.clickElement("xpath","//MenuItem[@Name='Tools']");
        common.clickElement("xpath","//MenuItem[@Name='Import Data']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        common.clickElement("xpath","//RadioButton[@Name='Masters']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        Thread.sleep(1500);
        WebElement fetchMaster=common.findWebElement("xpath","//Pane/ComboBox[@Name=concat('Specify data type and click on ', \"'\", 'Download File', \"'\", ' button to download template to prepare your data.')]/*[@Name=concat('Specify data type and click on ', \"'\", 'Download File', \"'\", ' button to download template to prepare your data.')]\n");
        fetchMaster.click();
        fetchMaster.sendKeys(selectMaster,Keys.DOWN,Keys.ENTER);
//        common.clickElement("xpath","//RadioButton[@Name=\"I don't have a file\"]");
//        common.clickElement("xpath","//Pane[@Name='Data Import Centre']/Pane[2]/Edit");
//        common.clickElement("xpath","//Window[@Name='Message']/Button[@Name='OK']");
//        System.out.println("file downloaded"+new String(Character.toChars(0x1F602)));
        WebElement focus = common.findWebElement("xpath", "//RadioButton[@Name='I have a file']");
        if(focus.isSelected()==true){
            System.out.println("element clicked");
        }else{
            focus.click();
        }
        common.clickElement("xpath","//Button[@Name='Browse']");
        WebElement enter=common.findWebElement("xpath","//Edit[@Name='File name:']");
        enter.click();
        enter.sendKeys(common.getData(dataFile,"balanceSheetBankAccount"));
        WebElement clickOpen=common.findWebElement("xpath","//ComboBox[@Name='Files of type:']/Button[@Name='Open']");
        clickOpen.sendKeys(Keys.TAB,Keys.ENTER);
        common.clickElement("xpath","//Button[@Name='Next >']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        Thread.sleep(1000);
        common.sliderHandling("xpath","//Pane/Table[@Name='ImportBalanceSheetAccounts']/ScrollBar[@Name='Vertical']/*[@Name='Position']",0,-50);
        List<WebElement> fetchcBankAct =common.findWebElements("xpath","//Table[@Name='ImportBalanceSheetAccounts']/*[starts-with(@Name,'Row')]/*[contains(@Name,'New Account * Row')]");
        System.out.println("Size of fetching: " + fetchcBankAct.size());
        for (int i = 0; i < fetchcBankAct.size()-1; i++) {
            WebElement element1 = fetchcBankAct.get(i);
            System.out.println("Element Text: " + element1.getText());
        }
        List<String> list1Texts = fetchcBankAct.stream().map(WebElement::getText).filter(text -> !text.isEmpty()).map(String::trim) .collect(Collectors.toList());
        list1Texts.remove(list1Texts.size()-1);
        System.out.println("list1TextsData :"+list1Texts);
        common.clickElement("xpath","//Button[@Name='Next >']");
        WebElement dataValidate=common.findWebElement("xpath","//Window[@Name='Import Data']/*[@Name='write header text here']/*[@Name='Data Import Centre']/Text[1]");
        System.out.println("test :"+dataValidate.getText());
        if (dataValidate.getText().contains("Value not supplied to")) {
            System.out.println("You missed some data might be Name,Code,Node,Description");
            System.out.println("You Can move to NEXT"+"But something missed");
        } else if (dataValidate.getText().contains("No errors found when importing data")) {
            System.out.println("you entered Currect Data can move to Nest Step");
        }
        common.clickElement("xpath","//Button[@Name='Next >']");
        WebElement validate = common.findWebElement("xpath", "//Window[@Name='Import Data']/*[@Name='write header text here']/*[@Name='Data Import Centre']/Text[1]");
        if(validate.getText().contains("Some errors are found")){
            Assert.fail("when You missed data might be Name,Code,Node,Description you will get this Errors"+" And We can't move to further also");
        } else if (validate.getText().equals("No errors found when importing data")) {
            System.out.println("no mistakes in the MasterData :" + new String(Character.toChars(0x1F602)));
            common.clickElement("xpath", "//Button[@Name='Next >']");
            WebElement validate1 = common.findWebElement("xpath", "//Window[@Name='Import Data']/*/*[@Name='Data Import Centre']/Text[1]");
            System.out.println("conformationText :" + validate1.getText());
            if (validate1.getText().equals("Saved Successfully")) {
                System.out.println("Master data imported successfully Great Job");
            } else if (!validate1.getText().equals("Saved Successfully")) {
                WebElement errorValidate = common.findWebElement("xpath", "//Window[@Name='Import Data']/*/*[@Name='Data Import Centre']/Text[1]");
                System.out.println("conformationText2: " + errorValidate.getText());
                if (errorValidate.getText().contains("Some errors are found")) {
                    System.out.println("You made a mistake in the Masters import, which might be due to identical names or existing names already being present");
                }
            }
        }
        common.clickElement("xpath","//Button[@Name='Next >']");
//        WebElement confirmSave = common.findWebElement("xpath", "//Window[@Name='Import Data']/*/*[@Name='Data Import Centre']");
//        System.out.println("conformationText :" + confirmSave.getText());
        common.clickElement("xpath","//Button[@Name='Finish']");
        System.out.println("importing Done");
        common.clickElement("xpath", "//MenuItem[@Name='Finance']");
        common.clickElement("xpath", "//MenuItem[@Name='Chart of Accounts']");
        WebElement navigateToBank=common.findWebElement("xpath", "//TreeItem[@Name='Balance Sheet']/TreeItem[@Name='Assets']");
        navigateToBank.sendKeys(Keys.ARROW_RIGHT,Keys.ARROW_RIGHT,Keys.ARROW_RIGHT);
        common.clickElement("xpath","//TreeItem[@Name='Current Assets, Loans and Advances']/TreeItem[@Name='Bank']");
        List<WebElement> fetching = common.findWebElements("xpath", "//Pane/List/ListItem");
        System.out.println("Size of fetching: " + fetching.size());
        for (WebElement v : fetching) {
            masterDataValidate = v.getText();
            System.out.println("masterDataValidate :" + masterDataValidate);
        }
        List<String> list2Texts = fetching.stream().map(WebElement::getText).filter(text -> !text.isEmpty()).map(String::trim).collect(Collectors.toList());
        System.out.println("list2TextsData :"+list2Texts);
        // Compare the two lists
        for (String text1 : list1Texts) {
            if (list2Texts.contains(text1)) {
                System.out.println("Match found for: " + text1);
                System.out.println("Data Imported Successfully "+new String(Character.toChars((0x1F355))));

            } else {
                System.out.println("No match for: " + text1);
            }
        }
    }
    public void balanceSheetCashMastersImports(String selectMaster) throws IOException, ParseException, InterruptedException {
        common.clickElement("xpath","//MenuItem[@Name='Tools']");
        common.clickElement("xpath","//MenuItem[@Name='Import Data']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        common.clickElement("xpath","//RadioButton[@Name='Masters']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        Thread.sleep(1500);
        WebElement fetchMaster=common.findWebElement("xpath","//Pane/ComboBox[@Name=concat('Specify data type and click on ', \"'\", 'Download File', \"'\", ' button to download template to prepare your data.')]/*[@Name=concat('Specify data type and click on ', \"'\", 'Download File', \"'\", ' button to download template to prepare your data.')]\n");
        fetchMaster.click();
        fetchMaster.sendKeys(selectMaster,Keys.DOWN,Keys.ENTER);
        WebElement focus = common.findWebElement("xpath", "//RadioButton[@Name='I have a file']");
        if(focus.isSelected()==true){
            System.out.println("element clicked");
        }else{
            focus.click();
        }
        common.clickElement("xpath","//Button[@Name='Browse']");
        WebElement enter=common.findWebElement("xpath","//Edit[@Name='File name:']");
        enter.click();
        enter.sendKeys(common.getData(dataFile,"balanceSheetCashAccount"));
        WebElement clickOpen=common.findWebElement("xpath","//ComboBox[@Name='Files of type:']/Button[@Name='Open']");
        clickOpen.sendKeys(Keys.TAB,Keys.ENTER);
        common.clickElement("xpath","//Button[@Name='Next >']");
        common.clickElement("xpath","//Button[@Name='Next >']");
        Thread.sleep(1000);
        common.sliderHandling("xpath","//Pane/Table[@Name='ImportBalanceSheetAccounts']/ScrollBar[@Name='Vertical']/*[@Name='Position']",0,-50);
        List<WebElement> fetchCashAct =common.findWebElements("xpath","//Table[@Name='ImportBalanceSheetAccounts']/*[starts-with(@Name,'Row')]/*[contains(@Name,'New Account * Row')]");
        System.out.println("Size of fetching: " + fetchCashAct.size());
        for (int i = 0; i < fetchCashAct.size()-1; i++) {
            WebElement element1 = fetchCashAct.get(i);
            System.out.println("Element Text: " + element1.getText());
        }
        List<String> list1Texts = fetchCashAct.stream().map(WebElement::getText).filter(text -> !text.isEmpty()).map(String::trim) .collect(Collectors.toList());
        list1Texts.remove(list1Texts.size()-1);
        System.out.println("list1TextsData :"+list1Texts);
        common.clickElement("xpath","//Button[@Name='Next >']");
        WebElement dataValidate=common.findWebElement("xpath","//Window[@Name='Import Data']/*[@Name='write header text here']/*[@Name='Data Import Centre']/Text[1]");
        System.out.println("test :"+dataValidate.getText());
        if (dataValidate.getText().contains("Value not supplied to")) {
            System.out.println("You missed some data might be Name,Code,Node,Description");
            System.out.println("You Can move to NEXT"+"But something missed");
        } else if (dataValidate.getText().contains("No errors found when importing data")) {
            System.out.println("you entered Currect Data can move to Nest Step");
        }
        common.clickElement("xpath","//Button[@Name='Next >']");
        WebElement validate = common.findWebElement("xpath", "//Window[@Name='Import Data']/*[@Name='write header text here']/*[@Name='Data Import Centre']/Text[1]");
        if(validate.getText().contains("Some errors are found")){
            Assert.fail("when You missed data might be Name,Code,Node,Description you will get this Errors"+" And We can't move to further also");
        } else if (validate.getText().equals("No errors found when importing data")) {
            System.out.println("no mistakes in the MasterData :" + new String(Character.toChars(0x1F602)));
            common.clickElement("xpath", "//Button[@Name='Next >']");
            WebElement validate1 = common.findWebElement("xpath", "//Window[@Name='Import Data']/*/*[@Name='Data Import Centre']/Text[1]");
            System.out.println("conformationText :" + validate1.getText());
            if (validate1.getText().equals("Saved Successfully")) {
                System.out.println("Master data imported successfully Great Job");
            } else if (!validate1.getText().equals("Saved Successfully")) {
                WebElement errorValidate = common.findWebElement("xpath", "//Window[@Name='Import Data']/*/*[@Name='Data Import Centre']/Text[1]");
                System.out.println("conformationText2: " + errorValidate.getText());
                if (errorValidate.getText().contains("Some errors are found")) {
                    System.out.println("You made a mistake in the Masters import, which might be due to identical names or existing names already being present");
                }
            }
        }
        common.clickElement("xpath","//Button[@Name='Next >']");
        common.clickElement("xpath","//Button[@Name='Finish']");
        System.out.println("importing Done");
        common.clickElement("xpath", "//MenuItem[@Name='Finance']");
        common.clickElement("xpath", "//MenuItem[@Name='Chart of Accounts']");
        WebElement navigateToBank=common.findWebElement("xpath", "//TreeItem[@Name='Balance Sheet']/TreeItem[@Name='Assets']");
        navigateToBank.sendKeys(Keys.ARROW_RIGHT,Keys.ARROW_RIGHT,Keys.ARROW_RIGHT);
        common.clickElement("xpath","//TreeItem[@Name='Current Assets, Loans and Advances']/TreeItem[@Name='Cash']");
        List<WebElement> fetching = common.findWebElements("xpath", "//Pane/List/ListItem");
        System.out.println("Size of fetching: " + fetching.size());
        for (WebElement v : fetching) {
            masterDataValidate = v.getText();
            System.out.println("masterDataValidate :" + masterDataValidate);
        }
        List<String> list2Texts = fetching.stream().map(WebElement::getText).filter(text -> !text.isEmpty()).map(String::trim).collect(Collectors.toList());
        System.out.println("list2TextsData :"+list2Texts);
        // Compare the two lists
        for (String text1 : list1Texts) {
            if (list2Texts.contains(text1)) {
                System.out.println("Match found for: " + text1);
                System.out.println("Data Imported Successfully "+new String(Character.toChars((0x1F355))));

            } else {
                System.out.println("No match for: " + text1);
            }
        }
    }
}




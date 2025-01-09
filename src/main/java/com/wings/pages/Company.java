package com.wings.pages;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class Company {
    WindowsDriver driver;
    Common common;
    String fileData = "./src/main/resources/company_Name.json";

    public Company(WindowsDriver driver) {
        this.driver = driver;
        common = new Common(this.driver);
    }

    public void companyCreation() throws InterruptedException, IOException, ParseException {
        setCompanyName();
        setPassword();
        dbCreation();
    }

    public void companyLogin() throws InterruptedException, IOException, ParseException {
        verifyLogin();
    }

    public void setCompanyName() throws IOException, ParseException {
        common.clickElement("xpath", "//Pane[@Name='Manage companies']/Text[@Name='Create a new company']/*[@Name='Create a new company']");
        common.clickElement("xpath", "//Button[@Name='Create']");
        common.clickElement("xpath", "//Button[@Name='Next']");

        common.inputText("xpath", "//Edit[@Name='Login Name']", common.getData(fileData, "companyName"));
        common.inputText("xpath", "//Edit[@Name='Company Name']", common.getData(fileData, "companyName"));
    }

    public void setPassword() throws InterruptedException, IOException, ParseException {
        Thread.sleep(2000);
        common.inputText("xpath", "//Edit[@Name='Password']", common.getData(fileData, "password"));
        common.inputText("xpath", "//Edit[@Name='Confirm Password']", common.getData(fileData, "password"));
        common.clickElement("name", "Advanced Options");
        common.clickElement("xpath", "//Button[@Name='Next']");
        common.clickElement("name", "OK");
    }

    public void dbCreation() throws InterruptedException, IOException, ParseException {
        common.inputText("xpath", "//Edit[@Name='Database ServerRequestFunctions']", "Madhuri");
        common.clickElement("xpath", "//Button[@Name='Next']");
        for (int i = 0; i < 80; i++) {
            WebElement next = common.findWebElement("xpath", "//Button[@Name='Next']");
            Thread.sleep(3000);
            if (next.isEnabled()) {
                common.clickElement("xpath", "//Button[@Name='Next']");
                break;
            }
        }
        System.out.println("DB creation successful for " + common.getData(fileData, "companyName") + " Company");
        common.clickElement("xpath", "//Button[@Name='Next']");
        common.clickElement("xpath", "//Button[@Name='Finish']");
        common.clickElement("xpath", "//Button[@Name='OK']");
        System.out.println("Company creation with name " + common.getData(fileData, "companyName") + " is successful ");
    }

    public void verifyLogin() throws InterruptedException, IOException, ParseException {
        Thread.sleep(2000);
        common.clickElement("xpath", "//Pane[@Name='Choose a recent company']/Text[@Name='> " + common.getData(fileData, "companyName") + "']/*[@Name='> " + common.getData(fileData, "companyName") + "']");

        common.inputText("xpath", "//Edit[@Name='Password']", common.getData(fileData, "password"));
        common.clickElement("xpath", "//Button[@Name='Submit']");
        Thread.sleep(1200);
        common.clickElement("name", "OK");
        System.out.println("Super User Login for " + common.getData(fileData, "companyName") + " company is successful " + new String(Character.toChars(0x2705)));
    }


}

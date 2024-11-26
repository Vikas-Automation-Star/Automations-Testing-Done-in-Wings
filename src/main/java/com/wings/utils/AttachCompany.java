package com.wings.utils;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class AttachCompany {

        WindowsDriver driver;

        public void attachCompany() throws IOException, InterruptedException, AWTException {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", "C:\\Program Files (x86)\\Wings Infonet\\Wings Accounting 24DNP\\Wings.exe");
            capabilities.setCapability("platformName", "Windows");
            capabilities.setCapability("deviceName", "WindowsPC");
            driver = new WindowsDriver<>(new URL("http://127.0.0.1:4723/"), capabilities);
            driver.manage().window().maximize();
            Thread.sleep(2000);
            driver.findElementByXPath("//Pane[@Name='Manage companies']/Text[@Name='Attach a company']/*[@Name='Attach a company']").click();
            Thread.sleep(3000);
            driver.findElementByXPath("//Button[@Name='Next >']").click();
            Thread.sleep(3000);
            driver.findElementByXPath("//Edit[@Name='Company Name']").sendKeys("Dup_Company");
            driver.findElementByXPath("//Button[@Name=' ....']").click();
//            driver.findElementByXPath("//ListItem[@Name='Dup_Company.mdf']").click();
            Thread.sleep(3000);
            driver.getKeyboard().sendKeys("Dup_Company.mdf");
            driver.getKeyboard().sendKeys(Keys.ENTER);
//            driver.findElementByXPath("//Dialog[@Name='Open']/Button[@Name='Open']").sendKeys(Keys.ENTER);
//            driver.findElementByXPath("//Edit[@Name='Database File Path']").sendKeys("C:\\Program Files\\Microsoft SQL Server\\MSSQL16.MSSQLSERVER\\MSSQL\\DATA\\Dup_Company.mdf");
            driver.findElementByXPath("//Button[@Name='Next >']").click();
            driver.findElementByXPath("//Button[@Name='Finish']").click();
        }

        public static void main (String[]args) throws IOException, InterruptedException, AWTException {
            AttachCompany attachCompany=new AttachCompany();
            attachCompany.attachCompany();
        }
    }


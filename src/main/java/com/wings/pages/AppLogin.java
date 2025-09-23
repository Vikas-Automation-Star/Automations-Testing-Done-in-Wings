package com.wings.pages;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

public class AppLogin {
    WindowsDriver driver, loginDriver, rootDriver;
    Common common = new Common(driver);
    String fileData = "./src/main/resources/company_Name.json";

    public WindowsDriver login() throws IOException, InterruptedException, ParseException {

        driver = common.initializeDriver(common.getProperty("multiUserApp"));
//    String currentwindowHandle = driver.getWindowHandle();
//    System.out.println("Window 1 -" + currentwindowHandle);
        driver.findElement(By.name("24D Books Automation")).click();
        Thread.sleep(5000);
        rootDriver = common.initializeDriver("Root");
        Thread.sleep(10000);
        WebElement login = rootDriver.findElement(By.name("Wings - Web Client"));
        String nativeWindow = login.getAttribute("NativeWindowHandle");
        String hexLoginId = Integer.toHexString(Integer.parseInt(nativeWindow));
        System.out.println("window id: " + hexLoginId);
        loginDriver = common.navigateToAppWindow(hexLoginId);
        common = new Common(loginDriver);

        common.inputText("xpath", "//Edit[@Name='Password']", common.getProperty("password"));
        System.out.println("Password TagName " + common.getTagName("name", "Password"));
        common.clickElement("name", "Submit");
        Thread.sleep(10000);
        common.clickElement("xpath","//Button[@Name='OK']");
        driver.quit();
        rootDriver.quit();
        return loginDriver;
    }


    public void singleUserLogin() throws InterruptedException, IOException, ParseException {
        Thread.sleep(2000);
        common.clickElement("xpath", "//Pane[@Name='Choose a recent company']/Text[@Name='> " + common.getData(fileData, "companyName") + "']/*[@Name='> " + common.getData(fileData, "companyName") + "']");
        Thread.sleep(3000);
        common.inputText("xpath", "//Edit[@Name='Password']", common.getData(fileData, "password"));
        common.clickElement("xpath", "//Button[@Name='Submit']");
        //only sometimes
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            WebElement next=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//Window[@Name='Company Initialization']/Pane[@Name='header text']/Button[@Name='Next >']")));
            next.click();
            WebElement finish = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//Window[@Name='Company Initialization']/Pane[@Name='header text']/Button[@Name='Finish']")));
            finish.click();
        }catch (Exception e){
            System.out.println("No next/finish is found");
        }
        //mandatory
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//Window[@Name='Information']/Button[@Name='OK']"))).click();
        System.out.println("Super User Login for " + common.getData(fileData, "companyName") + " company is successful " + new String(Character.toChars(0x2705)));
        String title = driver.getTitle();
        System.out.println(title);
//        Assert.assertEquals("Wings Finance - PRO [ Wings InfoNet ; 01-04-2024 To 31-03-2025 ; Super User ]",title);
    }

    public void singleUserLogin(String userName,String password) throws InterruptedException, IOException, ParseException {
        Thread.sleep(2000);
        common.clickElement("xpath", "//Pane[@Name='Choose a recent company']/Text[@Name='> " + common.getData(fileData, "companyName") + "']/*[@Name='> " + common.getData(fileData, "companyName") + "']");
        Thread.sleep(3000);
        common.inputText("xpath","//Edit[@Name='User name']",userName);
        common.inputText("xpath", "//Edit[@Name='Password']", password);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        //only sometimes
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            WebElement next=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//Window[@Name='Company Initialization']/Pane[@Name='header text']/Button[@Name='Next >']")));
            next.click();
            WebElement finish = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//Window[@Name='Company Initialization']/Pane[@Name='header text']/Button[@Name='Finish']")));
            finish.click();
        }catch (Exception e){
            System.out.println("No next/finish is found");
        }
        //mandatory
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//Window[@Name='Information']/Button[@Name='OK']"))).click();
        System.out.println("Super User Login for " + common.getData(fileData, "companyName") + " company is successful " + new String(Character.toChars(0x2705)));
        String title = driver.getTitle();
        System.out.println(title);
//        Assert.assertEquals("Wings Finance - PRO [ Wings InfoNet ; 01-04-2024 To 31-03-2025 ; Super User ]",title);
    }

    public WindowsDriver launchSingleUserApp() throws IOException, InterruptedException {
        driver = common.initializeDriver(common.getProperty("singleUserApp"));
        driver.manage().window().maximize();
        Thread.sleep(2000);
        common.findWebElement("name", "Wings 24");
        return driver;
    }

    public void logout() throws IOException {
        try {
            Thread.sleep(1500);
            common.clickElement("xpath", "//MenuItem[@Name='File']");
            Thread.sleep(1500);
            common.clickElement("name", "Exit");
            common.clickElement("name", "Yes");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
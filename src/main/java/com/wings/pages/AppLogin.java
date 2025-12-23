package com.wings.pages;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import mobileTesing.BaseHelper;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AppLogin {
    WebDriver mobileDriver;
    BaseHelper helper;
    WindowsDriver driver, loginDriver, rootDriver;
    Common common = new Common(driver);
    String fileData = "./src/main/resources/company_Name.json";

    public WindowsDriver login() throws IOException, InterruptedException, ParseException {
        Desktop desktop = Desktop.getDesktop();
        desktop.open (new File("C:\\Program Files (x86)\\Windows Application Driver\\WinAppDriver.exe"));
        Thread.sleep(5000);
        driver = common.initializeDriver(common.getProperty("multiUserApp"));
        driver.findElement(By.name(" 24D Books Automation")).click();
        Thread.sleep(5000);
        rootDriver = common.initializeDriver("Root");
        Thread.sleep(10000);
        WebElement login = rootDriver.findElement(By.name("Wings - Web Client"));
        String nativeWindow = login.getAttribute("NativeWindowHandle");
        String hexLoginId = Integer.toHexString(Integer.parseInt(nativeWindow));
        System.out.println("window id: " + hexLoginId);
        loginDriver = common.navigateToAppWindow(hexLoginId);
        common = new Common(loginDriver);
        common.inputText("xpath","//Edit[@Name='User name']",common.getProperty("userName"));
        common.inputText("xpath", "//Edit[@Name='Password']", common.getProperty("password"));
        common.clickElement("name", "Submit");
        Thread.sleep(10000);
        common.clickElement("xpath","//Button[@Name='OK']");
        driver.quit();
        rootDriver.quit();
        return loginDriver;
    }

    public WebDriver mobileLogin() throws InterruptedException, IOException {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Pixel 7");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        options.addArguments("--incognito");
        options.addArguments("--window-size=412,915");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dell\\Downloads\\chromedriver.exe");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("safebrowsing.enabled", false);
        prefs.put("download.prompt_for_download", false);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("autofill.profile_enabled", false);
        prefs.put("autofill.credit_card_enabled", false);
        options.addArguments("--disable-blink-features=AutomationControlled");
        prefs.put("profile.default_content_setting_values.geolocation", 2);
        options.setExperimentalOption("prefs", prefs);
        // Choose the specific profile folder
        // options.addArguments("user-data-dir=C:\\Users\\Dell\\AppData\\Local\\Google\\Chrome\\User Data");/
        //options.addArguments("profile-directory=Profile 2");
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--allow-insecure-localhost");
        options.addArguments("--ignore-certificate-errors");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        mobileDriver = new ChromeDriver(options);
        helper =new BaseHelper(mobileDriver);
        mobileDriver.manage().window().maximize();
        mobileDriver.navigate().to("http://localhost:4200");
        Thread.sleep(1000);

        helper.findWebElementInMobile("id","ion-input-3").sendKeys(helper.getProperty("serverURL"));
        helper.findWebElementInMobile("id","ion-input-4").sendKeys(helper.getProperty("dbName"));
        // Thread.sleep(1000);
        helper.findWebElementInMobile("xpath","(//ion-button[normalize-space()='Add Server'])[1]").click();
        Thread.sleep(1000);
        helper.findWebElementInMobile("id","ion-input-1").sendKeys(helper.getProperty("mobileUserName"));
        helper.findWebElementInMobile("id","ion-input-2").sendKeys(helper.getProperty("password"));
        helper.findWebElementInMobile("cssSelector",".ion-color.ion-color-wings.ios.button.button-block.button-solid.ion-activatable.ion-focusable").click();
        Thread.sleep(3000);
        return mobileDriver;
    }

    public void signOut() throws InterruptedException {
        Thread.sleep(1500);
        mobileDriver.findElement(By.xpath("(//ion-label[normalize-space()='Home'])[1]")).click();
        Thread.sleep(1000);
        mobileDriver.findElement(By.xpath("//*[text()='Super User']")).click();
        Thread.sleep(2000);
        mobileDriver.findElement(By.xpath("//*[text()='Sign Out']")).click();
        mobileDriver.close();
        mobileDriver.quit();
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
            Thread.sleep(2000);
            common.clickElement("xpath", "//MenuItem[@Name='File']");
            Thread.sleep(1500);
            common.clickElement("name", "Exit");
            common.clickElement("name", "Yes");
            Runtime.getRuntime().exec("taskkill /F /IM WinAppDriver.exe");
            System.out.println("✅ WinAppDriver stopped");
            Thread.sleep(10000);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
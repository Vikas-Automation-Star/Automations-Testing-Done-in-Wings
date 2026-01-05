package util;

import com.wings.utils.Common;
import com.wings.utils.Time;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class mobileTesting {
    private WebDriver driver;

    @BeforeTest
    public void beforeTest() throws InterruptedException {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Pixel 7");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        options.addArguments("--window-size=412,915");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-blink-features=AutomationControlled");

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dell\\Downloads\\chromedriver-win64\\chromedriver.exe");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("safebrowsing.enabled", false);
        prefs.put("download.prompt_for_download", false);
        options.setExperimentalOption("prefs", prefs);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("user-data-dir=C:\\ChromeTempProfile");
        options.setAcceptInsecureCerts(true);
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--allow-insecure-localhost");
        options.addArguments("--disable-web-security");

        driver = new ChromeDriver(options);
    }

//    @Test
    public void masterCreation() throws InterruptedException {
        driver.manage().window().maximize();
        driver.navigate().to("http://localhost:4200/login");
        Thread.sleep(2000);
        driver.findElement(By.id("ion-input-3")).sendKeys("https://10.10.10.90:8083");
        driver.findElement(By.id("ion-input-4")).sendKeys("24D Books Automation");
        driver.findElement(By.xpath("//ion-button[normalize-space()='Add Server'][1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@placeholder='Enter user name']")).sendKeys("Super User");
        driver.findElement(By.xpath("//input[@placeholder='Enter password']")).sendKeys("Wings@123");
        driver.findElement(By.cssSelector(".ion-color.ion-color-wings.ios.button.button-block.button-solid.ion-activatable.ion-focusable")).click();
        Thread.sleep(3000);

        //go to explorer
        driver.findElement(By.xpath("//ion-tab-button[@id='tab-button-app-navigator']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//ion-label[normalize-space()='Customers'])[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//h3[normalize-space()=\"AT_Cus_Comp_Inter\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//button[.//span[normalize-space()='Show Properties']]")).click();
        Thread.sleep(1500);
        //back
        driver.findElement(By.xpath("(//ion-back-button[contains(@class,'ion-activatable')])[2]")).click();
        //new
        Thread.sleep(1500);
        driver.findElement(By.xpath("(//ion-button[@class='ios button button-clear in-toolbar in-buttons ion-activatable ion-focusable'])[1]")).click();
        Thread.sleep(1500);
        //enter new customer details
        driver.findElement(By.xpath("//input[@placeholder='Enter New Account']")).sendKeys("Sample Customers"+Common.getRandomChar());
        driver.findElement(By.xpath("//input[@placeholder='Enter Account Code']")).sendKeys("SaCu"+ Common.getRandomChar());
        driver.findElement(By.xpath("//input[@placeholder='Enter Node']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//ion-label[normalize-space(.)='All Customers']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//textarea[contains(@placeholder,'Enter Description')]")).sendKeys("This is for Testing");
        Thread.sleep(1000);
        //save
        driver.findElement(By.xpath("//ion-button[normalize-space(.)='Save Customers']")).click();
    }

//    @Test
    public void navigateThroughElements() throws InterruptedException {
        driver.manage().window().maximize();
        driver.navigate().to("http://localhost:4200/login");
        Thread.sleep(2000);
        driver.findElement(By.id("ion-input-3")).sendKeys("https://10.10.10.90:8083");
        driver.findElement(By.id("ion-input-4")).sendKeys("24D Books Automation");
        driver.findElement(By.xpath("//ion-button[normalize-space()='Add Server'][1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@placeholder='Enter user name']")).sendKeys("Super User");
        driver.findElement(By.xpath("//input[@placeholder='Enter password']")).sendKeys("Wings@123");
        driver.findElement(By.cssSelector(".ion-color.ion-color-wings.ios.button.button-block.button-solid.ion-activatable.ion-focusable")).click();
        Thread.sleep(3000);

        //navigate
        //key metrics
        driver.findElement(By.xpath("//ion-tab-button[@id='tab-button-key-metrics']")).click();
        Thread.sleep(1000);
        //go to explorer
        driver.findElement(By.xpath("//ion-tab-button[@id='tab-button-app-navigator']")).click();
        Thread.sleep(1000);
        //favourites
        driver.findElement(By.xpath("//ion-tab-button[@id='tab-button-favorites']")).click();
        Thread.sleep(1000);
        //my task
        driver.findElement(By.xpath("//ion-tab-button[@id='tab-button-my-tasks']")).click();
        Thread.sleep(1000);
        //home
        driver.findElement(By.xpath("//ion-tab-button[@id='tab-button-home']")).click();
        Thread.sleep(1000);

//        driver.findElement(By.xpath("//ion-label[normalize-space(.)='Cash Receipts'][1]")).click();
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("(//ion-button[normalize-space(.)='Edit'])[1]")).click();
//        Thread.sleep(2000);
        //date
//        driver.findElement(By.xpath("//input[@id='ion-input-0']")).sendKeys(Time.timeStamp(), Keys.TAB);
        //branch
//        driver.findElement(By.xpath("//input[@placeholder='Select Branch']")).click();
    }

    @Test
    public void renameMaster() throws InterruptedException {
        driver.manage().window().maximize();
        driver.navigate().to("http://localhost:4200/login");
        Thread.sleep(2000);
        driver.findElement(By.id("ion-input-3")).sendKeys("https://10.10.10.90:8083");
        driver.findElement(By.id("ion-input-4")).sendKeys("24D Books Automation");
        driver.findElement(By.xpath("//ion-button[normalize-space()='Add Server'][1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@placeholder='Enter user name']")).sendKeys("Super User");
        driver.findElement(By.xpath("//input[@placeholder='Enter password']")).sendKeys("Wings@123");
        driver.findElement(By.cssSelector(".ion-color.ion-color-wings.ios.button.button-block.button-solid.ion-activatable.ion-focusable")).click();
        Thread.sleep(10000);

        //go to explorer
        driver.findElement(By.xpath("//ion-tab-button[@id='tab-button-app-navigator']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//ion-label[normalize-space()='Customers'])[3]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//h3[normalize-space()=\"Sample Customers\"]")).click();
        Thread.sleep(1000);

    }
    @AfterTest
    public void afterTest(){
//        //sign out
//        driver.findElement(By.xpath("//div[@class='company']")).click();
//        driver.findElement(By.xpath("//ion-label[normalize-space(.)='Sign Out']")).click();
    }

}

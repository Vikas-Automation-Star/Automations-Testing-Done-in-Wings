package util;

import com.wings.pages.AppLogin;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MobileTesting {

    WebDriver driver;
    AppLogin appLogin=new AppLogin();

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException {
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Pixel 7");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
//        options.addArguments("--incognito");
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
//        options.addArguments("user-data-dir=C:\\Users\\Dell\\AppData\\Local\\Google\\Chrome\\User Data");
//        options.addArguments("profile-directory=Profile 2");
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--allow-insecure-localhost");
        options.addArguments("--ignore-certificate-errors");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to(" http://localhost:4200");
        Thread.sleep(1000);
        driver.findElement(By.id("ion-input-3")).sendKeys("https://10.10.10.90:8083");
        driver.findElement(By.id("ion-input-4")).sendKeys("24D BOOKS AUTOMATION");
//        Thread.sleep(1000);
        WebElement server = driver.findElement(By.xpath("(//ion-button[normalize-space()='Add Server'])[1]"));
        server.click();
        Thread.sleep(1000);
        driver.findElement(By.id("ion-input-1")).sendKeys("Super User");
        driver.findElement(By.id("ion-input-2")).sendKeys("Wings@123");
        driver.findElement(By.cssSelector(".ion-color.ion-color-wings.ios.button.button-block.button-solid.ion-activatable.ion-focusable")).click();
        Thread.sleep(4000);
    }

    @Test
    public void test() throws InterruptedException, IOException {
        driver.findElement(By.xpath("//*[text()='Explorer']")).click();
        Thread.sleep(2000);

        // general info
        driver.findElement(By.cssSelector("body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-router-outlet:nth-child(1) > app-tabs:nth-child(2) > ion-tabs:nth-child(1) > div:nth-child(1) > ion-router-outlet:nth-child(1) > app-app-navigator:nth-child(2) > ion-content:nth-child(2) > div:nth-child(1) > div:nth-child(1) > ion-grid:nth-child(2) > ion-row:nth-child(1) > ion-col:nth-child(3) > div:nth-child(1) > ion-label:nth-child(2)")).click();
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='transaction-tables']//div[1]//ion-list[1]//ion-list-header[1]//ion-button[1]"))).click();
        Thread.sleep(1500);
        driver.findElement(By.id("ion-input-6")).sendKeys("21-11-2025");
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Select Branch')]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[text()='AT_Branch 1_Reg']")).click();
        driver.findElement(By.xpath("//*[contains(@placeholder,'Select Party Account')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//ion-label[normalize-space()='At_Cus_Reg_Intra']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='Inter State Sales to Registered Dealers']")).click();
        Thread.sleep(3000);
        WebElement continueBtn = driver.findElement(By.xpath("(//button[@type='button'])[11]"));
        continueBtn.click();
        driver.findElement(By.id("ion-input-9")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[text()='AT_Executive 1']")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("ion-input-10")).click();
        driver.findElement(By.id("ion-input-10")).sendKeys("As of now no Remarks");
        driver.findElement(By.xpath("//*[text()='Done']")).click();

//Cash
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//ion-button[@size='small'][normalize-space()='Edit'])[2]"))).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("(//ion-button[normalize-space()='+ New'])[1]")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("ion-input-11")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[text()='AT_Cash Acc 1']")).click();
        Thread.sleep(2000);
        WebElement amount= driver.findElement(By.id("ion-input-12"));
        amount.sendKeys("100.123");
        driver.findElement(By.id("ion-input-13")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='AT_TDS Transaction Nature 1']")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("ion-input-14")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[text()='AT_TDS Paid Acc 1']")).click();
        Thread.sleep(1500);
        WebElement tdsAmount= driver.findElement(By.id("ion-input-15"));tdsAmount.sendKeys("50.13");
        driver.findElement(By.id("ion-textarea-0")).sendKeys("No comments as of now");
        driver.findElement(By.xpath("(//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable'][normalize-space()='Done'])[2]")).click();
        driver.findElement(By.xpath("//*[text()='Done']")).click();

//Cheques
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//ion-button[@size='small'][normalize-space()='Edit'])[3]"))).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("(//ion-button[normalize-space()='+ New'])[1]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Select Bank Account')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='AT_Bank Acc 1']")).click();
        Thread.sleep(2000);
        WebElement amount1= driver.findElement(By.xpath("//*[contains(@placeholder,'Enter Amount')]"));
        amount1.sendKeys("100.123");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@placeholder='Enter Cheque/EFT No']")).click();
        driver.findElement(By.xpath("//*[@placeholder='Enter Cheque/EFT No']")).sendKeys("2001");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Enter Cheque Date')]")).sendKeys("24-12-2025");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Select Drawn On Bank')]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[text()='AT_Drawn On Bank 1']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Select TDS Transaction Nature')]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[text()='AT_TDS Transaction Nature 1']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Select TDS Account')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text()='AT_TDS Paid Acc 1']")).click();
        Thread.sleep(1500);
        WebElement tdsAmount1= driver.findElement(By.xpath("//*[contains(@placeholder,'Enter TDS Amount')]"));tdsAmount1.sendKeys("50.13");
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Select Charges Account')]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[text()='AT_Charges  Acc 1']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Enter Charges')]")).sendKeys("50.13");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Enter Comments')]")).sendKeys("no comments");
        Thread.sleep(2500);
        driver.findElement(By.cssSelector("body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-modal:nth-child(3) > div:nth-child(1) > ion-footer:nth-child(3) > ion-toolbar:nth-child(1) > ion-button:nth-child(1)")).click();
        driver.findElement(By.xpath("//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable']")).click();


//PostDated Cheques
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//ion-button[@size='small'][normalize-space()='Edit'])[4]"))).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("(//ion-button[normalize-space()='+ New'])[1]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Select PDC Account')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='AT_Cheques In Hand Acc 1']")).click();
        Thread.sleep(2000);
        WebElement amount2= driver.findElement(By.xpath("//*[@placeholder='Enter Amount']"));
        amount2.sendKeys("100.123");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Enter Cheque/EFT No')]")).click();
        driver.findElement(By.xpath("//*[contains(@placeholder,'Enter Cheque/EFT No')]")).sendKeys("1980");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Enter Cheque Date')]")).sendKeys("24-12-2025");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Select Drawn On Bank')]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[text()='AT_Drawn On Bank 1']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Select TDS Transaction Nature')]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[text()='AT_TDS Transaction Nature 1']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Select TDS Account')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text()='AT_TDS Paid Acc 1']")).click();
        Thread.sleep(1500);
        WebElement tdsAmount2= driver.findElement(By.xpath("//*[contains(@placeholder,'Enter TDS Amount')]"));tdsAmount2.sendKeys("50.13");
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Enter Comments')]")).sendKeys("no comments");
        Thread.sleep(2500);
        driver.findElement(By.cssSelector("body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-modal:nth-child(3) > div:nth-child(1) > ion-footer:nth-child(3) > ion-toolbar:nth-child(1) > ion-button:nth-child(1)")).click();
        driver.findElement(By.xpath("//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable']")).click();

//Cheques[PDC]
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[5]"))).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("(//ion-button[normalize-space()='+ New'])[1]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Select Bank Account')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='AT_Bank Acc 1']")).click();
        Thread.sleep(2000);
        WebElement amount3= driver.findElement(By.xpath("//*[@placeholder='Enter Amount']"));
        amount3.sendKeys("100.123");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@placeholder='Enter Cheque/EFT No']")).click();
        driver.findElement(By.xpath("//*[@placeholder='Enter Cheque/EFT No']")).sendKeys("2001");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Enter Cheque Date')]")).sendKeys("24-12-2025");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Select Drawn On Bank')]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[text()='AT_Drawn On Bank 1']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Select TDS Transaction Nature')]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[text()='AT_TDS Transaction Nature 1']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Select TDS Account')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text()='AT_TDS Paid Acc 1']")).click();
        Thread.sleep(1500);
        WebElement tdsAmount3= driver.findElement(By.xpath("//*[contains(@placeholder,'Enter TDS Amount')]"));tdsAmount3.sendKeys("50.13");
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Enter Comments')]")).sendKeys("no comments");
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-modal:nth-child(3) > div:nth-child(1) > ion-footer:nth-child(3) > ion-toolbar:nth-child(1) > ion-button:nth-child(1)")).click();
        driver.findElement(By.xpath("//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable']")).click();


        WebElement element = driver.findElement(By.xpath("(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[6]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);


//Credit card
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[6]"))).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("(//ion-button[normalize-space()='+ New'])[1]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Select Swipe Machine Type')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='AT_Swipe Machine Type 1']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Select Swipe Type')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='AT_Swipe Type 1']")).click();
        Thread.sleep(2000);
        WebElement amount4= driver.findElement(By.xpath("//*[contains(@placeholder,'Enter Amount')]"));
        amount4.sendKeys("100.123");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Select TDS Transaction Nature')]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[text()='AT_TDS Transaction Nature 1']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Select TDS Account')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text()='AT_TDS Paid Acc 1']")).click();
        Thread.sleep(1500);
        WebElement tdsAmount4= driver.findElement(By.xpath("//*[contains(@placeholder,'Enter TDS Amount')]"));tdsAmount4.sendKeys("50.13");
        Thread.sleep(1500);
        driver.findElement(By.xpath("//input[@placeholder='Enter Card No']")).sendKeys("9989211079");
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Enter Approval No')]")).sendKeys("23546");
        Thread.sleep(1500);
        driver.findElement(By.cssSelector("body > app-root:nth-child(1) > ion-app:nth-child(1) > ion-modal:nth-child(3) > div:nth-child(1) > ion-footer:nth-child(3) > ion-toolbar:nth-child(1) > ion-button:nth-child(1)")).click();
        driver.findElement(By.xpath("//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable']")).click();

//summary
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[7]"))).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable'])[1]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("(//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar in-toolbar-color ion-activatable ion-focusable'])[1]")).click();
        Thread.sleep(2500);
        driver.findElements(By.xpath("//button[.//span[normalize-space()='OK']]")).get(4).click();

        Thread.sleep(2000);
        WebElement geninfo = driver.findElement(By.xpath("(//ion-button[@class='ios button button-small button-clear ion-activatable ion-focusable'][normalize-space()='Edit'])[1]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(geninfo).perform();
        Thread.sleep(1500);
        geninfo.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[contains(@placeholder,'Select Party Account')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//ion-label[normalize-space()='At_Cus_Reg_Intra']")).click();
        Thread.sleep(2500);
        driver.findElement(By.xpath("//*[text()='Intra State Sales to Registered Dealers']")).click();
        Thread.sleep(3000);
        WebElement continueBt1= driver.findElement(By.xpath("(//button[@type='button'])[11]"));
        continueBt1.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar ion-activatable ion-focusable'])[1]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("(//ion-button[@class='ion-color ion-color-wings ios button button-block button-solid in-toolbar in-toolbar-color ion-activatable ion-focusable'])[1]")).click();
        Thread.sleep(25000);
        WebElement validateTransaction=driver.findElement(By.xpath("//*[contains(text(),'Transaction saved successfully')]"));
        if (validateTransaction.getText().contains("Transaction saved successfully")) {
            driver.findElements(By.xpath("//button[.//span[normalize-space()='OK']]")).get(4).click();
            System.out.println("Transaction saved And it's clicked successfully");
        }
        else {
            Assert.fail("Transaction not saved pls check again");
        }

    }

    @AfterTest
    public void logOut() throws InterruptedException {
        Thread.sleep(1500);
        driver.findElement(By.xpath("(//ion-label[normalize-space()='Home'])[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text()='Super User']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[text()='Sign Out']")).click();
        driver.close();
        driver.quit();
    }

}




































//List<WebElement> okay=driver.findElements(By.xpath("//button[.//span[normalize-space()='OK']]"));
//        okay.get(4).click();
//        for(WebElement v:okay){
//            System.out.println(v.getText()+"size ");
//            if (v.getText().equals("OK")) v.click();
//            System.out.println(v.getSize());
//        }

//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Receipts from Parties']"))).click();

//line 103 tp 105

//WebElement continueBtn = driver.findElement(By.xpath("(//button[@type='button'])[11]"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueBtn);
//        continueBtn.click();
//        driver.findElement(By.cssSelector("ion-alert[id='ion-overlay-32'] div[class='alert-button-group sc-ion-alert-ios'] button[type='button']")).click();
package com.wings.pages;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.wings.utils.Common;
import org.testng.Assert;

import java.io.IOException;

public class AppLogin {
    WindowsDriver driver, loginDriver, rootDriver;
    Common common=new Common(driver);
    String fileData="./src/main/resources/company_Name.json";


    public WindowsDriver login() throws IOException, InterruptedException {

        driver= common.initializeDriver(common.getProperty("app"));
        String currentwindowHandle=driver.getWindowHandle();
        System.out.println("Window 1 -"+currentwindowHandle);

        driver.findElement(By.name("24DBooksFin")).click();
        Thread.sleep(20000);

        rootDriver=common.initializeDriver("Root");
        WebElement login = rootDriver.findElement(By.name("Wings 24 - Web Client"));

        String nativeWindow = login.getAttribute("NativeWindowHandle");
        String hexLoginId = Integer.toHexString(Integer.parseInt(nativeWindow));
        System.out.println("window id: " + hexLoginId);

        loginDriver=common.navigateToAppWindow(hexLoginId);
        common=new Common(loginDriver);

        common.inputText("xpath","//Edit[@Name='Password']",common.getProperty("password"));
        System.out.println("Password TagName "+ common.getTagName("name","Password"));
        common.clickElement("name","Submit");
        Thread.sleep(25000);

        driver.quit();
        rootDriver.quit();
        return loginDriver;
    }

    public void singleUserLogin() throws InterruptedException, IOException, ParseException {
        Thread.sleep(2000);
        common.clickElement("xpath","//Pane[@Name='Choose a recent company']/Text[@Name='> "+common.getData(fileData,"companyName")+"']/*[@Name='> "+common.getData(fileData,"companyName")+"']");
        Thread.sleep(3000);
        common.inputText("xpath","//Edit[@Name='Password']", common.getData(fileData,"password"));
        common.clickElement("xpath","//Button[@Name='Submit']");
        Thread.sleep(2500);
        common.clickElement("name","OK");
        System.out.println("Super User Login for "+common.getData(fileData,"companyName")+" company is successful " +new String(Character.toChars(0x2705)));
        String title=driver.getTitle();
        System.out.println(title);
//        Assert.assertEquals("Wings Finance - PRO [ Wings InfoNet ; 01-04-2024 To 31-03-2025 ; Super User ]",title);
    }

    public WindowsDriver launchSingleUserApp() throws IOException, InterruptedException {
        driver=common.initializeDriver(common.getProperty("singleUserApp"));
        driver.manage().window().maximize();
        Thread.sleep(2000);
        common.findWebElement("name","Wings 24");
        return driver;
    }

    public void logout() throws IOException {
        try {
            common.clickElement("xpath", "//MenuItem[@Name='File']");
            common.clickElement("name", "Exit");
            common.clickElement("name", "Yes");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
//        } finally {
//            common.quitWinAppServer();
//        }
    }

    public void deleteSingleTransaction() throws InterruptedException {
//        common.clickElement("xpath", "//Text[@Name='Last Saved :']/following-sibling::Text//*[contains(@ControlType, 'Hyperlink')]");

        WebElement parent = common.findWebElement("xpath", "//Text[@Name='Last Saved :']/following-sibling::Text");

        WebElement hyperlink = parent.findElement(By.xpath("//*[contains(@ControlType, 'Hyperlink')]"));
        hyperlink.click();

        common.clickElement("xpath","//Button[@Name='View']");
        Thread.sleep(2500);
        common.clickElement("xpath","//Button[@Name='Tools']");
    }
}

package com.wings.utils;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Properties;

public class DriverManager {
    WindowsDriver driver, logindriver, rootdriver;

//    public static Connection getConnection(String sqlUrl, String sqlUser, String sqlPassword) {
//        sqlUrl="10.10.10.90";
//        sqlUser="dbuser1";
//        sqlPassword="dbuser1";
//        return null;
//    }

    public WindowsDriver login() throws IOException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "C:\\Program Files (x86)\\Wings Infonet Ltd\\Wings 23D Launcher\\WLauncher23D.exe");
        capabilities.setCapability("platformName", "Windows");
        capabilities.setCapability("deviceName", "WindowsPC");
        //giving the control to WAD
        driver = new WindowsDriver<>(new URL("http://127.0.0.1:4    723/"), capabilities);
        driver.manage().window().maximize();
        driver.findElement(By.name("24DBooksFin")).click();
        Thread.sleep(5000);

        //go to the  main desktop window and search for WebClient
        DesiredCapabilities rootcapabilities = new DesiredCapabilities();
        rootcapabilities.setCapability("app", "Root");
        rootcapabilities.setCapability("deviceName", "WindowsPC");
        rootdriver = new WindowsDriver<>(new URL("http://127.0.0.1:4723/"), rootcapabilities);
        WebElement login = rootdriver.findElement(By.name("Wings 24 - Web Client"));
        //the windowHandle which we receive is integer, so we need to convert it into hexadecimal string
        String nativeWindow = login.getAttribute("NativeWindowHandle");
        String hexloginid = Integer.toHexString(Integer.parseInt(nativeWindow));
        System.out.println("window id: " + hexloginid);

        DesiredCapabilities webClientCapabilities = new DesiredCapabilities();
        webClientCapabilities.setCapability("ms:waitforAppLaunch", 15); //wait for 15seconds( mention time in secs)
        //appTopLevelWindow- it helps us to take control of specific window which we want to handle
        webClientCapabilities.setCapability("appTopLevelWindow", hexloginid);

        logindriver = new WindowsDriver(new URL("http://127.0.0.1:4723"), webClientCapabilities);
//        logindriver.findElement(By.name("Login")).click();

        logindriver.findElement(By.xpath("//Edit[@Name='Password']")).sendKeys(getProperty("password"));
        logindriver.findElement(By.name("Submit")).click();
        Thread.sleep(25000);
        driver.quit();
        rootdriver.quit();
        return logindriver;
    }

    public String getProperty(String key) throws IOException {
        FileInputStream fis = new FileInputStream("./config.properties");
        Properties prop = new Properties();
        prop.load(fis);
        return prop.getProperty(key);
    }
}

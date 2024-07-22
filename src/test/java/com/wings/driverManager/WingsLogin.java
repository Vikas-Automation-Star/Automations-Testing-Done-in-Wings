package com.wings.driverManager;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class WingsLogin {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("app","C:\\Program Files (x86)\\Wings Infonet Pvt Ltd\\Wings 24D Launcher\\WLauncher24D.exe");
        capabilities.setCapability("platformName","Windows");
        capabilities.setCapability("deviceName","WindowsPC");

        WindowsDriver driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
        driver.manage().window().maximize();
        String currentwindowHandle=driver.getWindowHandle();
        System.out.println("Window 1 -"+currentwindowHandle);
        driver.findElementByName("24DBooksFin").click();
        Thread.sleep(15000);
        //driver.findElementByXPath("//window[@name='Wings 24 - Web Client']").click();
        Set<String> windowHandles=driver.getWindowHandles();
        System.out.println("Windows -"+windowHandles);
        for(String windowHandle:windowHandles){
            if(!windowHandle.equals(currentwindowHandle)){

                driver.switchTo().window(windowHandle);
                System.out.println("Window 2 -"+driver.getWindowHandle());
            }
        }
        driver.findElementByXPath("//window[@name='Wings 24 - Web Client']//pane[@name='Login']//edit[@name='Password']").sendKeys("Wings@123");
        Thread.sleep(5000);

        driver.quit();

    }

}

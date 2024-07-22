package com.wings.driverManager;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;

public class SaleOrder {


    public static void main(String[] args) throws IOException, InterruptedException {


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "C:\\Program Files (x86)\\Wings Infonet\\Wings Books 24D\\Wings.exe");
        capabilities.setCapability("platformName", "Windows");
        capabilities.setCapability("deviceName", "WindowsPC");
        WindowsDriver driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
    }

    }

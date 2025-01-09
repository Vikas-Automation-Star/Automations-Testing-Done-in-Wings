package com.wings.driverManager;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


public class WinAppDemo {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "C:\\Program Files (x86)\\Wings Infonet Pvt Ltd\\Wings 24D Launcher\\WLauncher24D.exe");
        capabilities.setCapability("platformName", "Windows");
        capabilities.setCapability("deviceName", "WindowsPC");

        WindowsDriver driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
        driver.manage().window().maximize();
        String currentwindowHandle = driver.getWindowHandle();
        System.out.println("Window 1 -" + currentwindowHandle);
        driver.findElementByName("24DBooksFin").click();
        Thread.sleep(12000);

        DesiredCapabilities rootCapabilities = new DesiredCapabilities();
        rootCapabilities.setCapability("app", "Root");
        rootCapabilities.setCapability("platformName", "Windows");
        rootCapabilities.setCapability("deviceName", "WindowsPC");
        WindowsDriver rootdriver = new WindowsDriver(new URL("http://127.0.0.1:4723"), rootCapabilities);

        WebElement loginWindow = rootdriver.findElement(By.name("Wings 24 - Web Client"));
        String nativeloginWindow = loginWindow.getAttribute("NativeWindowHandle");
        String hexloginWindow = Integer.toHexString(Integer.parseInt(nativeloginWindow));
        System.out.println("Login window - " + hexloginWindow);

        DesiredCapabilities webClientCapabilities = new DesiredCapabilities();
        webClientCapabilities.setCapability("ms:waitforAppLaunch", 15);
        webClientCapabilities.setCapability("appTopLevelWindow", hexloginWindow);
        WindowsDriver logindriver = new WindowsDriver(new URL("http://127.0.0.1:4723"), webClientCapabilities);
        logindriver.findElement(By.name("Login")).click();
        //logindriver.findElement(By.xpath("//*[@name='Login']"));
        logindriver.findElement(By.xpath("//Edit[@Name='Password']")).sendKeys("Wings@123");
        System.out.println("Password Tag Name" + logindriver.findElement(By.name("Password")).getTagName());
        logindriver.findElement(By.name("Submit")).click();
        //driver.findElementByXPath("//window[@name='Wings 24 - Web Client']//pane[@name='Login']//edit[@name='Password']").sendKeys("Wings@123");
        Thread.sleep(10000);
        logindriver.findElement(By.name("OK")).click();
        //logindriver.findElement(By.name("File")).click();
        //logindriver.findElement(By.name("Company")).click();
        //logindriver.findElement(By.name("Sales")).click();
        logindriver.findElement(By.name("Purchase")).click();
        Thread.sleep(2000);
        //logindriver.findElement(By.xpath("//TabItem[@Name='Configure']")).click();
        logindriver.findElement(By.xpath("//MenuItem[@Name='Suppliers']")).click();
        Thread.sleep(3000);
        //logindriver.findElement(By.name("All Suppliers")).click();
        WebElement allSuppliers = logindriver.findElement(By.xpath("//TreeItem[@Name='All Suppliers']"));
        allSuppliers.click();

        Thread.sleep(2000);
        Actions actions = new Actions(logindriver);
        actions.contextClick(allSuppliers).perform();
        Thread.sleep(2000);
        logindriver.findElement(By.xpath("//MenuItem[@Name='New Master']")).click();
        Thread.sleep(2000);
        //logindriver.findElement(By.name("  New ")).click();
        //Thread.sleep(2000);
        //logindriver.findElement(By.name("New Master")).click();
        // logindriver.findElement(By.xpath("//Button[@Name='New Master']")).click();
        Thread.sleep(3000);
        logindriver.findElement(By.xpath("//Edit[@Name='New Supplier *']")).sendKeys("WingsDemo");
        logindriver.findElement(By.xpath("//Edit[@Name='Supplier Code']")).sendKeys("2024");
        logindriver.findElement(By.xpath("//Edit[@Name='Description']")).sendKeys("Wings Test Supplier via WinAppDriver");
        logindriver.findElement(By.xpath("//Pane[@Name='Registration']/Button[@Name='...']")).click();
        Thread.sleep(2000);
        logindriver.findElement(By.xpath("//Edit[@Name='Party Reg Type *']/Button[@Name='Open']")).click();
        //logindriver.findElement(By.xpath("//Edit[@Name='Search Box']")).sendKeys("Exempted");
        //logindriver.findElement(By.xpath("//Button[@Name='Find']")).click();
        //logindriver.findElement(By.xpath("//Pane[@Name='LookUp DropDown']/Table/*[@Name='Data Panel']/ListItem[@Name='Row 1']/Item[@Name='MasterName row 1']")).click();
        //logindriver.findElement(By.xpath("//Pane[@Name='LookUp DropDown']//ListItem[@Name='Row 1']/Item[@Name='MasterName row 1']")).click();
        logindriver.findElement(By.xpath("//Edit[@Name='Address 1 *']")).sendKeys("Kondapur");
        logindriver.findElement(By.xpath("//Edit[@Name='City *']")).sendKeys("Hyderabad");
        logindriver.findElement(By.xpath("//Edit[@Name='State *']/Button[@Name='Open']")).click();
        Thread.sleep(2000);
        //logindriver.findElement(By.xpath("//Item[@Name='MasterName row 4']")).click();
        logindriver.findElement(By.xpath("//Edit[@Name='State Code *']")).sendKeys("21");
        logindriver.findElement(By.xpath("//Edit[@Name='ZIP *']")).sendKeys("500084");
        logindriver.findElement(By.xpath("//Button[@Name='Ok']")).click();
        logindriver.findElement(By.xpath("//Edit[@Name='Credit Period In Days']")).sendKeys("45");
        logindriver.findElement(By.xpath("//Button[@Name='Page down']")).click();
        logindriver.findElement(By.xpath("//Edit[@Name='Purchase Price List']/Button[@Name='Open']")).click();
        Thread.sleep(3000);
        logindriver.findElement(By.xpath("//Button[@Name='Save']")).click();
        logindriver.findElement(By.xpath("//Button[@Name='OK']")).click();
        logindriver.findElement(By.xpath("//Button[@Name='Cancel']")).click();
        logindriver.findElement(By.xpath("//ListItem[@Name='ManojDemo']")).click();
        logindriver.findElement(By.xpath("//Button[@Name='  Properties']")).click();
        Thread.sleep(2000);
        logindriver.findElement(By.xpath("//Edit[@Name='Credit Period In Days']")).sendKeys("45");
        logindriver.findElement(By.xpath("//Button[@Name='Save']")).click();
        Thread.sleep(2000);
        logindriver.findElement(By.xpath("//Button[@Name='OK']")).click();

        //logindriver.close();
        //driver.quit();
        // rootdriver.quit();
        //logindriver.quit();
    }


}

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AppLaunch {
    private static final Logger log = LoggerFactory.getLogger(AppLaunch.class);
    WindowsDriver driver, rootdriver, logindriver;

    @BeforeTest
    public void beforeTest() throws InterruptedException, MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "C:\\Program Files (x86)\\Wings Infonet Ltd\\Wings 23D Launcher\\WLauncher23D.exe");
        capabilities.setCapability("platformName", "Windows");
        capabilities.setCapability("deviceName", "WindowsPC");
        //giving the control to WAD
        driver = new WindowsDriver<>(new URL("http://127.0.0.1:4723/"), capabilities);
        driver.manage().window().maximize();
        driver.findElement(By.name("24DBooksFin")).click();
        Thread.sleep(5000);

        //go to the  main desktop window and search for WebClient
        DesiredCapabilities rootcapabilities = new DesiredCapabilities();
        rootcapabilities.setCapability("app", "Root");
        rootcapabilities.setCapability("deviceName", "WindowsPC");
        rootdriver = new WindowsDriver<>(new URL("http://127.0.0.1:4723/"), rootcapabilities);
        Thread.sleep(10000);
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
        logindriver.findElement(By.xpath("//Edit[@Name='Password']")).sendKeys("Wings@123");
        logindriver.findElement(By.name("Submit")).click();
        Thread.sleep(20000);
        logindriver.findElement(By.name("OK")).click();
        Thread.sleep(5000);
    }

    @Test
    public void test() {
        logindriver.findElement(By.xpath("//MenuItem[@Name='File']")).click();
        WebElement exit = logindriver.findElement(By.name("Exit"));
        Assert.assertTrue(exit.isDisplayed(), "Exit is not displayed");

        logindriver.findElement(By.xpath("//MenuItem[@Name='Company']")).click();
        WebElement company = logindriver.findElement(By.name("Company"));
        Assert.assertTrue(company.isDisplayed(), "Company isn't displayed");

        logindriver.findElement(By.xpath("//MenuItem[@Name='Sales']")).click();
        WebElement Customers = logindriver.findElement(By.name("Customers"));
        Assert.assertTrue(Customers.isDisplayed(), "Customers isn't displayed");

        logindriver.findElement(By.xpath("//MenuItem[@Name='Purchase']")).click();
        WebElement Suppliers = logindriver.findElement(By.name("Suppliers"));
        Assert.assertTrue(Suppliers.isDisplayed(), "Suppliers isn't displayed");

        logindriver.findElement(By.xpath("//MenuItem[@Name='Inventory']")).click();
        WebElement Opening_Stock = logindriver.findElement(By.name("Opening Stock"));
        Assert.assertTrue(Opening_Stock.isDisplayed(), "Opening Stock isn't displayed");

        logindriver.findElement(By.xpath("//MenuItem[@Name='Finance']")).click();
        WebElement Chart_of_Accounts = logindriver.findElement(By.name("Chart of Accounts"));
        Assert.assertTrue(Chart_of_Accounts.isEnabled(), "Chart of Accounts isn't displayed");

        logindriver.findElement(By.xpath("//MenuItem[@Name='Production']")).click();
        WebElement Bill_of_Material = logindriver.findElement(By.name("Bill of Material"));
        Assert.assertTrue(Bill_of_Material.isDisplayed(), "Bill of Material isn't displayed");

        logindriver.findElement(By.xpath("//MenuItem[@Name='Taxes']")).click();
        WebElement GST = logindriver.findElement(By.name("GST"));
        Assert.assertTrue(GST.isDisplayed(), "GST isn't displayed");

        logindriver.findElement(By.xpath("//MenuItem[@Name='Configure']")).click();
        WebElement Voucher_Types = logindriver.findElement(By.name("Voucher Types"));
        Assert.assertTrue(Voucher_Types.isDisplayed(), "Voucher Types isn't displayed");

        logindriver.findElement(By.xpath("//MenuItem[@Name='Audit']")).click();
        WebElement Login_Status = logindriver.findElement(By.name("Login Status"));
        Assert.assertTrue(Login_Status.isDisplayed(), "Login Status isn't displayed");

        logindriver.findElement(By.xpath("//MenuItem[@Name='Tools']")).click();
        WebElement Search_Masters = logindriver.findElement(By.name("Search Masters"));
        Assert.assertTrue(Search_Masters.isDisplayed(), "Search Masters isn't displayed");

        logindriver.findElement(By.xpath("//MenuItem[@Name='Help']")).click();
        WebElement About = logindriver.findElement(By.name("About"));
        Assert.assertTrue(About.isDisplayed(), "About isn't displayed");
        //TAB ITEMS
        logindriver.findElement(By.xpath("//TabItem[@Name='Configure']")).click();
        WebElement data = logindriver.findElement(By.name("Settings"));
        Assert.assertTrue(data.isDisplayed(), "Settings is not displayed");

        logindriver.findElement(By.xpath("//TabItem[@Name='Analytics']")).click();
        WebElement dashBoard = logindriver.findElement(By.name("Dashboard"));
        Assert.assertTrue(dashBoard.isDisplayed(), "dashboard is not displayed");

        logindriver.findElement(By.xpath("//TabItem[@Name='Workbench']")).click();
        WebElement sales = logindriver.findElement(By.name("SALES"));
        WebElement purchase = logindriver.findElement(By.name("PURCHASES"));
        Assert.assertTrue(sales.isDisplayed() && purchase.isDisplayed(), "sales & purchase r not displayed");

        logindriver.findElement(By.xpath("//TabItem[@Name='My Todo List']")).click();
        WebElement mytodolist = logindriver.findElement(By.name("Row 2"));
        Assert.assertTrue(mytodolist.isDisplayed(), "Status message is not displayed");


    }

    @AfterTest
    public void afterTest() {
        //close the window
        logindriver.findElementByXPath("//MenuItem[@Name='File']").click();
        logindriver.findElementByXPath("//MenuItem[@Name='Exit']").click();
        logindriver.findElement(By.xpath("//Button[@Name='Yes']")).click();
        driver.quit();
        logindriver.quit();
        rootdriver.quit();
    }
}

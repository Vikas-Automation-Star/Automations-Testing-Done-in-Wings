package com.wings.utils;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.io.File;
import java.util.Random;

public class Common {

    WindowsDriver driver;
    Time time=new Time();
    Process process;

    public Common(WindowsDriver remotedriver){
        driver=remotedriver;
    }


    public WindowsDriver initializeDriver(String app) throws IOException {
//        initiateWinAppServer();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app",app);
        capabilities.setCapability("platformName", getProperty("platformName"));
        capabilities.setCapability("deviceName", getProperty("deviceName"));
        WindowsDriver newdriver = new WindowsDriver<>(new URL("http://127.0.0.1:4723/"), capabilities);
        driver=newdriver;
        return newdriver;
    }

    public WindowsDriver navigateToAppWindow(String windowId) throws MalformedURLException {
        DesiredCapabilities webClientCapabilities = new DesiredCapabilities();
        webClientCapabilities.setCapability("ms:waitforAppLaunch", 15);
        webClientCapabilities.setCapability("appTopLevelWindow", windowId);
        WindowsDriver appDriver = new WindowsDriver<>(new URL("http://127.0.0.1:4723"), webClientCapabilities);
        return appDriver;
    }

    public WebElement findWebElement(String locatorType, String locator) {
        By by = null;
        WebElement element=null;
        try {
            if (locatorType.equals("xpath")) {
                by = By.xpath(locator);
            } else if (locatorType.equals("name")) {
                by = By.name(locator);
            }
            element=this.driver.findElement(by);
        } catch (Exception e) {
            e.printStackTrace();
            screnShot();
        }
            return element;

    }
    public List<WebElement> findWebElements(String locatorType, String locator) {
        By by = null;
        List<WebElement> element=null;
        try {
            if (locatorType.equals("xpath")) {
                by = By.xpath(locator);
            } else if (locatorType.equals("name")) {
                by = By.name(locator);
            }
             element=this.driver.findElements(by);
        } catch (Exception e) {
            e.printStackTrace();
            screnShot();
        }
        return element;

    }

    public void clickElement(String locatorType, String locator){
        WebElement element=findWebElement(locatorType,locator);
        element.click();
    }

    public boolean isDisplayed(String locatorType, String locator){
        boolean displayed=findWebElement(locatorType,locator).isDisplayed();
        return displayed;
    }

    public boolean isEnabled(String locatorType, String locator){
        boolean enabled =findWebElement(locatorType,locator).isEnabled();
        return enabled;
    }

    public void inputText(String locatorType,String locator,String inputText){
        WebElement element=findWebElement(locatorType,locator);
        element.sendKeys(inputText,Keys.TAB);
    }
    public void getText(String locatorType,String locator){
        WebElement element=findWebElement(locatorType,locator);
        element.getText();
    }

    public void inputAndVerify(String locatorType,String locator,String inputText){
        WebElement element=findWebElement(locatorType,locator);
        element.sendKeys(inputText,Keys.TAB);
//        Thread.sleep(1000);
//        WebElement element = common.findWebElement(locatorType, locator);
        if (element.getText().equals(inputText)) {
            System.out.println("successfully selected/opened:- " + element.getText());

        } else {
            Assert.fail(element.getText() + "is not seleced");
        }
    }

    public void rowDropDown(String dataToBeSelected) {
        List<WebElement> elementList =findWebElements("xpath", "//Table/*[@Name='Data Panel']/*[contains(@Name,'Row')]");
        System.out.println("Size :" + elementList.size());
        for (WebElement i : elementList) {
//            System.out.println(i.getText());
//            System.out.println(i.getAttribute("SelectionItem.IsSelected"));
            if (i.getAttribute("SelectionItem.IsSelected").equals("True")) {

                if (!i.getText().contains(dataToBeSelected)) {
                    i.sendKeys(Keys.DOWN);
                } else if (i.getText().contains(dataToBeSelected)) {
                    i.sendKeys(Keys.ENTER);
                    break;
                }
            }
        }
    }

    public void sliderHandling(String locatorType,String locator,int xOffset,int yOffset){
        WebElement slider = findWebElement(locatorType,locator);
        Actions actions = new Actions(driver);
        actions.clickAndHold(slider).moveByOffset(xOffset,yOffset).release().perform();
    }

    public void deleteInvalidRows(){
        WebElement element= findWebElement("xpath","//Edit[@Name=' Row 0, Not sorted.']");
        Actions actions=new Actions(driver);
        actions.contextClick(element).perform();
        clickElement("xpath","//MenuItem[@Name='Delete Invalid Rows']");
    }
    public String getTagName(String locatorType,String locator){
        WebElement element=findWebElement(locatorType,locator);
        return element.getTagName();
    }

    public String getProperty(String key) throws IOException {
        FileInputStream fis = new FileInputStream("./config.properties");
        Properties prop = new Properties();
        prop.load(fis);
        return prop.getProperty(key);
    }

    public String getData(String fileName,String key) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(fileName);
        Object obj = parser.parse(reader);
        JSONObject jsonObject = (JSONObject) obj;
        Object value = jsonObject.get(key);
        if(value ==null ){
            return null;
        } else if(value instanceof String) {
            return (String) value;
        } else if (value instanceof Long) {
            return Long.toString((Long) value);
        } else if (value instanceof Double) {
            return Double.toString((Double) value);
        } else if (value instanceof Boolean) {
            return Boolean.toString((Boolean) value);
        } else {
            return String.valueOf(value);
        }
    }

//    public String getOptionalData(String fileName,String key) throws IOException, ParseException {
//        JSONParser parser = new JSONParser();
//        try {
//            FileReader reader = new FileReader(fileName);
//            Object obj = parser.parse(reader);
//            JSONObject jsonObject = (JSONObject) obj;
//            Object value = jsonObject.get(key);
//            if (value == null) {
//                throw new NoSuchElementException("no key found");
//            } else if (value instanceof String) {
//                return (String) value;
//            } else if (value instanceof Long) {
//                return Long.toString((Long) value);
//            } else if (value instanceof Double) {
//                return Double.toString((Double) value);
//            } else if (value instanceof Boolean) {
//                return Boolean.toString((Boolean) value);
//            } else {
//                return String.valueOf(value);
//            }
//        } catch (Exception e) {
//            System.out.println("No Key found, Exception Handled");
//        }
//    }

    public int getRandom(){
        Random rand = new Random();
        int randomNumber = 1000 + rand.nextInt(9000);
        return randomNumber;
    }


    public void screnShot(){
        try {
            TakesScreenshot screenshot= (TakesScreenshot) driver;
            File temp= screenshot.getScreenshotAs(OutputType.FILE);
            File perm=new File("./results/screenshots/failure"+time.timeStamp()+".png");
            FileHandler.copy(temp,perm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDataEvenNoKeyPresent(String fileName, String key) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(fileName)) { // Try-with-resources for automatic closing
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            if (!jsonObject.containsKey(key)) {
                System.out.println("No key present in the file.");
                return null; // Return null if the key is not present
            }

            Object value = jsonObject.get(key);
            if (value == null) {
                return null; // Return null if the value is null
            } else if (value instanceof String) {
                return (String) value;
            } else if (value instanceof Long) {
                return Long.toString((Long) value);
            } else if (value instanceof Double) {
                return Double.toString((Double) value);
            } else if (value instanceof Boolean) {
                return Boolean.toString((Boolean) value);
            } else {
                return String.valueOf(value); // Fallback for other types
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
            e.printStackTrace(); // Print stack trace for debugging
        } catch (IOException e) {
            System.err.println("An I/O error occurred while reading the file.");
            e.printStackTrace(); // Print stack trace for debugging
        } catch (ParseException e) {
            System.err.println("Error parsing the JSON file.");
            e.printStackTrace(); // Print stack trace for debugging
        }

        return null; // Return null in case o
    }

    public void quit(){
        driver.quit();
    }

    public void initiateWinAppServer() throws IOException {
        String wadServerPath = "C:\\Program Files (x86)\\Windows Application Driver\\WinAppDriver.exe";
        ProcessBuilder builder = new ProcessBuilder(wadServerPath).inheritIO();
        process = builder.start(); // your winappdriver code
    }

    public void quitWinAppServer() throws IOException {
//        process.destroy();
        String command="taskkill /IM \"WinAppDriver.exe\" /F";
        Process p = Runtime.getRuntime().exec(command);
    }
}

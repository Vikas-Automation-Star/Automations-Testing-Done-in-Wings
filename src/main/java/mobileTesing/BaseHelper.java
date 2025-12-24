package mobileTesing;

import com.wings.utils.Time;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class BaseHelper {
    WebDriver driver;
    Time time = new Time();

    public BaseHelper(WebDriver driver){
        this.driver=driver;
    }

    public WebElement findWebElementInMobile(String locatorType, String locator) {
        By by = null;
        WebElement element = null;
        try {
            switch (locatorType.toLowerCase()) {
                case "id":
                    by = By.id(locator);
                    break;
                case "name":
                    by = By.name(locator);
                    break;
                case "xpath":
                    by = By.xpath(locator);
                    break;
                case "css":
                case "cssselector":
                    by = By.cssSelector(locator);
                    break;
                case "classname":
                    by = By.className(locator);
                    break;
                case "tagname":
                    by = By.tagName(locator);
                    break;

                case "linktext":
                    by = By.linkText(locator);
                    break;
                case "partiallinktext":
                    by = By.partialLinkText(locator);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid locator type: " + locatorType);
            }
            element = driver.findElement(by);
        } catch (Exception e) {
            e.printStackTrace();
            screenShot();  // your screenshot method
        }
        return element;
    }

    public WebElement findWebElementWithTime(String locatorType, String locator, int timeout) {
        By by;
        switch (locatorType.toLowerCase()) {
            case "id":
                by = By.id(locator);
                break;
            case "name":
                by = By.name(locator);
                break;
            case "xpath":
                by = By.xpath(locator);
                break;
            case "css":
            case "cssselector":
                by = By.cssSelector(locator);
                break;
            case "classname":
                by = By.className(locator);
                break;
            case "tagname":
                by = By.tagName(locator);
                break;
            case "linktext":
                by = By.linkText(locator);
                break;
            case "partiallinktext":
                by = By.partialLinkText(locator);
                break;
            default:
                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            e.printStackTrace();
            screenShot();
            return null;
        }
    }

    public List<WebElement> findWebElementsInMobile(String locatorType, String locator, int timeoutInSeconds) {
        By by = null;
        List<WebElement> elements = null;

        try {
            switch (locatorType.toLowerCase()) {
                case "id":
                    by = By.id(locator);
                    break;

                case "name":
                    by = By.name(locator);
                    break;

                case "xpath":
                    by = By.xpath(locator);
                    break;

                case "css":
                case "cssselector":
                    by = By.cssSelector(locator);
                    break;

                case "classname":
                    by = By.className(locator);
                    break;

                case "tagname":
                    by = By.tagName(locator);
                    break;

                case "linktext":
                    by = By.linkText(locator);
                    break;

                case "partiallinktext":
                    by = By.partialLinkText(locator);
                    break;

                default:
                    throw new IllegalArgumentException("Invalid locator type: " + locatorType);
            }

            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);

            // ✅ Wait until at least one matching element is present
            elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));

        } catch (Exception e) {
            e.printStackTrace();
            screenShot();
        }

        return elements;
    }

    public void screenShot() {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File temp = screenshot.getScreenshotAs(OutputType.FILE);
            File perm = new File("./results/screenshots/failure" + time.timeStamp() + ".png");
            FileHandler.copy(temp, perm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) throws IOException {
        FileInputStream fis = new FileInputStream("./config.properties");
        Properties prop = new Properties();
        prop.load(fis);
        return prop.getProperty(key);
    }

    public void clickElement(String locatorType, String locator) {
        WebElement element = findWebElementInMobile(locatorType, locator);
        element.click();
    }

    public String getText(String locatorType, String locator) {
        WebElement element = findWebElementInMobile(locatorType, locator);
        return element.getText();
    }

    public void inputText(String locatorType, String locator, String inputText) {
        WebElement element = findWebElementInMobile(locatorType, locator);
        element.sendKeys(inputText, Keys.TAB);
    }

    public String getData(String fileName, String dataSet, String key) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(fileName);
        Object obj = parser.parse(reader);

        JSONArray jsonArray = (JSONArray) obj;
        for (Object objItem : jsonArray) { //find dataset name
            JSONObject jsonObject = (JSONObject) objItem;
            if (jsonObject.containsKey("dataset") && jsonObject.get("dataset").equals(dataSet)) {
                JSONObject dataObject = (JSONObject) jsonObject.get("data");
                Object value = dataObject.get(key);
                if (value == null) {
                    return null;
                } else if (value instanceof String) {
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
        }
        return null;
    }

    public void addData(String locatorType, String locator, String fileName, String sheetName, String key, int j) throws IOException, ParseException {
        WebElement element = findWebElementInMobile(locatorType, locator);
        element.click();
        // build key like product0, product1, product2
        String dynamicKey = key + j;
        String value = getData(fileName, sheetName, dynamicKey);
        element.sendKeys(value);
    }

    public WebElement searchAndClick( String fileName, String sheetName, String key, int j) throws IOException, ParseException {
        String dynamicKey=key+j;
        WebElement element =driver.findElement(By.xpath(getData(fileName,sheetName,dynamicKey)));
//        WebElement element = findWebElementInMobile(locatorType, getData(fileName,sheetName,dynamicKey));
        element.click();
        return element ;
    }


}

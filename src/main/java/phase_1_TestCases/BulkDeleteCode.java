package phase_1_TestCases;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class BulkDeleteCode extends Transaction {
    WindowsDriver driver, rootdriver;
    Common common;
    String dataFile;

    public BulkDeleteCode(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }
    public void bulkDelete(List<String> voucherNumbers) throws InterruptedException, IOException, ParseException {
        common.clickElement("xpath", "//MenuItem[@Name='Tools']");
        common.clickElement("xpath", "//MenuItem[@Name='Vouchers']");
        common.clickElement("xpath", "//MenuItem[@Name='Bulk Delete']");
        //enter data
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "Root");
        rootdriver = new WindowsDriver<>(new URL("http://127.0.0.1:4723/"), capabilities);
        Thread.sleep(5000);
        List<WebElement> panes = By.tagName("Window").findElements(rootdriver);
        Thread.sleep(2500);
        if (panes.isEmpty()) {
            System.out.println("No windows found.");
        } else {
            for (WebElement i : panes) {
                String nativeWindow = i.getAttribute("NativeWindowHandle");
                String hexloginid = Integer.toHexString(Integer.parseInt(nativeWindow));
                System.out.println("window id: " + hexloginid);
                String name = i.getAttribute("Name");
                System.out.println("Name:- " + name);
                if (name.equals("Bulk Delete")) {
                    Thread.sleep(5000);

                    // Extract series and numbers from voucher list
                    String firstVoucher = voucherNumbers.get(0);
                    String lastVoucher = voucherNumbers.get(voucherNumbers.size() - 1);

                    String[] firstVoucherParts = firstVoucher.trim().split("\\s+");
                    String[] lastVoucherParts = lastVoucher.trim().split("\\s+");

                    if (firstVoucherParts.length < 2 || firstVoucherParts[1].isEmpty()) {
                        throw new IllegalArgumentException("Invalid firstVoucher format: " + firstVoucher);
                    }
                    if (lastVoucherParts.length < 2 || lastVoucherParts[1].isEmpty()) {
                        throw new IllegalArgumentException("Invalid lastVoucher format: " + lastVoucher);
                    }

                    String series = firstVoucherParts[0];  // Extracts "SE"
                    int startNum = Integer.parseInt(firstVoucherParts[1]);
                    int endNum = Integer.parseInt(lastVoucherParts[1]);

                    WebElement seriesInput = rootdriver.findElementByXPath("//Pane/Text[@Name='Document Series']/following-sibling::Edit");
                    seriesInput.sendKeys(series);

                    WebElement fromNumInput = rootdriver.findElementByXPath("//Pane/Text[@Name='From Document No']/following-sibling::Edit");
                    fromNumInput.sendKeys(String.valueOf(startNum));

                    WebElement toNumInput = rootdriver.findElementByXPath("//Pane/Text[@Name='To Document No']/following-sibling::Edit");
                    toNumInput.sendKeys(String.valueOf(endNum));

                    rootdriver.findElementByXPath("//Button[@Name='Delete']").click();

                    WebDriverWait wait = new WebDriverWait(rootdriver, 15);
                    try {
                        WebElement noTransactionOkButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//Button[@Name='OK']")));
                        if (noTransactionOkButton.isDisplayed()) {
                            noTransactionOkButton.click();
                            System.out.println("Clicked OK button - No transactions found.");
                            // After clicking OK, click Cancel to exit
                            WebElement cancelButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//Button[@Name='Cancel']")));
                            cancelButton.click();
                            // Fail the test as no transactions were found
                            Assert.fail("No Transaction Found and Deleted");
                        }
                    } catch (Exception e) {
                        // If "OK" button is not displayed, skip to "Yes"
                        System.out.println("No 'OK' button displayed, proceeding to Yes.");
                    }
                    WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//Button[@Name='Yes']")));
                    yesButton.click();
                    //additional yes
                    try {
                        WebElement additionalYesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//Button[@Name='Yes']")));
                        if (additionalYesButton.isDisplayed()) {
                            additionalYesButton.click();
                            System.out.println("Clicked additional Yes.");
                        }
                    } catch (Exception e) {
                        System.out.println("No additional 'Yes' button displayed.");
                    }
                    WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//Button[@Name='OK']")));
                    okButton.click();
                    break;
                }
            }
            validateDeletedTransactions(voucherNumbers);
        }
    }
    // now validated deleted one's

    public void validateDeletedTransactions1(List<String> expectedDeletedTransactions) throws InterruptedException {
        common.clickElement("xpath","//MenuItem[@Name='Audit']");
        common.clickElement("xpath","//MenuItem[@Name='Void Transactiones']");
        Thread.sleep(2500);
        common.clickElement("xpath","//Button[@Name='Submit']");
        Thread.sleep(2500);
        //navigate to date option
        WebElement date=common.findWebElement("xpath","//Header[@Name='Date']");
        Actions actions=new Actions(driver);
        actions.moveToElement(date).perform();
        common.clickElement("xpath","//Button[@Name='Date ColumnFilterButton']");
        List today=common.findWebElements("xpath","//Pane/List[@Name='Yesterday']/CheckBox[@Name='Today']");
        System.out.println("list of days: "+today.size());
        for (int i = 0; i < today.size(); i++) {
            WebElement date1= (WebElement) today.get(i);
            date1.click();
            String data=date1.getText();
            System.out.println("data: "+data);
        }
        common.clickElement("xpath","//Button[@Name='Close']");
        List<WebElement> listElements = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]/DataItem[contains(@Name,'Voucher No row')]");
        System.out.println("Size of elements under List: " + listElements.size());
        ArrayList<String> actualDeletedTransactions = new ArrayList<>();
        for (int i = 0; i < listElements.size(); i++) {
            WebElement element = listElements.get(i);
            String voucherText = element.getText().trim().replaceAll("\\s+", " ");
            System.out.println("Extracted Voucher: " + voucherText);
            actualDeletedTransactions.add(voucherText);
        }
        System.out.println("Final extracted transactions: " + actualDeletedTransactions);

        // Verify deleted transactions
        boolean allDeleted = actualDeletedTransactions.containsAll(expectedDeletedTransactions);
        boolean anyMissing = !expectedDeletedTransactions.containsAll(actualDeletedTransactions);
        if (allDeleted) {
            System.out.println("✅ All transactions deleted successfully: " + expectedDeletedTransactions);
        } else {
            List<String> missing = new ArrayList(expectedDeletedTransactions);
            missing.removeAll(actualDeletedTransactions);
            System.out.println("❌ Missing transactions: " + missing);
        }
    }
    public void validateDeletedTransactions(List<String> expectedDeletedTransactions) throws InterruptedException {
        common.clickElement("xpath", "//MenuItem[@Name='Audit']");
        common.clickElement("xpath", "//MenuItem[@Name='Void Transactiones']");
        Thread.sleep(2500);
        common.clickElement("xpath", "//Button[@Name='Submit']");
        Thread.sleep(2500);

        // Navigate to date option
        WebElement date = common.findWebElement("xpath", "//Header[@Name='Date']");
        Actions actions = new Actions(driver);
        actions.moveToElement(date).perform();
        common.clickElement("xpath", "//Button[@Name='Date ColumnFilterButton']");

        List<WebElement> today = common.findWebElements("xpath", "//Pane/List[@Name='Yesterday']/CheckBox[@Name='Today']");
        System.out.println("list of days: " + today.size());

        for (WebElement dateElement : today) {
            dateElement.click();
            System.out.println("Selected Date: " + dateElement.getText());
        }

        common.clickElement("xpath", "//Button[@Name='Close']");

        // Extract vouchers and their timestamps
        List<WebElement> voucherElements = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]/DataItem[contains(@Name,'Voucher No row')]");

        List<WebElement> timeElements = common.findWebElements("xpath", "//Table/*[@Name='Data Panel']/ListItem[contains(@Name,'Row')]/DataItem[contains(@Name,'Time row')]");

        System.out.println("Total Records Found: " + voucherElements.size());

        // Store only the latest timestamp for each voucher
        Map<String, String> latestTransactions = new HashMap<>();

        for (int i = 0; i < voucherElements.size(); i++) {
            String voucher = voucherElements.get(i).getText().trim().replaceAll("\\s+", " ");
            String time = timeElements.get(i).getText().trim();
            System.out.println("Extracted Voucher: " + voucher + " at " + time);
            // Compare timestamps and update if it's the latest
            if (!latestTransactions.containsKey(voucher) || time.compareTo(latestTransactions.get(voucher)) > 0) {
                latestTransactions.put(voucher, time);
            }
        }
        System.out.println("Final Extracted Transactions: " + latestTransactions);
        // Compare with expected transactions
        List<String> missing = new ArrayList<>(expectedDeletedTransactions);
        missing.removeAll(latestTransactions.keySet());
        if (missing.isEmpty()) {
            System.out.println("✅ All transactions deleted successfully.");
        } else {
            System.out.println("❌ Missing transactions: " + missing);
        }
    }
}
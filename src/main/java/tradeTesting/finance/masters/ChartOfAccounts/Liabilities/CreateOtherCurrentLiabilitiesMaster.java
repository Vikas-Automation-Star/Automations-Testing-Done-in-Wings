package tradeTesting.finance.masters.ChartOfAccounts.Liabilities;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class CreateOtherCurrentLiabilitiesMaster extends Masters {
        WindowsDriver driver;
        Common common;
        String dataFile;

        public CreateOtherCurrentLiabilitiesMaster(WindowsDriver driver,String file){
            super(driver);
            this.driver=driver;
            common=new Common(this.driver);
            dataFile=file;
        }

        public void createOtherCurrentLiabilitiesMasterTrade() throws InterruptedException, IOException, ParseException, AWTException {
            navigateToMastersWhen2Steps("Finance","Chart of Accounts");
            Thread.sleep(1000);
            WebElement assets = common.findWebElement("xpath", "//TreeItem[@Name='Balance Sheet']/TreeItem[@Name='Liabilities']");
            assets.click();
            assets.sendKeys(Keys.ARROW_RIGHT,Keys.ARROW_RIGHT,Keys.DOWN,Keys.ARROW_RIGHT);
            WebElement AllBranch = common.findWebElement("xpath", "//TreeItem[@Name='Other Current Liabilities']");
            Actions actions = new Actions(driver);
            actions.contextClick(AllBranch).perform();
            common.clickElement("name", "New Master");
            Thread.sleep(1500);
            common.inputText("xpath", "//Edit[@Name='New Account *']", common.getData(dataFile, "accountName") + common.getRandom());
            Thread.sleep(1000);
            common.inputText("xpath","//Edit[@Name='Account Code']",String.valueOf(common.getRandom()));
            common.findWebElement("xpath", "//Edit[@Name='Account Type *']").clear();
            common.inputText("xpath", "//Edit[@Name='Account Type *']",common.getData(dataFile,"accType"));
            common.inputText("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
            common.clickElement("xpath", "//Button[@Name='Save  Show Properties']");
            Thread.sleep(1000);
            //properties
            common.clickElement("xpath", "//Pane[@Name='Registration']/Button[@Name='...']");
            common.inputText("xpath", "//Window[@Name='Registration']/Pane/Pane/Edit[@Name='Party Reg Type *']", "registered");
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            common.inputText("xpath", "//Edit[@Name='GSTIN']", common.getData(dataFile, "gst"));
            common.clickElement("xpath", "//Edit[@Name='PAN']");
            common.clickElement("xpath", "//Button[@Name='Verify GSTIN']");
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            common.clickElement("name", "Ok");
            //save
            saveMasterTrade();
            //inactivate
            common.clickElement("xpath","//TreeItem[@Name='Provisions']");
            common.clickElement("xpath", "//TreeItem[@Name='Other Current Liabilities']");
            List<WebElement> listItems = common.findWebElements("xpath", "//Pane[@Name='Chart of Accounts']/Pane/Pane/Pane/Pane/Pane/List/*");
            boolean masterValidation=false;
            for (WebElement items : listItems){
                System.out.println(items.getText());
                if (items.getText().startsWith(common.getData(dataFile,"accountName"))){
                    masterValidation=true;
                    System.out.println("Master is created successfully - " + items.getText());
                    //inactivate it
                    items.click();
                    Actions actions1 =new Actions(driver);
                    actions1.contextClick(items).perform();
                    common.clickElement("xpath","//MenuItem[@Name='Inactivate']");
                    WebDriverWait wait=new WebDriverWait(driver,5);
                    WebElement okButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//Button[@Name='OK']")));
                    okButton.click();
                    System.out.println("Master Inactivated successfully");
                    break;
                }
            }
            if (!masterValidation) Assert.fail("Master is not validated");
        }
    }
package tradeTesting.finance.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class CreateAccountGroupsMaster extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateAccountGroupsMaster(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void createAccountGroupsMaster() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToMastersWhen2Steps("Finance","Account Groups");
        Thread.sleep(1000);
        WebElement AllBranch = common.findWebElement("xpath", "//TreeItem[@Name='Account Groups']/TreeItem[@Name='All Account Groups']");
        Actions actions = new Actions(driver);
        actions.contextClick(AllBranch).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(1000);
        common.inputText("xpath", "//Edit[@Name='New Account Group *']", common.getData(dataFile, "name") + common.getRandom());
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Save']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Window/Button[@Name='OK']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Cancel']");
        //inactive
        common.clickElement("xpath","//TreeItem[@Name='Account Groups']");
        common.clickElement("xpath","//TreeItem[@Name='All Account Groups']");
        List<WebElement> listItems = common.findWebElements("xpath", "//Pane[@Name='Account Groups']/Pane/Pane/Pane/Pane/Pane/List/*");
        boolean masterValidation=false;
        for (WebElement items : listItems){
            System.out.println(items.getText());
            if (items.getText().startsWith(common.getData(dataFile,"name"))){
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
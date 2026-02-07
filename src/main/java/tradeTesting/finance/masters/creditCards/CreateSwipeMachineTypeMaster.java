package tradeTesting.finance.masters.creditCards;

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
import java.io.IOException;
import java.util.List;

public class CreateSwipeMachineTypeMaster extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateSwipeMachineTypeMaster(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void createSwipeMachineTypeMaster() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Finance","Credit Cards","Swipe Machine Types");
        Thread.sleep(1000);
        WebElement AllBranch = common.findWebElement("xpath", "//TreeItem[@Name='Swipe Machine Types']/TreeItem[@Name='All Swipe Machine Types']");
        Actions actions = new Actions(driver);
        actions.contextClick(AllBranch).perform();
        common.clickElement("name", "New Master");
        Thread.sleep(1000);
        common.inputText("xpath", "//Edit[@Name='New Swipe Machine Type *']", common.getData(dataFile, "accountName") + common.getRandom());
        Thread.sleep(1000);
        common.inputText("xpath", "//Edit[@Name='Credit Card Company']", common.getData(dataFile, "creditCardName"));
        Thread.sleep(1000);
        common.clickElement("xpath", "//Text[@Name='Swipe Type Property']/following-sibling::Button[@Name='...']");
        Thread.sleep(1000);
        common.inputText("xpath", "//Edit[@Name='Swipe Type Row 0, Not sorted.']", common.getData(dataFile, "swipeTypeName"));
        Thread.sleep(1000);
        common.inputText("xpath", "//Edit[@Name='Discount Row 0, Not sorted.']", common.getData(dataFile, "discount"));
        Thread.sleep(1000);
        common.clickElement("xpath","//Button[@Name='Ok']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Save']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Window/Button[@Name='OK']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Pane/Button[@Name='Cancel']");
        //inactive
        common.clickElement("xpath","//TreeItem[@Name='Swipe Machine Types']");
        common.clickElement("xpath","//TreeItem[@Name='All Swipe Machine Types']");
        List<WebElement> listItems = common.findWebElements("xpath", "//Pane[@Name='Swipe Machine Types']/Pane/Pane/Pane/Pane/Pane/List/*");
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
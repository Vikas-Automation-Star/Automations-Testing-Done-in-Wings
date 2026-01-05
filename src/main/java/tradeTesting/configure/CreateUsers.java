package tradeTesting.configure;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class CreateUsers extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateUsers(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createUsers() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Configure","User Rights","Users");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Simple Users']/TreeItem[@Name='All Simple Users']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Master *']", common.getData(dataFile, "newUser") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Master *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        inputTextWithValidation("xpath", "//Edit[@Name='Voucher Series']", common.getData(dataFile, "voucherSeries"));
        common.clickElement("xpath","//CheckBox[@Name='Restrict Editing']");
        inputTextWithValidation("xpath", "//Edit[@Name='No Of Days']", common.getData(dataFile, "noOfDays"));
        common.clickElement("xpath","//CheckBox[@Name='Is Administrator']");
        common.clickElement("xpath","//Text[@Name='User App Extension']/following-sibling::Button[@Name='...']");
        common.inputText("xpath","//Edit[@Name='App ExtensionName Row 0, Not sorted.']","appExtensionName");
        common.clickElement("xpath","//Button[@Name='Ok']");
        common.clickElement("xpath","//Text[@Name='User Role Applicable Sales Executives']/following-sibling::Button[@Name='...']");
        common.inputText("xpath","//Edit[@Name='UserRole Applicable Sales Executives Row 0, Not sorted.']","salesExecutive");
        common.clickElement("xpath","//Button[@Name='Ok']");


        saveAfterMasterCreate();
        common.clickElement("xpath","//TreeItem[@Name='Simple Users']");
        common.clickElement("xpath","//TreeItem[@Name='All Simple Users']");
        List<WebElement> fetchMasters=common.findWebElements("xpath","//List//ListItem");
        boolean masterValidation=false;
        for (WebElement v:fetchMasters){
            if (v.getText().equals(master)) {
                masterValidation=true;
                System.out.println("masterName :"+v.getText());
                System.out.println("Master validated do Inactive");
                Actions actions=new Actions(driver);
                actions.contextClick(v).perform();
                common.clickElement("xpath","//MenuItem[@Name='Inactivate']");
                common.clickElement("xpath","//*/Button[@Name='OK']");
                System.out.println("Master inactive successfully");
                closeMaster("Users");
                break;
            }
        }
        if (!masterValidation) Assert.fail("Master is not validated");
        Thread.sleep(1000);
    }
}

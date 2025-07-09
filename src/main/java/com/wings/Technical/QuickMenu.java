package com.wings.Technical;

import com.wings.pages.TransactionsBaseClass;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class QuickMenu extends TransactionsBaseClass {
    WindowsDriver driver;
    Common common;
    String dataFile;


    public QuickMenu(WindowsDriver driver, String file) {
        super(driver);
        common=new Common(this.driver=driver);
        dataFile =file;
    }

    public void quickMenu() throws InterruptedException, IOException, ParseException {
        common.clickElement("xpath", "//MenuItem[@Name='Tools']");
        common.clickElement("xpath", "//MenuItem[@Name='Quick Menu']");
        Thread.sleep(1500);
        common.findWebElement("xpath", "//Window[@Name='Quick Menu']/Pane/ComboBox[@Name='Please select and press enter: ']").
                sendKeys(common.getData(dataFile,"quickMenu","searchedElement"), Keys.ENTER);
        Thread.sleep(1500);
        List<WebElement> childElements = common.findWebElements("xpath", "//Window[starts-with(@Name,'Wings Finance - PRO ')]/*");
        boolean flag=false;
        for (WebElement element : childElements) {
            System.out.println("gcmjyh" + element.getText());
            if (element.getText().contains(common.getData(dataFile,"quickMenu","searchedElement"))) {
                flag=true;
                System.out.println("Searched item is found");
               break;
            }
        }
        if (!flag) Assert.fail("Searched Item is not Found. Pls check Quick Menu");
    }
}

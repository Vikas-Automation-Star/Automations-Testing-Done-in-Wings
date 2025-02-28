package phase_1_TestCases;

import com.sun.source.tree.AssertTree;
import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ControllingWithFeatureManagement extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    ControllingWithFeatureManagement(WindowsDriver driver, String dFile) {
        super(driver);
        this.driver = driver;
        dataFile = dFile;
        common = new Common(driver);
    }

    public void menuItemsFeatureManagementPositive() throws InterruptedException {
        common.clickElement("xpath", "//TabItem[@Name='Configure']");
        common.clickElement("xpath", "//Text[@Name='Select Modules']");
        List<String> menus = new ArrayList<>();
        String finance = checkboxSelection("//CheckBox[@Name='Finance']");
        menus.add(finance);
        String taxes = checkboxSelection("//CheckBox[@Name='Taxes']");
        menus.add(taxes);
        String general = checkboxSelection("//Pane[@Name='Finance']/CheckBox[@Name='General']");
        menus.add(general);
        String purchase = checkboxSelection("//CheckBox[@Name='Purchases']");
        menus.add(purchase);
        String sales = checkboxSelection("//CheckBox[@Name='Sales']");
        menus.add(sales);
        String inventory = checkboxSelection("//CheckBox[@Name='Inventory']");
        menus.add(inventory);
        String production = checkboxSelection("//CheckBox[@Name='Production']");
        menus.add(production);
        String general1 = checkboxSelection("//Pane[@Name='General']/Pane/CheckBox[@Name='General']");
        menus.add(general1);
        menus.remove(7);

//        menus.sort();
        saveProperties();
        System.out.println("menusSize :" + menus.size());
        System.out.println("menus :" + menus);
        List<String> validateMenus = new ArrayList<>();
//        List<WebElement> checkMenus =common.findWebElements("xpath","//MenuBar/MenuItem");
//        System.out.println("validateMenusSize :"+ checkMenus.size());
        List<WebElement> checkMenus = common.findWebElements("xpath", "//Pane[@Name='Cost Centres']/Text/*");
        System.out.println("validateMenusSize :" + checkMenus.size());
        for (WebElement v : checkMenus) {
            String allMenus = v.getText();
//            System.out.println("allMenus :"+allMenus);
            validateMenus.add(allMenus);
        }
        validateMenus.remove(0);
        System.out.println("validateMenus :" + validateMenus);
        Assert.assertTrue(menus.containsAll(validateMenus), "both lists are not matched");
    }

    public void menuItemsNegativeFeatureManagementNegative() throws InterruptedException {
        common.clickElement("xpath", "//TabItem[@Name='Configure']");
        common.clickElement("xpath", "//Text[@Name='Select Modules']");
        List<String> menus = new ArrayList<>();
        String finance = uncheckCheckBox("//CheckBox[@Name='Finance']");
        menus.add(finance);
        String taxes = uncheckCheckBox("//CheckBox[@Name='Taxes']");
        menus.add(taxes);
        String general = uncheckCheckBox("//CheckBox[@Name='General']");
        menus.add(general);
        String purchase = uncheckCheckBox("//CheckBox[@Name='Purchases']");
        menus.add(purchase);
        String sales = uncheckCheckBox("//CheckBox[@Name='Sales']");
        menus.add(sales);
        String inventory = uncheckCheckBox("//CheckBox[@Name='Inventory']");
        menus.add(inventory);
        String production = uncheckCheckBox("//CheckBox[@Name='Production']");
        menus.add(production);
        String general1 = uncheckCheckBox("//Pane[@Name='General']/Pane/CheckBox[@Name='General']");
        menus.add(general1);
        saveProperties();
        System.out.println("menusSize :" + menus.size());
        System.out.println("menus :" + menus);
//        List<String> validateMenus=new ArrayList<>();
//        List<WebElement> checkMenus =common.findWebElements("xpath","//Pane[@Name='Cost Centres']/Text/*");
//        System.out.println("validateMenusSize :"+ checkMenus.size());
//        for (WebElement v: checkMenus){
//            String allMenus=v.getText();
////            System.out.println("allMenus :"+allMenus);
//            validateMenus.add(allMenus);
//        }
//        validateMenus.remove(0);
//        System.out.println("validateMenus :"+validateMenus);
//        Assert.assertTrue(menus.equals(validateMenus),"both lists are not matched");
    }
}

package phase_1_TestCases;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.sort;

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

    public void validateMenuItemsPositive() throws InterruptedException {
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
        sort(menus);
        System.out.println("menusSize :" + menus.size());
        System.out.println("menus :" + menus);
        saveProperties();
        List<String> validateMenus = new ArrayList<>();
//        List<WebElement> checkMenus =common.findWebElements("xpath","//MenuBar/MenuItem");
//        System.out.println("validateMenusSize :"+ checkMenus.size());
        List<WebElement> checkMenus = common.findWebElements("xpath", "//Pane[@Name='Cost Centres']/Text/*");
        System.out.println("fetchedMenusSize :" + checkMenus.size());
        for (WebElement v : checkMenus) {
            String allMenus = v.getText();
//            System.out.println("allMenus :"+allMenus);
            validateMenus.add(allMenus);
        }
        validateMenus.remove(0);
        sort(validateMenus);
        System.out.println("validateMenusSize :" + validateMenus.size());
        System.out.println("validateMenus :" + validateMenus);
        Assert.assertTrue(menus.equals(validateMenus), "both lists are not matched");
    }
    public void validateMenuItemsNegative() throws InterruptedException {
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
        System.out.println("menusSize :" + menus.size());
        System.out.println("menus :" + menus);
        saveProperties();
        List<String> validateMenus=new ArrayList<>();
        List<WebElement> checkMenus =common.findWebElements("xpath","//Pane[@Name='Cost Centres']/Text/*");
        System.out.println("fetchedMenusSize :"+ checkMenus.size());
        for (WebElement v: checkMenus){
            String allMenus=v.getText();
            validateMenus.add(allMenus);
        }
        System.out.println("validateMenusSize :" + validateMenus.size());
        System.out.println("validateMenus :" + validateMenus);
        Assert.assertTrue(!validateMenus.contains(menus),"validateMenus contains Menus it should not like this");
    }
    public void validateTransactionFieldsPositive() throws InterruptedException {
        common.clickElement("xpath", "//TabItem[@Name='Configure']");
        common.clickElement("xpath","//Text[@Name='Sales']/*[@Name='Sales']");
        common.clickElement("xpath","//Button[@Name='Work Flow']");
        List<String> enableTransactionFields =new ArrayList<>();
        String salesEnquiries=checkboxSelection("//Pane[@Name='Sales Enquiries']/CheckBox[@Name='Sales Enquiries']");
        enableTransactionFields.add(salesEnquiries.replace("Sales ",""));
        String salesQuotations=checkboxSelection("//Pane[@Name='Sales Quotations']/CheckBox[@Name='Sales Quotations']");
        enableTransactionFields.add(salesQuotations.replace("Sales ",""));
        String salesOrders=checkboxSelection("//Pane[@Name='Sales Orders']/CheckBox[@Name='Sales Orders']");
        enableTransactionFields.add(salesOrders.replace("Sales ",""));
        String salesDeliveries=checkboxSelection("//Pane[@Name='Deliveries']/CheckBox[@Name='Deliveries']");
        enableTransactionFields.add(salesDeliveries.replace("Sales ",""));
        String salesInvoices=checkboxSelection("//Pane[@Name='Sales Invoices']/CheckBox[@Name='Sales Invoices']");
        enableTransactionFields.add(salesInvoices.replace("Sales ",""));
        System.out.println("enableTransactionFields :"+ enableTransactionFields);
        saveProperties();
        common.clickElement("xpath","//Button[@Name='Close']");
        common.clickElement("xpath","//MenuItem[@Name='Sales']");
        List<String> validateTransactionFields=new ArrayList<>();
        List<WebElement> menuFields=common.findWebElements("xpath","//Menu[@Name='Sales']/*");
        for (WebElement salesTransactionFields:menuFields){
            String fetchFields =salesTransactionFields.getText();
            validateTransactionFields.add(fetchFields);
        }
        System.out.println("validatedTransactionFields :"+validateTransactionFields);
        if (validateTransactionFields.containsAll(enableTransactionFields)) System.out.println("Validation Passed!");
        else System.out.println("Validation Failed Transaction fields not contained in SalesMenu!");
    }
    public void validateTransactionFieldsNegative() throws InterruptedException {
        common.clickElement("xpath", "//TabItem[@Name='Configure']");
        common.clickElement("xpath","//Text[@Name='Sales']/*[@Name='Sales']");
        common.clickElement("xpath","//Button[@Name='Work Flow']");
        List<String> enableTransactionFields =new ArrayList<>();
        String salesEnquiries=uncheckCheckBox("//Pane[@Name='Sales Enquiries']/CheckBox[@Name='Sales Enquiries']");
        enableTransactionFields.add(salesEnquiries.replace("Sales ",""));
        String salesQuotations=uncheckCheckBox("//Pane[@Name='Sales Quotations']/CheckBox[@Name='Sales Quotations']");
        enableTransactionFields.add(salesQuotations.replace("Sales ",""));
        String salesOrders=uncheckCheckBox("//Pane[@Name='Sales Orders']/CheckBox[@Name='Sales Orders']");
        enableTransactionFields.add(salesOrders.replace("Sales ",""));
        String salesDeliveries=uncheckCheckBox("//Pane[@Name='Deliveries']/CheckBox[@Name='Deliveries']");
        enableTransactionFields.add(salesDeliveries.replace("Sales ",""));
        String salesInvoices=uncheckCheckBox("//Pane[@Name='Sales Invoices']/CheckBox[@Name='Sales Invoices']");
        enableTransactionFields.add(salesInvoices.replace("Sales ",""));
        System.out.println("disableTransactionFields :"+ enableTransactionFields);
        saveProperties();
        common.clickElement("xpath","//Button[@Name='Close']");
        common.clickElement("xpath","//MenuItem[@Name='Sales']");
        List<String> validateTransactionFields=new ArrayList<>();
        List<WebElement> menuFields=common.findWebElements("xpath","//Menu[@Name='Sales']/*");
        for (WebElement salesTransactionFields:menuFields){
            String fetchFields =salesTransactionFields.getText();
            validateTransactionFields.add(fetchFields);
        }
        System.out.println("validatedTransactionFields :"+validateTransactionFields);
        if (!validateTransactionFields.containsAll(enableTransactionFields)) System.out.println("Validation Passed No transactionFields are present!");
        else System.out.println("Validation Failed transactionFields Present!");
    }

}

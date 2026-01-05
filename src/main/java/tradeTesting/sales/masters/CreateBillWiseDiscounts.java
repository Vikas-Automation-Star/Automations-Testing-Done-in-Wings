package tradeTesting.sales.masters;

import com.wings.pages.Masters;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class CreateBillWiseDiscounts extends Masters {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public CreateBillWiseDiscounts(WindowsDriver driver, String file) {
        super(driver);
        this.driver = driver;
        common = new Common(this.driver);
        dataFile = file;
    }

    public void createBillWiseDiscounts() throws InterruptedException, IOException, ParseException {
        navigateToMastersWhen3Steps("Sales","Promotion","Bill Wise Discounts");
        Thread.sleep(1500);
        createMaster("xpath", "//TreeItem[@Name='Bill Wise Discounts']/TreeItem[@Name='All Bill Wise Discounts']");
        Thread.sleep(3000);
        inputTextWithValidation("xpath", "//Edit[@Name='New Bill Wise Discounts *']", common.getData(dataFile, "newBillWiseDiscount") + common.getRandom());
        String master=common.findWebElement("xpath","//Edit[@Name='New Bill Wise Discounts *']").getText();
        inputTextWithValidation("xpath", "//Edit[@Name='Description']", common.getData(dataFile, "description"));
        inputTextWithValidation("xpath", "//Edit[@Name='From Date']", common.getData(dataFile, "fromDate"));
        inputTextWithValidation("xpath", "//Edit[@Name='To Date']", common.getData(dataFile, "toDate"));
//        common.clickElement("xpath","//CheckBox[@Name='Fixed Slab']");
        common.clickElement("xpath","//CheckBox[@Name='All Customers']");
        common.clickElement("xpath","//Button[@Name='Choose Customers']");
        common.clickElement("xpath","//RadioButton[@Name='Choose Customers']");
        common.clickElement("xpath","//Button[@Name='OK']");
        common.clickElement("xpath","//*[contains(@Name,'Row')]/CheckBox[@Name='Select Row 0']");
        common.clickElement("xpath","//*[contains(@Name,'Row')]/CheckBox[@Name='Select Row 1']");
        common.clickElement("xpath","//Button[@Name='OK']");
        common.clickElement("xpath","//Text[@Name='Bill Wise Slabs']/following-sibling ::Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='From Value * Row 0, Not sorted.']", common.getData(dataFile, "fromValue"));
        common.inputText("xpath", "//Edit[@Name='To Value Row 0, Not sorted.']", common.getData(dataFile, "toValue"));
        common.inputText("xpath", "//Edit[@Name='Discount Basis * Row 0, Not sorted.']", common.getData(dataFile, "discountBasis"));
        common.inputText("xpath", "//Edit[@Name='Discount * Row 0, Not sorted.']", common.getData(dataFile, "discount"));
        common.clickElement("xpath","//Button[@Name='AddlDiscDetails Row 0']");
        common.inputText("xpath", "//Edit[@Name='Value More Than Row 0, Not sorted.']", common.getData(dataFile, "valueMoreThan"));
        common.inputText("xpath", "//Edit[@Name='Discount Basis Row 0, Not sorted.']", common.getData(dataFile, "discountBasis1"));
        common.inputText("xpath", "//Edit[@Name='Discount Row 0, Not sorted.']", common.getData(dataFile, "discount1"));
        common.clickElement("xpath","//Button[@Name='Ok']");
        common.clickElement("xpath","//Button[@Name='Ok']");
        common.clickElement("xpath","//Text[@Name='Addl Disc Details']/following-sibling ::Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='Value More Than Row 0, Not sorted.']", common.getData(dataFile, "valueMoreThan"));
        common.inputText("xpath", "//Edit[@Name='Discount Basis Row 0, Not sorted.']", common.getData(dataFile, "discountBasis1"));
        common.inputText("xpath", "//Edit[@Name='Discount Row 0, Not sorted.']", common.getData(dataFile, "discount1"));
        common.clickElement("xpath","//Button[@Name='Ok']");
        common.clickElement("xpath","//Text[@Name='Appllicabl Cust Categories']/following-sibling ::Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='Customer Category Row 0, Not sorted.']", common.getData(dataFile, "customerCategories"));
        common.clickElement("xpath","//Button[@Name='Ok']");
        common.clickElement("xpath","//Text[@Name='Apllicable Cust Types']/following-sibling ::Button[@Name='...']");
        common.inputText("xpath", "//Edit[@Name='Customer Type Row 0, Not sorted.']", common.getData(dataFile, "customerType"));
        common.clickElement("xpath","//Button[@Name='Ok']");
        common.clickElement("xpath","//Text[@Name='Appllicable Customers']/following-sibling ::Button[@Name='...']");
//        common.inputText("xpath", "//Edit[@Name='Customer Row 0, Not sorted']", common.getData(dataFile, "customer"));
        common.clickElement("xpath","//Button[@Name='Ok']");



        saveAfterMasterCreate();
        validateMastersAndInactive("Bill Wise Discounts",master);
        Thread.sleep(1000);
    }
}

package TestCases;

import com.wings.pages.Transaction;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class SalesInvoicesExcludingGST extends Transaction {
    WindowsDriver driver;
    Common common;
    String dataFile;

    public SalesInvoicesExcludingGST(WindowsDriver driver, String file) {
        super(driver);
        common = new Common(this.driver = driver);
        dataFile = file;
    }

    public void salesInvoicesExcludingGST() throws InterruptedException, IOException, ParseException, AWTException {
        navigateToSalesInvoiceMenu();
        Thread.sleep(1000);
        lastTransactionName();
        //branch selection
        common.clickElement("xpath", "//Edit[@Name='Voucher Type']");
        selectOptionalMaster(common.getData(dataFile, "voucher"), "xpath", "//Edit[@Name='Voucher Type']");
        common.clickElement("xpath", "//Edit[@Name='Branch *']");
        selectAndValidateData(common.getData(dataFile, "branch"), "xpath", "//Edit[@Name='Branch *']");
        common.clickElement("xpath", "//Edit[@Name='Location *']");
        Thread.sleep(1000);
        common.clickElement("xpath", "//Edit[@Name='Cash/Party Code']");
        selectAndValidateData(common.getData(dataFile, "partyCode"), "xpath", "//Edit[@Name='Cash/Party Code']");
        Thread.sleep(1000);
        gstTransactionType("Registered Dealers");
        common.clickElement("xpath", "//Edit[@Name='Sales A/c Code']");
        common.clickElement("xpath", "//Edit[@Name='Sales Account']");
//        common.clickElement("xpath", "//CheckBox[@Name='Apply TCS']");
//        common.clickElement("xpath", "//Edit[@Name='TCS Trans Nature']");
        common.clickElement("xpath", "//Edit[@Name='Invoice Type']");
        Thread.sleep(1000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        common.clickElement("xpath", "//Edit[@Name='Price List']");
        selectAndValidateData(common.getData(dataFile, "priceList"), "xpath", "//Edit[@Name='Price List']");
        common.clickElement("xpath", "//Edit[@Name='Executive *']");
        selectOptionalMaster(common.getData(dataFile, "executive"), "xpath", "//Edit[@Name='Executive *']");
        common.clickElement("xpath", "//Edit[@Name='Remarks']");
        selectOptionalMaster(common.getData(dataFile, "remarks"), "xpath", "//Edit[@Name='Remarks']");
        //F3-Items
        common.clickElement("xpath","//Edit[@Name='Product Code Row 0, Not sorted.']");
        super.selectMaster(common.getData(dataFile,"Pcode"));
//        common.inputText("xpath", "//Edit[@Name='Product Code Row 0, Not sorted.']", common.getData(dataFile, "productCode"));
        common.clickElement("xpath", "//Edit[@Name='Quantity Row 0, Not sorted.']");
        common.inputText("xpath", "//Edit[@Name='Quantity Row 0, Not sorted.']", common.getData(dataFile,"quantity"));
        int offset = 700;
        common.sliderHandling("xpath", "//Table[@Name='Items']/*/Thumb[@Name='Position']", offset, 0);
        //save
        transactionSave();
//        lastTransactionName();
    }
}

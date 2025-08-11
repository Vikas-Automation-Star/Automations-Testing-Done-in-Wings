package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseEnquiries;
import com.wings.pages.purchase.transactions.PurchaseOrdersAgainstQuotation;
import com.wings.pages.purchase.transactions.PurchaseQuotationsAgainstEnquiries;
import com.wings.pages.purchase.transactions.PurchaseVouchersAgainstOrder;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPurchaseVouchersAgainstOrders {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    String dataFile = "./src/main/resources/menuItems/purchase/transactions/461617 - Purchase Enquiries-AC_PE_3.xlsx";
    String dataFile1 = "./src/main/resources/menuItems/purchase/transactions/461887 - Purchase Quotations against Enquiries-AC_PQAPE_1.xlsx";
    String dataFile2 = "./src/main/resources/menuItems/purchase/transactions/480465 - Purchase Orders against Quotations-AC_POAQ 1.xlsx";
    String dataFile3 = "./src/main/resources/menuItems/purchase/transactions/479082 - Purchase Vouchers against Orders-AC_PVAO_1.xlsx";


    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver= appLogin.login();
    }

    @Test
    public void purchaseVouchersAgainstOrders() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseEnquiries enquiries=new PurchaseEnquiries(driver,dataFile);
        String pe=enquiries.purchaseEnquires();
        appLogin.logout();
        driver= appLogin.login();
        PurchaseQuotationsAgainstEnquiries quotationsAgainstEnquiries = new PurchaseQuotationsAgainstEnquiries(driver, dataFile1);
        String pqae =quotationsAgainstEnquiries.purchaseQuotationsAgainstEnquiry(pe);
        appLogin.logout();
        driver= appLogin.login();
        PurchaseOrdersAgainstQuotation POAQ = new PurchaseOrdersAgainstQuotation(driver, dataFile2);
        String poaq=POAQ.purchaseOrdersAgainstQuotation(pqae);
        appLogin.logout();
        driver= appLogin.login();
        PurchaseVouchersAgainstOrder pvao = new PurchaseVouchersAgainstOrder(driver, dataFile3);
        pvao.purchaseVouchersAgainstOrder(poaq);
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}

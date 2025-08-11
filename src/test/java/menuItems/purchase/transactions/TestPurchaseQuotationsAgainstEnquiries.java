package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseEnquiries;
import com.wings.pages.purchase.transactions.PurchaseQuotationsAgainstEnquiries;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPurchaseQuotationsAgainstEnquiries {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    String dataFile = "./src/main/resources/menuItems/purchase/transactions/461617 - Purchase Enquiries-AC_PE_3.xlsx";
    String dataFile1 = "./src/main/resources/menuItems/purchase/transactions/461887 - Purchase Quotations against Enquiries-AC_PQAPE_1.xlsx";


    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver= appLogin.login();
    }

    @Test
    public void purchaseQuotationsAgainstEnquiries() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseEnquiries enquiries=new PurchaseEnquiries(driver,dataFile);
        String pe=enquiries.purchaseEnquires();
        PurchaseQuotationsAgainstEnquiries pqae = new PurchaseQuotationsAgainstEnquiries(driver, dataFile1);
        pqae.purchaseQuotationsAgainstEnquiry(pe);
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}

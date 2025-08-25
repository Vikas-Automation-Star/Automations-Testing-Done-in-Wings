package phase_1_TestCases;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesEnquiry;
import com.wings.pages.sales.transactions.SalesEnquiry_BulkDelete;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BulkDelete {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/salesEnquiry.json";
    List<String> voucherNumbers = new ArrayList<>();

    @BeforeTest
    public void beforeTest() throws Exception {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        SalesEnquiry_BulkDelete enquiryBulkDelete = new SalesEnquiry_BulkDelete(driver, dataFile);

        // Capture voucher numbers
        for (int i = 0; i < 2; i++) {
            String voucherNum = enquiryBulkDelete.salesEnquiry_bulkDelete();
            voucherNumbers.add(voucherNum);
        }
        // Store the voucher numbers for later use
        this.voucherNumbers = voucherNumbers;
    }

    @Test
    public void bulkDelete() throws Exception {
        BulkDeleteCode bulkDeleteCode=new BulkDeleteCode(driver,dataFile);
        bulkDeleteCode.bulkDelete(voucherNumbers);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
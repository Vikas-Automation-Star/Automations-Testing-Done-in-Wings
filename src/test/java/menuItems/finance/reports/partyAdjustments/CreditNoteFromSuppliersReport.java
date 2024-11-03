package menuItems.finance.reports.partyAdjustments;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.reports.partyAdjustments.CreditNoteFromSuppliersReportCode;
import java.awt.*;
import java.io.IOException;

public class CreditNoteFromSuppliersReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws  InterruptedException, IOException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void creditNoteFromSupplierReport() throws InterruptedException, AWTException {
        CreditNoteFromSuppliersReportCode noteFromSuppliersReportCode=new CreditNoteFromSuppliersReportCode(driver);
        noteFromSuppliersReportCode.creditNotefromSupplierReport();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

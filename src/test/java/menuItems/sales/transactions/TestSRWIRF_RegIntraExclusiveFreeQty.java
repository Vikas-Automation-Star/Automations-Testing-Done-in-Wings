package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesInvoiceFQ_RegIntraExclusive;
import com.wings.pages.sales.transactions.SRTWIR_RegIntraExclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSRWIRF_RegIntraExclusiveFreeQty {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/menuItems/Sales/Transactions/freeQuantitySITCExclusiveIntra_Reg.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void ExclusiveIntraTCSGST() throws IOException, ParseException, InterruptedException, AWTException {
        SalesInvoiceFQ_RegIntraExclusive tcsgstFreeQty=new SalesInvoiceFQ_RegIntraExclusive(driver,dataFile);
        SRTWIR_RegIntraExclusive exclusive=new SRTWIR_RegIntraExclusive(driver,dataFile);
        exclusive.RegExclusiveInvoiceReference(tcsgstFreeQty.intraStateExclusiveTCSGST_FreeQty());
//                exclusive.RegExclusiveInvoiceReference("SI43");

    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
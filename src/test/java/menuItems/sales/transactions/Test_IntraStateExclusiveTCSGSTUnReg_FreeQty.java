package menuItems.sales.transactions;
//need to execute, changes done 2
import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SRTWIR_UnRegIntraExclusive;
import com.wings.pages.sales.transactions.UnReg_IntraExclusiveTCSGST_FreeQty;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class Test_IntraStateExclusiveTCSGSTUnReg_FreeQty {

    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/TestCasesData/freeQuantitySITCExclusiveIntra_UnReg.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void UnRegFreeQtyIntra() throws IOException, ParseException, InterruptedException, AWTException {
        UnReg_IntraExclusiveTCSGST_FreeQty unRegFreeQty=new UnReg_IntraExclusiveTCSGST_FreeQty(driver,dataFile);
        SRTWIR_UnRegIntraExclusive unRegIntraExclusive=new SRTWIR_UnRegIntraExclusive(driver,dataFile);
        unRegIntraExclusive.UnRegExclusiveInvoiceReference("hi");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
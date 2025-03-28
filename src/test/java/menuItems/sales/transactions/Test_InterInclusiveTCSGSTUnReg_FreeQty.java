package menuItems.sales.transactions;
// modifies, need to execute -4 modify gst values
import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SRTWIR_UnRegInterInclusive;
import com.wings.pages.sales.transactions.UnReg_InterInclusiveTCSGST_FreeQty;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class Test_InterInclusiveTCSGSTUnReg_FreeQty {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/TestCasesData/freeQuantitySITCInclusiveInter_UnReg.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void InclusiveTCSGSTFreeQuantity() throws IOException, ParseException, InterruptedException, AWTException {
        UnReg_InterInclusiveTCSGST_FreeQty unRegInterInclusiveTCSGSTFreeQty=new UnReg_InterInclusiveTCSGST_FreeQty(driver,dataFile);
        SRTWIR_UnRegInterInclusive interInclusive=new SRTWIR_UnRegInterInclusive(driver,dataFile);
        interInclusive.UnRegInclusiveInvoiceReference(unRegInterInclusiveTCSGSTFreeQty.interStateInclusiveTCSGST_FreeQty());
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}

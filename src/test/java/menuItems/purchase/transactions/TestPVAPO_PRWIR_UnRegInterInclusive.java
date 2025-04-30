package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PVAPO_PRWIR_UnRegInterInclusive;
import com.wings.pages.purchase.transactions.PVAPO_UnRegInterInclusive;
import com.wings.pages.purchase.transactions.PurchaseOrder_UnRegInterInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPVAPO_PRWIR_UnRegInterInclusive {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/purchase/transactions/PO_MReceiptsAPO_PVAPO_UnRegInterInclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void UnReg_PO_InclusivePrice() throws InterruptedException, IOException, ParseException, AWTException {
        PurchaseOrder_UnRegInterInclusive unRegInclusive=new PurchaseOrder_UnRegInterInclusive(driver,file);
        PVAPO_UnRegInterInclusive pvapoInc=new PVAPO_UnRegInterInclusive(driver,file);
        String[] unRegInc=pvapoInc.pvapo_UnRegInterInclusive(unRegInclusive.unReg_PO_GSTInclusive()[1]);
        PVAPO_PRWIR_UnRegInterInclusive unRegInterPRWIR=new PVAPO_PRWIR_UnRegInterInclusive(driver,file);
        unRegInterPRWIR.pvapo_PRWIR_UnRegIntraInclusive(unRegInc[1]);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

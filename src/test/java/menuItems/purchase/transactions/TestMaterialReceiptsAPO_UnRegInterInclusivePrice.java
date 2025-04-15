package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.MaterialReceiptsAPO_UnRegInterInclusive;
import com.wings.pages.purchase.transactions.PurchaseOrder_UnRegInterInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestMaterialReceiptsAPO_UnRegInterInclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/purchase/transactions/PO_MReceiptsAPO_PVAPO_UnRegInterInclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void UnReg_MRAO_InclusivePrice() throws InterruptedException, NoSuchMethodException, IOException, ParseException, AWTException {
        PurchaseOrder_UnRegInterInclusive unRegInclusive=new PurchaseOrder_UnRegInterInclusive(driver,file);
        String[] unRegInc=unRegInclusive.unReg_PO_GSTInclusive();
        MaterialReceiptsAPO_UnRegInterInclusive receipts=new MaterialReceiptsAPO_UnRegInterInclusive(driver,file);
        receipts.unReg_MRAO_InclusiveGST(unRegInc[1]);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

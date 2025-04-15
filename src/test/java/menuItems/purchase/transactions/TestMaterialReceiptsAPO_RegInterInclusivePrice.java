package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.MaterialReceiptsAPO_RegInterInclusive;
import com.wings.pages.purchase.transactions.PurchaseOrder_RegInterInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestMaterialReceiptsAPO_RegInterInclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/purchase/transactions/PO_MReceiptsAPO_PVAPO_RegInterInclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void reg_PurchaseOrder_GST_InclusivePrice() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseOrder_RegInterInclusive orderGstInclusive=new PurchaseOrder_RegInterInclusive(driver,file);
        String[] inclusivePO=orderGstInclusive.Reg_PO_GSTInclusive();
        MaterialReceiptsAPO_RegInterInclusive mrao=new MaterialReceiptsAPO_RegInterInclusive(driver,file);
        mrao.mroa_InclusiveGST(inclusivePO[1]);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

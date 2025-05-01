package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.MaterialReceiptsAPO_RegInterInclusive;
import com.wings.pages.purchase.transactions.PVAMR_RegInterInclusive;
import com.wings.pages.purchase.transactions.PVAPO_Reg_InterInclusive;
import com.wings.pages.purchase.transactions.PurchaseOrder_RegInterInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPVAMR_RegInterInclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/purchase/transactions/PO_MReceiptsAPO_PVAPO_RegInterInclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void reg_PVAO_RCM_GST_InclusivePrice() throws IOException, ParseException, InterruptedException, NoSuchMethodException, AWTException {
        PurchaseOrder_RegInterInclusive po = new PurchaseOrder_RegInterInclusive(driver, file);
        MaterialReceiptsAPO_RegInterInclusive pvao = new MaterialReceiptsAPO_RegInterInclusive(driver, file);
        String [] inclusiveMRPO=pvao.mroa_InclusiveGST(po.Reg_PO_GSTInclusive()[1]);
        PVAMR_RegInterInclusive pvamr=new PVAMR_RegInterInclusive(driver,file);
        String[] inclusivePVAMR=pvamr.pvamr_RegInterInclusive(inclusiveMRPO[1]);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

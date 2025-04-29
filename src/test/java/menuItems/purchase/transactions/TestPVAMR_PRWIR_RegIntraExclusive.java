package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.MaterialReceiptsAPO_RegIntraExclusive;
import com.wings.pages.purchase.transactions.PVAMR_PRWIR_RegIntraExclusive;
import com.wings.pages.purchase.transactions.PVAMR_RegIntraExclusive;
import com.wings.pages.purchase.transactions.PurchaseOrder_RegIntraExclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestPVAMR_PRWIR_RegIntraExclusive {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/purchase/transactions/PO_MReceiptsAPO_PVAPO_RegIntraExclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void Reg_PO_MRAPO_GST_ExclusivePrice() throws IOException, ParseException, InterruptedException, AWTException {
        PurchaseOrder_RegIntraExclusive pr = new PurchaseOrder_RegIntraExclusive(driver, file);
        String[] exclusivePO =pr.reg_PV_GSTExclusive();
        MaterialReceiptsAPO_RegIntraExclusive mrao=new MaterialReceiptsAPO_RegIntraExclusive(driver,file);
        String[] exclusiveMRAO=mrao.MRAO_ExclusiveGST(exclusivePO[1]);
        PVAMR_RegIntraExclusive pvamr=new PVAMR_RegIntraExclusive(driver,file);
        String[] inclusivePVAMR=pvamr.pvamr_RegIntraExclusive(exclusiveMRAO[1]);
        PVAMR_PRWIR_RegIntraExclusive prwir_regIntraExclusive=new PVAMR_PRWIR_RegIntraExclusive(driver,file);
        prwir_regIntraExclusive.regIntraExcluisveWithInvoiceRef_PVAMR(inclusivePVAMR[1]);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
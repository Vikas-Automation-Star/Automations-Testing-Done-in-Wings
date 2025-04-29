package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.MaterialReceiptsAPO_RegInterInclusive;
import com.wings.pages.purchase.transactions.PVAMR_PRWIR_RegInterInclusive;
import com.wings.pages.purchase.transactions.PVAMR_RegInterInclusive;
import com.wings.pages.purchase.transactions.PurchaseOrder_RegInterInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestPVAMR_PRWIR_RegInterInclusive {
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
        PVAMR_PRWIR_RegInterInclusive prwir_regInterInclusive=new PVAMR_PRWIR_RegInterInclusive(driver,file);
        prwir_regInterInclusive.regInterInclusiveWithInvoiceRef_PVAMR(inclusivePVAMR[1]);
//        prwir_regInterInclusive.regInterInclusiveWithInvoiceRef_PVAMR("PVAMR4");
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
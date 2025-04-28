package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.MaterialReceiptsAPO_RegIntraExclusive;
import com.wings.pages.purchase.transactions.PVAMR_RegIntraExclusive;
import com.wings.pages.purchase.transactions.PurchaseOrder_RegIntraExclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPVAMR_RegIntraExclusivePrice {
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
        System.out.println("first Voucher " + exclusivePO[0]); //without space
        System.out.println("secVoucher " + exclusivePO[1]); //with space

        MaterialReceiptsAPO_RegIntraExclusive mrao=new MaterialReceiptsAPO_RegIntraExclusive(driver,file);
        String[] exclusiveMRAO=mrao.MRAO_ExclusiveGST(exclusivePO[1]);
        System.out.println("receipts Voucher " + exclusiveMRAO[0]); //without space
        System.out.println("receipts secVoucher " + exclusiveMRAO[1]); //with space

        PVAMR_RegIntraExclusive pvamr=new PVAMR_RegIntraExclusive(driver,file);
        String[] inclusivePVAMR=pvamr.pvamr_RegIntraExclusive(exclusiveMRAO[1]);
        System.out.println("PVAMR Voucher " + inclusivePVAMR[0]); //without space
        System.out.println("PVAMR secVoucher " + inclusivePVAMR[1]); //with space
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PVAPO_Reg_InterInclusive;
import com.wings.pages.purchase.transactions.PurchaseOrder_RegInterInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPVAPO_Reg_InterInclusivePrice {
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
        PurchaseOrder_RegInterInclusive pr = new PurchaseOrder_RegInterInclusive(driver, file);
        PVAPO_Reg_InterInclusive pvao = new PVAPO_Reg_InterInclusive(driver, file);
        pvao.pvopo_Reg_InterInclusive(pr.Reg_PO_GSTInclusive()[1]);
    }
    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

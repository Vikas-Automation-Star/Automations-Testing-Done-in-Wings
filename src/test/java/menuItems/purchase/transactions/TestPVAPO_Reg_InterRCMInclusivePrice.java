package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PVAPO_RegInterRCMGSTInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestPVAPO_Reg_InterRCMInclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/purchase/transactions/PO_MReceiptsAPO_PVAPO_RegInterInclusive.json";
    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void reg_PVAO_RCM_GST_ExclusivePrice() throws IOException, ParseException, InterruptedException, NoSuchMethodException, AWTException {
//        Reg_Inter_PO_GSTInclusive pr = new Reg_Inter_PO_GSTInclusive(driver, file);
//        String[] inclusiveRCM=pr.Reg_PO_GSTInclusive();
        PVAPO_RegInterRCMGSTInclusive pvao=new PVAPO_RegInterRCMGSTInclusive(driver,file);
        pvao.reg_Inter_PVAO_Inclusive("PO 15");
    }

    @BeforeTest
    public void afterTest() throws IOException, InterruptedException {
//        appLogin.logout();
    }
}

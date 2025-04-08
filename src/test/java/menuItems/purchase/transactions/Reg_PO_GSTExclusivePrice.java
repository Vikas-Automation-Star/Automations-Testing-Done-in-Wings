package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.MRAO_ExclusiveGST;
import com.wings.pages.purchase.transactions.Reg_PO_GSTExclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class Reg_PO_GSTExclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/purchase/transactions/Reg_PO_GSTExclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void Reg_purchaseOrders_GST_Exclusive() throws IOException, ParseException, InterruptedException, NoSuchMethodException, AWTException {
        Reg_PO_GSTExclusive pr = new Reg_PO_GSTExclusive(driver, file);
        pr.reg_PV_GSTExclusive();
        MRAO_ExclusiveGST mrao=new MRAO_ExclusiveGST(driver,file);
        mrao.MRAO_ExclusiveGST();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

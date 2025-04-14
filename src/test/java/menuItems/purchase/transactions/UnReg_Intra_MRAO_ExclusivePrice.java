package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.UnReg_Intra_MRAO_ExclusiveGST;
import com.wings.pages.purchase.transactions.UnReg_Intra_PO_GSTExclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class UnReg_Intra_MRAO_ExclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/purchase/transactions/UnReg_Intra_PO_GSTExclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void UnReg_MRAO_ExclusivePrice() throws IOException, ParseException, InterruptedException, NoSuchMethodException, AWTException {
        UnReg_Intra_PO_GSTExclusive orders=new UnReg_Intra_PO_GSTExclusive(driver,file);
        orders.unReg_PO_GSTExclusive();
        UnReg_Intra_MRAO_ExclusiveGST receipts=new UnReg_Intra_MRAO_ExclusiveGST(driver,file);
        receipts.unReg_MRAO_ExclusiveGST();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

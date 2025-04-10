package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.UnReg_MRAO_InclusiveGST;
import com.wings.pages.purchase.transactions.UnReg_PO_GSTInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class UnReg_PO_GSTInclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/purchase/transactions/UnReg_PO_GSTInclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void UnReg_MRAO_InclusivePrice() throws InterruptedException, NoSuchMethodException, IOException, ParseException, AWTException {
        UnReg_PO_GSTInclusive unRegInclusive=new UnReg_PO_GSTInclusive(driver,file);
        unRegInclusive.unReg_PO_GSTInclusive();
        UnReg_MRAO_InclusiveGST receipts=new UnReg_MRAO_InclusiveGST(driver,file);
        receipts.unReg_MRAO_InclusiveGST();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}

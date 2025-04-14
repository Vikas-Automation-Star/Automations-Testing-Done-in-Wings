package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.Reg_Intra_PO_GSTExclusive;
import com.wings.pages.purchase.transactions.Reg_Intra_PVAO_Exclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class Reg_Intra_PVAO_ExclusivePrice {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/purchase/transactions/Reg_Intra_PO_GSTExclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void reg_PVAO_GST_ExclusivePrice() throws IOException, ParseException, InterruptedException, NoSuchMethodException, AWTException {
        Reg_Intra_PO_GSTExclusive pr = new Reg_Intra_PO_GSTExclusive(driver, file);
        pr.reg_PV_GSTExclusive();
        Reg_Intra_PVAO_Exclusive pvoa=new Reg_Intra_PVAO_Exclusive(driver,file);
        pvoa.reg_PVAO_Exclusive();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}

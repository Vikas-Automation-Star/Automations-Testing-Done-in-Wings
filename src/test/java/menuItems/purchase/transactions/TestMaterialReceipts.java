package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.MaterialReceipt;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestMaterialReceipts {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String file = "./src/main/resources/menuItems/purchase/transactions/MaterialReceipts.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(file,"MaterialReceipts","userName"),common.getData(file,"MaterialReceipts","password"));
    }
    @Test
    public void MaterialReceipts() throws IOException, ParseException, InterruptedException, AWTException {
        MaterialReceipt materialReceipt = new MaterialReceipt(driver, file);
        materialReceipt.materialReceipt();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

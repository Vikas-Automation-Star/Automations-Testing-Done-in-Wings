package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.MaterialReceipt;
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
    String file = "./src/main/resources/menuItems/purchase/transactions/473342 - Material Receipts-AC_MR_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
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
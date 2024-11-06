package menuItems.purchase.masters;;

import com.wings.pages.purchase.masters.Supplier;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class SuppliersMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="./src/main/resources/MenuItems/purchase/masters/suppliers.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();;
    }

    @Test
    public void suppliersMaster() throws IOException, ParseException, InterruptedException, AWTException {
        Supplier supplier=new Supplier(driver,file);
        supplier.createSupplier();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

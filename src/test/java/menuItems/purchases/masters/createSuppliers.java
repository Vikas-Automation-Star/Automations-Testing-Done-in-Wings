package menuItems.purchases.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.purchases.masters.Suppliers;
import java.awt.*;
import java.io.IOException;

public class createSuppliers {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="./src/main/resources/menuItems/purchases/masters/supplier.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void newCust() throws InterruptedException, IOException, ParseException, AWTException {
        Suppliers suppliers=new Suppliers(driver,file);
        suppliers.suppliers();
        suppliers.newSupplier();
    }

        @AfterTest
        public void afterTest(){
            appLogin.logout();
        }
}

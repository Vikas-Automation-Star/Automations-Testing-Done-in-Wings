package menuItems.inventory.masters.product.attributes;

import com.wings.pages.AppLogin;
import com.wings.pages.inventory.masters.product.attributes.ProductSubClass;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestProductSubClass {

        WindowsDriver driver;
        AppLogin appLogin = new AppLogin();
        String file = "./src/main/resources/menuItems/inventory/masters/productSubClass.json";

        @BeforeTest
        public void beforeTest() throws InterruptedException, IOException, ParseException {
            driver = appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
        }

        @Test
        public void productSubClass() throws IOException, ParseException, InterruptedException, AWTException {
            ProductSubClass productSubClass=new ProductSubClass(driver,file);
            productSubClass.productSubClasses();

        }

        @AfterTest
        public void afterTest() throws IOException {
            appLogin.logout();
        }
    }
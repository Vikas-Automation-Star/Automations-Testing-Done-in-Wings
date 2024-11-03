package menuItems.sales.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.masters.ProductDiscountGroup;

import java.awt.*;
import java.io.IOException;

public class ProductDiscountMaster {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file="./src/main/resources/menuItems/Sales/Masters/productDiscountGroup.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void productDiscount() throws InterruptedException, IOException, ParseException, AWTException {
        ProductDiscountGroup productDiscountGroup=new ProductDiscountGroup(driver,file);
        productDiscountGroup.productGroup();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

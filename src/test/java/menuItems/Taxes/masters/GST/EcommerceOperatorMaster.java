package menuItems.Taxes.masters.GST;

import com.wings.pages.taxes.masters.GST.EcommerceOperator;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class EcommerceOperatorMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="./src/main/resources/MenuItems/Taxes/masters/gst/EcommerceOperator.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void EcommerceOperatorMaster() throws InterruptedException, IOException, ParseException, AWTException {
        EcommerceOperator eo=new EcommerceOperator(driver,file);
        eo.ecommerceOperator();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

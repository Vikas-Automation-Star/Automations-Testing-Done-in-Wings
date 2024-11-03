package menuItems.sales.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.masters.PartyDiscountGroup;

import java.awt.*;
import java.io.IOException;

public class PartyDiscountGroupMaster {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file="./src/main/resources/menuItems/Sales/Masters/partyDiscount.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void partyDiscount() throws InterruptedException, IOException, ParseException, AWTException {
        PartyDiscountGroup discountGroup=new PartyDiscountGroup(driver,file);
        discountGroup.partyGroup();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

package menuItems.purchase.masters;;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.masters.PriceList;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class PriceListMaster {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="./src/main/resources/MenuItems/purchase/masters/priceList.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test PriceList");
    }

    @Test
    public void priceList() throws IOException, ParseException, InterruptedException, AWTException {
        PriceList pl=new PriceList(driver,file);
        pl.createpriceList();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
        Allure.step("After test PriceList");
    }

}

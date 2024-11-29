package menuItems.inventory.transactions;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.transactions.InterLocationTransfers;
import java.awt.*;
import java.io.IOException;

public class InterLocatonTransfersTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file="./src/main/resources/menuItems/inventory/transactions/interLocationTransfer.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test - Inter Location Transfers");
    }

    @Test
    public void interLocationTransfer() throws InterruptedException, AWTException, IOException, ParseException {
        InterLocationTransfers locationTransfers=new InterLocationTransfers(driver,file);
        locationTransfers.locationTransfer();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
        Allure.step("After Test - Inter Location Transfers");
    }
}
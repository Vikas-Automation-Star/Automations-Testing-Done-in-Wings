package menuItems.sales.transactions;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesTargetExecutiveWise;
import java.awt.*;
import java.io.IOException;

public class SalesTargetExectiveWiseTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/Sales/Transactions/salesTargetExecWise.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test Sales Target Executive Wise");
    }

    @Test
    public void salesTarget() throws IOException, ParseException, InterruptedException, AWTException {
        SalesTargetExecutiveWise targets=new SalesTargetExecutiveWise(driver,file);
        targets.salesTarget();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
        Allure.step("After Test Sales Target Executive Wise");
    }
}
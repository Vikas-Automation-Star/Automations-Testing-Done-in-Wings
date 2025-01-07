package menuItems.Taxes.transactions.GST;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.transactions.GST.Gstr1MonthEnd;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class Gstr1MonthEndTransaction {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="./src/main/resources/MenuItems/Taxes/transactions/GST/Gstr1MonthEnd.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();;
    }

    @Test
    public void Gstr1MonthEndTransaction() throws InterruptedException, IOException, ParseException, AWTException {
        Gstr1MonthEnd gstr1MonthEnd=new Gstr1MonthEnd(driver,file);
        gstr1MonthEnd.Gstr1MonthEnd();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

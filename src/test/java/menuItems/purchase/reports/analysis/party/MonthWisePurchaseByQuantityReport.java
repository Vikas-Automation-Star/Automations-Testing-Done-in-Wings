package menuItems.purchase.reports.analysis.party;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.reports.analysis.party.MonthWisePurchaseByQuantity;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class MonthWisePurchaseByQuantityReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }
    @Test
    public void monthWiseSalesByQuantity() throws InterruptedException {
        MonthWisePurchaseByQuantity mwpbv=new MonthWisePurchaseByQuantity(driver);
        mwpbv.monthWisePurchaseByQuantity();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}

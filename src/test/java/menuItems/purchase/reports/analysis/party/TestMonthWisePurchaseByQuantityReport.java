package menuItems.purchase.reports.analysis.party;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.reports.analysis.party.MonthWisePurchaseByQuantity;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestMonthWisePurchaseByQuantityReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String file = "./src/main/resources/menuItems/purchase/reports/party/monthAndYearWiseParty.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(file,"monthWisePurchaseByQuantity","userName"),common.getData(file,"monthWisePurchaseByQuantity","password"));
    }

    @Test
    public void monthWisePurchaseByQuantity() throws InterruptedException, IOException, ParseException {
        MonthWisePurchaseByQuantity mwpbv = new MonthWisePurchaseByQuantity(driver,file);
        mwpbv.monthWisePurchaseByQuantity();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

package menuItems.purchase.reports.analysis.party;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.reports.analysis.party.YearWisePurchaseByValue;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestYearWisePurchaseByValueReport {
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
    public void yearWisePurchaseByValue() throws InterruptedException, IOException, ParseException {
        YearWisePurchaseByValue ywpbv = new YearWisePurchaseByValue(driver,file);
        ywpbv.yearWisePurchaseByValue();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

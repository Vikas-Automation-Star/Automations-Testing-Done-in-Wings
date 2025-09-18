package menuItems.sales.masters;

import org.testng.annotations.AfterTest;
import com.wings.pages.sales.masters.PriceList;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import java.io.IOException;

public class TestSalesPriceListMaster {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/menuItems/Sales/Masters/salesPriceList.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void newPriceList() throws InterruptedException, IOException, ParseException {
        PriceList priceList = new PriceList(driver, file);
        priceList.priceList();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
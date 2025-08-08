package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.Deliveries;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class DeliveriesTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/476979 - Deliveries-AC.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void deliveries() throws IOException, InterruptedException, ParseException, AWTException {
        Deliveries deliveries = new Deliveries(driver, dataFile);
        deliveries.salesDeliveries();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
package menuItems.sales.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.Deliveries;
import java.io.IOException;

public class DeliveriesTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/menuItems/Sales/Transactions/deliveries.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void deliveries() throws IOException, InterruptedException, ParseException {
        Deliveries deliveries=new Deliveries(driver,dataFile);
        deliveries.salesDeliveries();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }

}

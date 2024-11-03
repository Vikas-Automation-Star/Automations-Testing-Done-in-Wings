package menuItems.sales.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.DeliveryReturns;

import java.io.IOException;

public class DeliveryReturnsTransactions {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/menuItems/Sales/Transactions/deliveryReturns.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void deliveryReturns() throws IOException, InterruptedException, ParseException {
        DeliveryReturns deliveryReturns=new DeliveryReturns(driver,dataFile);
        deliveryReturns.deliveryreturns();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

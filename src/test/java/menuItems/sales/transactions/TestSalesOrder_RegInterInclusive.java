package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesOrder_RegInterInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesOrder_RegInterInclusive {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/menuItems/Sales/Transactions/deliveriesAgainstOrder_RegInterInclusive.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void regInterInclusive() throws IOException, ParseException, InterruptedException, AWTException {
        SalesOrder_RegInterInclusive regInterInclusive=new SalesOrder_RegInterInclusive(driver,dataFile);
        regInterInclusive.interInclusiveReg();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

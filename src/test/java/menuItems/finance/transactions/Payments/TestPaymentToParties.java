package menuItems.finance.transactions.Payments;

import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Payments.PaymentToParties;

import java.awt.*;
import java.io.IOException;

public class TestPaymentToParties {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String file = "./src/main/resources/menuItems/finance/transaction/paymentToParties.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(file,"paymentsToParties","userName"),common.getData(file,"paymentsToParties","password"));
    }

    @Test
    public void paymentToParties() throws InterruptedException, AWTException, IOException, ParseException {
        PaymentToParties paymentToParties = new PaymentToParties(driver, file);
        paymentToParties.paymentToParty();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
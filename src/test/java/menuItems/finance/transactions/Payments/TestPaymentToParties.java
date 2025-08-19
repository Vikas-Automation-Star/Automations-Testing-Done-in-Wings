package menuItems.finance.transactions.Payments;

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
    String file = "./src/main/resources/menuItems/finance/transaction/461060 - Payments to Parties-AC_PPAY_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void paymentToParties() throws InterruptedException, IOException, ParseException {
        PaymentToParties paymentToParties = new PaymentToParties(driver, file);
        paymentToParties.paymentToParty();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
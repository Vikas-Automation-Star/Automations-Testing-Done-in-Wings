package menuItems.finance.transactions.Payments;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Payments.PaymentToParties;
import java.io.IOException;

public class TestPaymentToParties {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_PAYMENT_TO_PARTIES="./output/temp_api_request_bodies/paymentToParties.json";
    private static final String API_RESPONSE_PAYMENT_TO_PARTIES="./output/api_responses/paymentToParties.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/finance/transaction/461060 - Payments to Parties-AC_PPAY_1_Output.xls";

    String file = "./src/main/resources/menuItems/finance/transaction/461060 - Payments to Parties-AC_PPAY_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void paymentToParties() throws Exception {
        PaymentToParties paymentToParties = new PaymentToParties(driver, file);
        paymentToParties.paymentToParty(TEMP_API_BODY_PAYMENT_TO_PARTIES,API_RESPONSE_PAYMENT_TO_PARTIES,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
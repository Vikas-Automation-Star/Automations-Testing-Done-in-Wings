package menuItems.finance.transactions.Payments;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Payments.CashPayments;
import java.awt.*;
import java.io.IOException;

public class CashPaymentsTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/cashPayment.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test - Cash Payments");
    }

    @Test
    public void cashPayment() throws InterruptedException, AWTException, IOException, ParseException {
        CashPayments cashPayments=new CashPayments(driver,dataFile);
        cashPayments.cashPayment();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
        Allure.step("After Test - Cash Payments");
    }
}
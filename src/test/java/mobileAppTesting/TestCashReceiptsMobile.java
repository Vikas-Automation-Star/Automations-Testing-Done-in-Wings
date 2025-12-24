package mobileAppTesting;

import com.wings.pages.AppLogin;
import mobileTesing.CashReceiptsMobile;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCashReceiptsMobile {
    WebDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="src/main/resources/mobileTesting/cashReceipts.json";

    @BeforeTest
    public void setAppLogin() throws IOException, InterruptedException {
        driver= appLogin.mobileLogin();
    }

    @Test
    public void receiptsFromParties() throws InterruptedException, IOException, ParseException {
        CashReceiptsMobile receiptsMobile=new CashReceiptsMobile(driver,file);
        receiptsMobile.cashReceipts();
    }

    @AfterTest
    public void signOut() throws InterruptedException {
        appLogin.signOut();
    }
}

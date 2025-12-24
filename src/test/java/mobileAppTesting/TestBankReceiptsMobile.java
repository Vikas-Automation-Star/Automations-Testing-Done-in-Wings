package mobileAppTesting;

import com.wings.pages.AppLogin;
import mobileTesing.BankReceiptsMobile;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestBankReceiptsMobile {
    WebDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="src/main/resources/mobileTesting/bankReceipts.json";

    @BeforeTest
    public void setAppLogin() throws IOException, InterruptedException {
        driver= appLogin.mobileLogin();
    }

    @Test
    public void receiptsFromParties() throws InterruptedException, IOException, ParseException {
        BankReceiptsMobile bankReceiptsMobile=new BankReceiptsMobile(driver,file);
        bankReceiptsMobile.bankReceipts();
    }

    @AfterTest
    public void signOut() throws InterruptedException {
        appLogin.signOut();
    }
}

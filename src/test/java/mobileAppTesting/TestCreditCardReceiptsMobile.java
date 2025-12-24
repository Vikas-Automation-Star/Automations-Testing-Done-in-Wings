package mobileAppTesting;

import com.wings.pages.AppLogin;
import mobileTesing.CreditCardReceiptsMobile;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCreditCardReceiptsMobile {

    WebDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="src/main/resources/mobileTesting/creditCardReceiptsMobile.json";

    @BeforeTest
    public void setAppLogin() throws IOException, InterruptedException {
        driver= appLogin.mobileLogin();
    }

    @Test
    public void creditCardReceiptsMobile() throws InterruptedException, IOException, ParseException {
        CreditCardReceiptsMobile cardReceiptsMobile=new CreditCardReceiptsMobile(driver,file);
        cardReceiptsMobile.creditCardReceiptsMobile();
    }

    @AfterTest
    public void signOut() throws InterruptedException {
        appLogin.signOut();
    }

}

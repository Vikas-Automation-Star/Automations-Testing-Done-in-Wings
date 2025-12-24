package mobileAppTesting;

import com.wings.pages.AppLogin;
import mobileTesing.SalesInvoiceMobile;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestSalesInvoiceMobile {

    WebDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="src/main/resources/mobileTesting/salesInvoiceMobile.json";

    @BeforeTest
    public void setAppLogin() throws IOException, InterruptedException {
        driver= appLogin.mobileLogin();
    }

    @Test
    public void salesInvoiceMobile() throws InterruptedException, IOException, ParseException {
        SalesInvoiceMobile invoiceMobile=new SalesInvoiceMobile(driver,file);
        invoiceMobile.salesInvoiceMobile();
    }

    @AfterTest
    public void signOut() throws InterruptedException {
//        appLogin.signOut();
    }

}

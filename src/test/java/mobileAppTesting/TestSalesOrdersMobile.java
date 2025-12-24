package mobileAppTesting;

import com.wings.pages.AppLogin;
import mobileTesing.SalesOrdersMobile;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestSalesOrdersMobile {

    WebDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="src/main/resources/mobileTesting/salesOrdersMobile.json";

    @BeforeTest
    public void setAppLogin() throws IOException, InterruptedException {
        driver= appLogin.mobileLogin();
    }

    @Test
    public void salesOrders() throws InterruptedException, IOException, ParseException {
        SalesOrdersMobile ordersMobile=new SalesOrdersMobile(driver,file);
        ordersMobile.salesOrders();
    }

    @AfterTest
    public void signOut() throws InterruptedException {
        appLogin.signOut();
    }

}

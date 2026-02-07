package tradeTesting.sales.transactions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestSalesReturnTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/sales/transactions/492538 - Sales Returns-Trd_SR_4_SI_4.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void testSalesRetunTrade() throws IOException, ParseException, InterruptedException, AWTException {
        SalesReturnTrade salesReturnTrade=new SalesReturnTrade(driver,dataFile);
        salesReturnTrade.salesReturnTrade("SI 9");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
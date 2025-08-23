package menuItems.finance.transactions.Journals;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Journals.BookIncomesOrReceivables;
import com.wings.pages.finance.transactions.Journals.BookingOfOtherCosts;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestBookingOfOtherCosts {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/479089 - Booking Of Other Costs-AC_BOC_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void receiptFromParty() throws IOException, ParseException, InterruptedException, AWTException {
        BookingOfOtherCosts bookingOfOtherCosts=new BookingOfOtherCosts(driver,dataFile);
        bookingOfOtherCosts.otherBookingCosts();
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}

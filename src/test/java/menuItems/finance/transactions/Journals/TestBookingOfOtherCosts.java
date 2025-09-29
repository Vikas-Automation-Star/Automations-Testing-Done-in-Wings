package menuItems.finance.transactions.Journals;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Journals.BookIncomesOrReceivables;
import com.wings.pages.finance.transactions.Journals.BookingOfOtherCosts;
import com.wings.pages.purchase.transactions.PurchaseVoucher;
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

    private static final String TEMP_API_BODY_PURCHASE_VOUCHERS="./output/temp_api_request_bodies/PurchaseVouchers.json";
    private static final String API_RESPONSE_PURCHASE_VOUCHERS="./output/api_responses/PurchaseVouchers.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9_Output.xls";

    private static final String TEMP_API_BODY_BOOKING_OF_OTHER_COSTS="./output/temp_api_request_bodies/BookingOfOtherCosts.json";
    private static final String API_RESPONSE_BOOKING_OF_OTHER_COSTS="./output/api_responses/BookingOfOtherCosts.json";
    private static final String OUTPUT_FILE1="./src/main/resources/menuItems/finance/transaction/479089 - Booking Of Other Costs-AC_BOC_2_PV_11_Output.xls";

    String file = "./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9.xls";
    String dataFile="./src/main/resources/menuItems/finance/transaction/479089 - Booking Of Other Costs-AC_BOC_2_PV_11.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void bookingOfOtherCosts() throws Exception {
//        PurchaseVoucher po = new PurchaseVoucher(driver, file);
//        String purchaseVoucher=po.purchaseVoucher(TEMP_API_BODY_PURCHASE_VOUCHERS,API_RESPONSE_PURCHASE_VOUCHERS,OUTPUT_FILE);
//
//        appLogin.logout();
//        driver= appLogin.login();

        BookingOfOtherCosts bookingOfOtherCosts=new BookingOfOtherCosts(driver,dataFile);
        bookingOfOtherCosts.otherBookingCosts("PV 15",TEMP_API_BODY_BOOKING_OF_OTHER_COSTS,API_RESPONSE_BOOKING_OF_OTHER_COSTS,OUTPUT_FILE1);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}

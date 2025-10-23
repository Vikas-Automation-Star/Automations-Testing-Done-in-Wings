package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.DeliveriesAgainstOrdersTransaction;
import com.wings.pages.sales.transactions.SalesOrders;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestDeliveriesAgainstOrder {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();

    private static final String TEMP_API_SALES_ORDER="./output/temp_api_request_bodies/salesOrder.json";
    private static final String API_RESPONSE_SALES_ORDER="./output/api_responses/salesOrder.json";
    private static final String OUTPUT_FILE_SALES_ORDER="./src/main/resources/menuItems/Sales/Transactions/478887 - Sales Orders-AC_Output.xls";

    private static final String TEMP_API_DELIVERIES_AGAINST_ORDER="./output/temp_api_request_bodies/deliveriesAgainstOrder.json";
    private static final String API_RESPONSE_DELIVERIES_AGAINST_ORDER="./output/api_responses/deliveriesAgainstOrder.json";
    private static final String OUTPUT_FILE_DELIVERIES_AGAINST_ORDER="./src/main/resources/menuItems/Sales/Transactions/479587 - Deliveries against Orders-AC_Output.xls";

    String dataFile1="./src/main/resources/menuItems/Sales/Transactions/478887 - Sales Orders-AC.xls";
    String dataFile2="./src/main/resources/menuItems/Sales/Transactions/479587 - Deliveries against Orders-AC.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void deliveriesAgainstOrders() throws Exception {
//        SalesOrders salesOrders=new SalesOrders(driver,dataFile1);
//        String salesOrderVoucher=salesOrders.salesOrder(TEMP_API_SALES_ORDER,API_RESPONSE_SALES_ORDER,OUTPUT_FILE_SALES_ORDER);
//        appLogin.logout();
//        driver = appLogin.login();

        DeliveriesAgainstOrdersTransaction deliveriesAgainstOrdersTransaction=new DeliveriesAgainstOrdersTransaction(driver,dataFile2);
        String deliveriesAgainstOrdersVoucher=
                deliveriesAgainstOrdersTransaction.deliveriesAgainstOrders
                        ("SO 54",TEMP_API_DELIVERIES_AGAINST_ORDER,API_RESPONSE_DELIVERIES_AGAINST_ORDER,OUTPUT_FILE_DELIVERIES_AGAINST_ORDER);
    }

    @AfterTest
    public void afterTest() throws IOException {
            appLogin.logout();
    }
}
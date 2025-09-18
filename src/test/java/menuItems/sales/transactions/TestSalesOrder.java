package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesOrders;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestSalesOrder {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_SALES_ORDER="./output/temp_api_request_bodies/salesOrder.json";
    private static final String API_RESPONSE_SALES_ORDER="./output/api_responses/salesOrder.json";
    private static final String OUTPUT_FILE_SALES_ORDER="./src/main/resources/menuItems/Sales/Transactions/478887 - Sales Orders-AC_Output.xls";

    String dataFile="./src/main/resources/menuItems/Sales/Transactions/478887 - Sales Orders-AC.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void salesOrder() throws Exception {
        SalesOrders salesOrders=new SalesOrders(driver,dataFile);
        String salesOrderVoucher=salesOrders.salesOrder(TEMP_API_SALES_ORDER,API_RESPONSE_SALES_ORDER,OUTPUT_FILE_SALES_ORDER);

    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}

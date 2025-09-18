package menuItems.Production.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.production.transactions.ProductOrders;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.io.IOException;

public class TestProductionOrders {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_PRODUCTION_ORDERS="./output/temp_api_request_bodies/ProductionsOrders.json";
    private static final String API_RESPONSE_PRODUCTION_ORDERS="./output/api_responses/ProductionsOrders.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/production/transactions/475293 - Production Orders-AC_PRO_1_Output.xls";

    String file = "./src/main/resources/menuItems/production/transactions/475293 - Production Orders-AC_PRO_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void ProductionOrdersTransaction() throws Exception {
        ProductOrders po = new ProductOrders(driver, file);
        po.productOrders(TEMP_API_BODY_PRODUCTION_ORDERS,API_RESPONSE_PRODUCTION_ORDERS,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}

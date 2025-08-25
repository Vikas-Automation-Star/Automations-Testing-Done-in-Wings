package menuItems.inventory.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.inventory.transactions.PhysicalStockTake;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestPhysicalStockTake {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    private static final String TEMP_API_BODY_PHYSICAL_STOCK_TAKE="./output/temp_api_request_bodies/physicalStockTake.json";
    private static final String API_RESPONSE_PHYSICAL_STOCK_TAKE ="./output/api_responses/physicalStockTake.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/inventory/transactions/458873 - Physical Stock Take-AC_PST_1_Output.xls";
    String file = "./src/main/resources/menuItems/inventory/transactions/458873 - Physical Stock Take-AC_PST_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }
    @Test
    public void stockConversion() throws Exception {
        PhysicalStockTake physicalStockTake=new PhysicalStockTake(driver,file);
        physicalStockTake.physicalStockTake(TEMP_API_BODY_PHYSICAL_STOCK_TAKE,API_RESPONSE_PHYSICAL_STOCK_TAKE,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

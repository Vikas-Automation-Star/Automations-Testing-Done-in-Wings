package menuItems.inventory.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.inventory.transactions.InitiateStockTake;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestInitiateStockTake {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_INITIATE_STOCK_TAKE="./output/temp_api_request_bodies/initiateStockTake.json";
    private static final String API_RESPONSE_INITIATE_STOCK_TAKE="./output/api_responses/initiateStockTake.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/inventory/transactions/450889 - Initiate Stock Take-AC_IST_1_Output.xls";


    String file = "./src/main/resources/menuItems/inventory/transactions/450889 - Initiate Stock Take-AC_IST_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void initiateStockTake() throws InterruptedException, IOException, ParseException {
        InitiateStockTake initiateStockTake=new InitiateStockTake(driver,file);
        initiateStockTake.initiateStockTake(TEMP_API_BODY_INITIATE_STOCK_TAKE,API_RESPONSE_INITIATE_STOCK_TAKE,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}

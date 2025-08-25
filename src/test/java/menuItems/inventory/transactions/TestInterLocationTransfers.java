package menuItems.inventory.transactions;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.inventory.transactions.InterLocationTransfers;
import java.awt.*;
import java.io.IOException;

public class TestInterLocationTransfers {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    private static final String TEMP_API_BODY_INTER_LOCATION_TRANSFER="./output/temp_api_request_bodies/interLocationTransfer.json";
    private static final String API_RESPONSE_INTER_LOCATION_TRANSFER ="./output/api_responses/interLocationTransfer.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/inventory/transactions/473073 - Inter Location Transfers-AC_ILT_1_Output.xls";


    String file = "./src/main/resources/menuItems/inventory/transactions/473073 - Inter Location Transfers-AC_ILT_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void interLocationTransfer() throws InterruptedException, AWTException, IOException, ParseException {
        InterLocationTransfers locationTransfers = new InterLocationTransfers(driver, file);
        locationTransfers.locationTransfer(TEMP_API_BODY_INTER_LOCATION_TRANSFER, API_RESPONSE_INTER_LOCATION_TRANSFER,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
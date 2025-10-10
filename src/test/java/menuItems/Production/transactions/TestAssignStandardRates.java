package menuItems.Production.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.production.transactions.AssignStandardRates;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestAssignStandardRates {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    private static final String TEMP_API_BODY_ASSIGN_STANDARD_RATES="./output/temp_api_request_bodies/AssignStandardRates.json";
    private static final String API_RESPONSE_ASSIGN_STANDARD_RATES="./output/api_responses/AssignStandardRates.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/production/transactions/163139 - Assign Standard Rates-AC_ASR_1_Output.xls";

    String file = "./src/main/resources/menuItems/production/transactions/163139 - Assign Standard Rates-AC_ASR_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void assignStandardRates() throws Exception, AWTException {
        AssignStandardRates assignStandardRates = new AssignStandardRates(driver, file);
        assignStandardRates.assignStandardRates(TEMP_API_BODY_ASSIGN_STANDARD_RATES,API_RESPONSE_ASSIGN_STANDARD_RATES,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

package menuItems.Production.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.production.transactions.MaterialIssuesAndReceiptsFromProduction;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestMaterialIssuesAndReceiptsFromProduction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_MATERIAL_ISSUES_RECEIPTS_FROM_PRODUCTION="./output/temp_api_request_bodies/MaterialIssuesReceiptsFromProduction.json";
    private static final String API_RESPONSE_CLOSE_PRODUCTION_ORDERS="./output/api_responses/MaterialIssuesReceiptsFromProduction.json";
    private static final String OUTPUT_FILE1="./src/main/resources/menuItems/production/transactions/465120 - Material Issues and Receipts from Production-AC_MIRFP_1_Output.xls";

    String file = "./src/main/resources/menuItems/production/transactions/465120 - Material Issues and Receipts from Production-AC_MIRFP_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void MaterialIssuesAndReceiptsFromProductionTransaction() throws Exception {
        MaterialIssuesAndReceiptsFromProduction mirp = new MaterialIssuesAndReceiptsFromProduction(driver, file);
        mirp.materialIssuesAndReceiptsFromProduction(TEMP_API_BODY_MATERIAL_ISSUES_RECEIPTS_FROM_PRODUCTION,API_RESPONSE_CLOSE_PRODUCTION_ORDERS,OUTPUT_FILE1);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}

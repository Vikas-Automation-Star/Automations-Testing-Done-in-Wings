package menuItems.Production.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.production.transactions.MaterialIssuesToProduction;
import com.wings.pages.production.transactions.MaterialReceiptFromProduction;
import com.wings.pages.production.transactions.ProductOrders;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestMaterialReceiptFromProduction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_PRODUCTION_ORDERS="./output/temp_api_request_bodies/ProductionsOrders.json";
    private static final String API_RESPONSE_PRODUCTION_ORDERS="./output/api_responses/ProductionsOrders.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/production/transactions/475293 - Production Orders-AC_PRO_1_Output.xls";

    private static final String TEMP_API_BODY_MATERIAL_ISSUES_PRODUCTION="./output/temp_api_request_bodies/MaterialIssuesToProduction.json";
    private static final String API_RESPONSE_MATERIAL_ISSUES_PRODUCTION="./output/api_responses/MaterialIssuesToProduction.json";
    private static final String OUTPUT_FILE1="./src/main/resources/menuItems/production/transactions/461542 - Material Issues to Production-AC_MITP_1_Output.xls";

    private static final String TEMP_API_BODY_MATERIAL_RECEIPTS_FROM_PRODUCTION="./output/temp_api_request_bodies/MaterialReceiptsFromProduction.json";
    private static final String API_RESPONSE_MATERIAL_RECEIPTS_FROM_PRODUCTION="./output/api_responses/MaterialReceiptsFromProduction.json";
    private static final String OUTPUT_FILE2="./src/main/resources/menuItems/production/transactions/475297 - Material Receipts from Production-AC_MRFP_1_Output.xls";

    String file = "./src/main/resources/menuItems/production/transactions/475293 - Production Orders-AC_PRO_1.xls";
    String file2 = "./src/main/resources/menuItems/production/transactions/461542 - Material Issues to Production-AC_MITP_1.xls";
    String file3 = "./src/main/resources/menuItems/production/transactions/475297 - Material Receipts from Production-AC_MRFP_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.login();
    }

    @Test
    public void MaterialReceiptFromProduction() throws Exception {
        ProductOrders productOrders = new ProductOrders(driver, file);
        String productionOrderVoucher=productOrders.productOrders(TEMP_API_BODY_PRODUCTION_ORDERS,API_RESPONSE_PRODUCTION_ORDERS,OUTPUT_FILE);

        appLogin.logout();
        driver= appLogin.login();

        MaterialIssuesToProduction materialIssuesToProduction = new MaterialIssuesToProduction(driver, file2);
        String materialIssuesToProductionVoucher= materialIssuesToProduction.materialIssuesToProduction(productionOrderVoucher,TEMP_API_BODY_MATERIAL_ISSUES_PRODUCTION,API_RESPONSE_MATERIAL_ISSUES_PRODUCTION,OUTPUT_FILE1);

        appLogin.logout();
        driver= appLogin.login();

        MaterialReceiptFromProduction materialReceiptFromProduction = new MaterialReceiptFromProduction(driver, file3);
        materialReceiptFromProduction.materialReceiptFromProduction(materialIssuesToProductionVoucher,TEMP_API_BODY_MATERIAL_RECEIPTS_FROM_PRODUCTION,API_RESPONSE_MATERIAL_RECEIPTS_FROM_PRODUCTION,OUTPUT_FILE2);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}

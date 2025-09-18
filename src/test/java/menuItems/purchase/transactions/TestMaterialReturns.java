package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.MaterialReceipt;
import com.wings.pages.purchase.transactions.MaterialReturn;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestMaterialReturns {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_MATERIAL_RECEIPTS="./output/temp_api_request_bodies/materialReceipt.json";
    private static final String API_RESPONSE_MATERIAL_RECEIPTS="./output/api_responses/materialReceipt.json";
    private static final String OUTPUT_FILE_MATERIAL_RECEIPTS="./src/main/resources/menuItems/purchase/transactions/473342 - Material Receipts-AC_MR_1_Output.xls";

    private static final String TEMP_API_MATERIAL_RETURNS="./output/temp_api_request_bodies/materialReturns.json";
    private static final String API_RESPONSE_MATERIAL_RETURNS="./output/api_responses/materialReturns.json";
    private static final String OUTPUT_FILE_MATERIAL_RETURNS="./src/main/resources/menuItems/purchase/transactions/477031 - Material Returns-AC_MRT_1_Output.xls";

    String fileMR = "./src/main/resources/menuItems/purchase/transactions/473342 - Material Receipts-AC_MR_1.xls";
    String file = "./src/main/resources/menuItems/purchase/transactions/477031 - Material Returns-AC_MRT_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void materialReturns() throws Exception {
        MaterialReceipt materialReceipt=new MaterialReceipt(driver,fileMR);
        String materialReceiptVoucher=materialReceipt.materialReceipt(TEMP_API_BODY_MATERIAL_RECEIPTS,API_RESPONSE_MATERIAL_RECEIPTS,OUTPUT_FILE_MATERIAL_RECEIPTS);

        appLogin.logout();
        driver=appLogin.login();

        MaterialReturn materialReturn = new MaterialReturn(driver, file);
        materialReturn.materialReturn(materialReceiptVoucher,TEMP_API_MATERIAL_RETURNS,API_RESPONSE_MATERIAL_RETURNS,OUTPUT_FILE_MATERIAL_RETURNS);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

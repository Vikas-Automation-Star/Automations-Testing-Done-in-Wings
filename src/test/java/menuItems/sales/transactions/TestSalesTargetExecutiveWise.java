package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.DefineSalesTargetExecutiveWise;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TestSalesTargetExecutiveWise {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    private static final String TEMP_API_SALES_TARGET="./output/temp_api_request_bodies/salesTarget.json";
    private static final String API_RESPONSE_SALES_TARGET="./output/api_responses/salesTarget.json";
    private static final String OUTPUT_FILE_SALES_TARGET="./src/main/resources/menuItems/Sales/Transactions/406559 - Define Sales Targets-Executive Wise-AC_Output.xls";

    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/406559 - Define Sales Targets-Executive Wise-AC.xls";


    @BeforeMethod
    public void beforeTest() throws ParseException, InterruptedException, IOException {
        driver = appLogin.login();
    }

    @Test
    public void salesTargetExecutiveWise() throws Exception {
        DefineSalesTargetExecutiveWise salesTargetExecutiveWise=new DefineSalesTargetExecutiveWise(driver,dataFile);
        salesTargetExecutiveWise.salesTargetExecutiveWise(TEMP_API_SALES_TARGET,API_RESPONSE_SALES_TARGET,OUTPUT_FILE_SALES_TARGET);
    }

    @AfterMethod
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
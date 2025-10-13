package menuItems.Taxes.transactions.GST;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.transactions.GST.GSTR2MonthEnd;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestGSTR2MonthEnd {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_GSTR2_MONTHEND ="./output/temp_api_request_bodies/gstr2MonthEnd.json";
    private static final String API_RESPONSE_GSTR2_MONTHEND ="./output/api_responses/gstr2MonthEnd.json";
    private static final String OUTPUT_FILE_GSTR2_MONTHEND ="./src/main/resources/menuItems/Taxes/transactions/GST/478154 - GSTR2 Month End-AC_GSTRV2_1_Output.xls";

    String file = "./src/main/resources/menuItems/Taxes/transactions/GST/478154 - GSTR2 Month End-AC_GSTRV2_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void gstr2MonthEndTransaction() throws Exception {
        GSTR2MonthEnd gstr2MonthEnd=new GSTR2MonthEnd(driver,file);
        gstr2MonthEnd.gstr2MonthEnd(TEMP_API_GSTR2_MONTHEND,API_RESPONSE_GSTR2_MONTHEND,OUTPUT_FILE_GSTR2_MONTHEND);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
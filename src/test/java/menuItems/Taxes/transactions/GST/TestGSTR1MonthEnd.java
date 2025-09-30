package menuItems.Taxes.transactions.GST;

import com.wings.pages.AppLogin;
import com.wings.pages.taxes.transactions.GST.GSTR1MonthEnd;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestGSTR1MonthEnd {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_GSTR1_MONTHEND="./output/temp_api_request_bodies/gstr1MonthEnd.json";
    private static final String API_RESPONSE_GSTR1_MONTHEND="./output/api_responses/gstr1MonthEnd.json";
    private static final String OUTPUT_FILE_GSTR1_MONTHEND="./src/main/resources/menuItems/Taxes/transactions/GST/482921 - GSTR1 Month End-AC_GSTRV1_1_Output.xls";

    String file = "./src/main/resources/menuItems/Taxes/transactions/GST/482921 - GSTR1 Month End-AC_GSTRV1_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void gstr1MonthEndTransaction() throws Exception {
        GSTR1MonthEnd gstr1MonthEnd = new GSTR1MonthEnd(driver, file);
        gstr1MonthEnd.gstr1MonthEnd(TEMP_API_GSTR1_MONTHEND,API_RESPONSE_GSTR1_MONTHEND,OUTPUT_FILE_GSTR1_MONTHEND);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

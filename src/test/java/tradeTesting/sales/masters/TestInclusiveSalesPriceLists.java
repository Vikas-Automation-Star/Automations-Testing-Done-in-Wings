package tradeTesting.sales.masters;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestInclusiveSalesPriceLists {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/tradeAutomation/sales/masters/tradeInclusiveSalesPriceLists.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void inclusiveSalesPriceLists() throws Exception {
        CreateInclusiveSalesPriceLists inclusiveSalesPriceLists=new CreateInclusiveSalesPriceLists(driver,file);
        inclusiveSalesPriceLists.createInclusiveSalesPriceLists();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}

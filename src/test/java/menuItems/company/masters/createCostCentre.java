package menuItems.company.masters;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.Company;
import com.wings.pages.company.masters.CostCenter;

import java.io.IOException;

public class createCostCentre {

        WindowsDriver driver;
        AppLogin appLogin=new AppLogin();
        CostCenter center;
        String file="./src/main/resources/MeniItems/company/Masters/costCentreData.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver=appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
        }

        @Test
        public void newReason() throws IOException, ParseException, InterruptedException {
            center=new CostCenter(driver,file);
            center.findCostCenter();

        }
    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

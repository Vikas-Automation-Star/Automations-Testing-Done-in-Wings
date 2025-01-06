package menuItems.sales.reports;

//import com.wings.utils.SalesEnquiriesDemo;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.reports.DeliveriesReportCode;
import java.io.IOException;


public class DeliveryReport {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void deliveries() throws InterruptedException {
        DeliveriesReportCode deliveriesReportCode=new DeliveriesReportCode(driver);
        deliveriesReportCode.deliveries();
    }

    @AfterTest
    public void afterTest(){
//        appLogin.logout();
    }
}

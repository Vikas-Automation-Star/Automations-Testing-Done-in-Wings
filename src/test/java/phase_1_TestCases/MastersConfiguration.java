package phase_1_TestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class MastersConfiguration {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/phase_1_List/masterConfig.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void newNode() throws InterruptedException, IOException, ParseException, AWTException {
        MasterConfig config =new MasterConfig(driver,dataFile);
        String customerName=config.newCustomer();
        config.customerRename(customerName);
        config.masterInactive("At_Cus_Reg_Intra");
        config.createNode();
        config.renameNode();
        config.moveAsSubNodeAndMainNode();
        config.movingMastersBetweenNodes("//ListItem[@Name='AP_Customer_Customer_customer']/Text[@Name='AP_Customer_Customer_customer']");
    }

    @AfterTest
    public void afterTest() throws IOException {
            appLogin.logout();
    }
}
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
    String dataFile = "./src/main/resources/phase_1_List/masterConfig.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test(priority = 1)
    public void createNewCustomer() throws InterruptedException, IOException, ParseException, AWTException {
        MasterConfig config = new MasterConfig(driver, dataFile);
        String customerName = config.newCustomer();
        config.customerRename(customerName);
    }

    @Test(priority = 2)
    public void setMasterInactive() throws InterruptedException, IOException, ParseException, AWTException {
        MasterConfig config = new MasterConfig(driver, dataFile);
        config.masterInactive("At_Cus_Reg_Intra");
    }

    @Test(priority = 3)
    public void searchMaster() throws IOException, ParseException {
        MasterConfig search=new MasterConfig(driver,dataFile);
        search.searchMaster("code");
    }

    @Test(priority = 4)
    public void nodeCreation() throws InterruptedException, IOException, ParseException, AWTException {
        MasterConfig config = new MasterConfig(driver, dataFile);
        config.createNode();
    }

    @Test(priority = 5)
    public void nodeRename() throws InterruptedException, IOException, ParseException, AWTException {
        MasterConfig config = new MasterConfig(driver, dataFile);
        config.renameNode();
    }

    @Test(priority = 6)
    public void moveAsSubNodeAndMoveToMainNode() throws InterruptedException, IOException, ParseException, AWTException {
        MasterConfig config = new MasterConfig(driver, dataFile);
        config.moveAsSubNodeAndMainNode();
    }

    @Test(priority = 7)
    public void moveMastersBetweenNodes() throws InterruptedException, IOException, ParseException, AWTException {
        MasterConfig config = new MasterConfig(driver, dataFile);
        config.movingMastersBetweenNodes("//ListItem[@Name='Anjali Devi']/Text[@Name='Anjali Devi']");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}

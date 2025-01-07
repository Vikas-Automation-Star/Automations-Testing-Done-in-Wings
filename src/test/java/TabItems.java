import io.appium.java_client.windows.WindowsDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.utils.Common;
import com.wings.utils.DriverManager;

import java.io.IOException;

public class TabItems {
    WindowsDriver driver;
    Common common;
    DriverManager driverManager=new DriverManager();

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=driverManager.login();

    }

    @Test
    public void test(){
        common=new Common(driver);
        common.clickElement("xpath","//TabItem[@Name='Configure']");
        Assert.assertTrue(common.isDisplayed("name","Settings"),"Settings is not displayed");

        common.clickElement("xpath","//TabItem[@Name='Analytics']");
        Assert.assertTrue(common.isDisplayed("name","Dashboard"),"dashboard is not displayed");

        common.clickElement("xpath","//TabItem[@Name='Workbench']");
        Assert.assertTrue(common.isDisplayed("name","SALES") && common.isDisplayed("name","PURCHASES")
                ,"sales & purchase r not displayed");

        common.clickElement("xpath","//TabItem[@Name='My Todo List']");
//        Assert.assertTrue(common.isDisplayed("name","Row 2"),"Status message is not displayed");

    }

    @AfterTest
    public void afterTest() throws IOException {
        common.clickElement("xpath", "//Button[@Name='Close']");
        common.clickElement("xpath","//Button[@Name='Yes']");
    }
}

package tradeTesting.configure;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestVoucherTypes {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/tradeAutomation/configure/tradeVoucherTypes.json";

    @BeforeTest
    public void beforeTest() throws  InterruptedException, IOException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void voucherTypes () throws Exception {
        CreateVoucherTypes voucherTypes=new CreateVoucherTypes(driver,file);
        voucherTypes.createVoucherTypes();

    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

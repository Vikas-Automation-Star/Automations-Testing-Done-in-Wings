package menuItems.Production.transactions;

import com.wings.pages.production.transactions.MaterialissuestoProduction;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class MaterialIssuesToProductionTransaction {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String file="./src/main/resources/MenuItems/production/transactions/MaterialIssuesToProductionTransaction.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void materialIssuesToProductionTransaction() throws IOException, ParseException, InterruptedException {
        MaterialissuestoProduction mifp=new MaterialissuestoProduction(driver,file);
        mifp.materialIssuesToProduction();

    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

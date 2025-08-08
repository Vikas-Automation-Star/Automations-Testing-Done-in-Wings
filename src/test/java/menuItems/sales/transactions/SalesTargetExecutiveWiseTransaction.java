package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.DefineSalesTargetExecutiveWise;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class SalesTargetExecutiveWiseTransaction {
        WindowsDriver driver;
        AppLogin appLogin = new AppLogin();
        String dataFile = "./src/main/resources/menuItems/Sales/Transactions/406559 - Define Sales Targets-Executive Wise-AC.xls";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver = appLogin.login();
        }

        @Test
        public void salesOrderCancellation() throws IOException, InterruptedException, ParseException, AWTException {
            DefineSalesTargetExecutiveWise salesTargetExecutiveWise=new DefineSalesTargetExecutiveWise(driver,dataFile);
            salesTargetExecutiveWise.salesTargetExecutiveWise();
        }

        @AfterTest
        public void afterTest() throws IOException {
            appLogin.logout();
        }
    }
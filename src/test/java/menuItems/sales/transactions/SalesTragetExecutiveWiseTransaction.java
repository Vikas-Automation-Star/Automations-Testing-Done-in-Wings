package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.DefineSalesTargetExecutiveWise;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class SalesTragetExecutiveWiseTransaction {
        WindowsDriver driver;
        AppLogin appLogin = new AppLogin();
        Common common;
        String dataFile = "./src/main/resources/menuItems/Sales/Transactions/salesEnquiry.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            common=new Common(driver);
            driver = appLogin.launchSingleUserApp();
            appLogin.singleUserLogin(common.getData(dataFile,"salesEnquiry","userName"),common.getData(dataFile,"salesEnquiry","password"));
        }

        @Test
        public void salesOrderCancellation() throws IOException, InterruptedException, ParseException, AWTException {
            DefineSalesTargetExecutiveWise salesTargetExecutiveWise=new DefineSalesTargetExecutiveWise(driver,dataFile);
            salesTargetExecutiveWise.salesTargetExecutiveWise();
        }

        @AfterTest
        public void afterTest() throws IOException {
//            appLogin.logout();
        }
    }
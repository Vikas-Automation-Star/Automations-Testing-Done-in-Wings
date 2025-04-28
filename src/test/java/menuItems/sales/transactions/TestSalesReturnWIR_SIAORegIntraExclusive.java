package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.*;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesReturnWIR_SIAORegIntraExclusive {
        WindowsDriver driver;
        AppLogin appLogin=new AppLogin();
        String dataFile="./src/main/resources/menuItems/Sales/Transactions/deliveriesAgainstOrder_RegIntraExclusive.json";

        @BeforeTest
        public void beforeTest() throws IOException, ParseException, InterruptedException {
            driver=appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
        }

        @Test
        public void regInterInclusive_SIAO() throws IOException, ParseException, InterruptedException, AWTException {
            SalesOrder_RegIntraExclusive orderRegIntraExclusive=new SalesOrder_RegIntraExclusive(driver,dataFile);

            SalesinvoiceAgainstOrders_RegIntraExclusive againstOrdersRegIntraExclusive=new SalesinvoiceAgainstOrders_RegIntraExclusive(driver,dataFile);
            String []SIAO=againstOrdersRegIntraExclusive.intraExclusiveRegSIAO(orderRegIntraExclusive.intraExclusiveReg()[1]);

           SRWIR_SIAORegIntraExclusive srwirSiaoRegIntraExclusive=new SRWIR_SIAORegIntraExclusive(driver,dataFile);
           srwirSiaoRegIntraExclusive.regExclusiveIntraInvoiceRef_SIAO(SIAO[1]);
//           srwirSiaoRegIntraExclusive.regExclusiveIntraInvoiceRef_SIAO("SIAO8");

        }

        @AfterTest
        public void afterTest() throws IOException {
            appLogin.logout();
        }
    }
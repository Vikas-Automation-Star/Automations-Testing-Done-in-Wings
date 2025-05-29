package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SRWIR_SIAORegInterInclusive;
import com.wings.pages.sales.transactions.SalesInvoiceAgainstOrders_RegInterInclusive;
import com.wings.pages.sales.transactions.SalesOrder_RegInterInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesReturnWIR_SIAORegInterInclusive {
        WindowsDriver driver;
        AppLogin appLogin=new AppLogin();
        String dataFile="./src/main/resources/menuItems/Sales/Transactions/deliveriesAgainstOrder_RegInterInclusive.json";

        @BeforeTest
        public void beforeTest() throws IOException, ParseException, InterruptedException {
            driver=appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
        }

        @Test
        public void regInterInclusive_SIAO() throws IOException, ParseException, InterruptedException, AWTException {
            SalesOrder_RegInterInclusive orderRegInterInclusive=new SalesOrder_RegInterInclusive(driver,dataFile);

            SalesInvoiceAgainstOrders_RegInterInclusive invoiceAgainstOrdersRegInterInclusive =new SalesInvoiceAgainstOrders_RegInterInclusive(driver,dataFile);
            String []SIAO=invoiceAgainstOrdersRegInterInclusive.interInclusiveRegSIAO(orderRegInterInclusive.interInclusiveReg()[1]);

            SRWIR_SIAORegInterInclusive srwirSiaoRegInterInclusive=new SRWIR_SIAORegInterInclusive(driver,dataFile);
            srwirSiaoRegInterInclusive.regInclusiveInterInvoiceRef_SIAO(SIAO[1]);
//            srwirSiaoRegInterInclusive.regInclusiveInterInvoiceRef_SIAO("SIAO 2");

        }

        @AfterTest
        public void afterTest() throws IOException {
            appLogin.logout();
        }
    }
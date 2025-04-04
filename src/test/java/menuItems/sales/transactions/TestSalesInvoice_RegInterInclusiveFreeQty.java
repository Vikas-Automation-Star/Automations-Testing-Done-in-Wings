package menuItems.sales.transactions;
//done
import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesInvoiceFQ_RegInterInclusive;
import com.wings.pages.sales.transactions.SRTWIR_RegInterInclusive;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesInvoice_RegInterInclusiveFreeQty {
        WindowsDriver driver;
        AppLogin appLogin=new AppLogin();
        String dataFile="./src/main/resources/menuItems/Sales/Transactions/freeQuantitySITCInclusiveInter_Reg.json";

        @BeforeTest
        public void beforeTest() throws IOException, ParseException, InterruptedException {
            long beforeTestStartTime=System.currentTimeMillis();
            driver=appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
            long beforeTestEndTime=System.currentTimeMillis();
            System.out.println("total before test time in sec: "+ (beforeTestEndTime-beforeTestStartTime)/1000);
        }

        @Test
        public void InclusiveTCSGSTFreeQuantity() throws IOException, ParseException, InterruptedException, AWTException {
            SalesInvoiceFQ_RegInterInclusive interInclusiveTCSGSTFreeQty=new SalesInvoiceFQ_RegInterInclusive(driver,dataFile);
            SRTWIR_RegInterInclusive regInterInclusive=new SRTWIR_RegInterInclusive(driver,dataFile);
//            regInterInclusive.RegInclusiveInvoiceReference("SI 2");
            regInterInclusive.RegInclusiveInvoiceReference(interInclusiveTCSGSTFreeQty.interInclusiveTCSGST_FreeQty());
        }

        @AfterTest
        public void afterTest() throws IOException {
//        appLogin.logout();
        }
    }
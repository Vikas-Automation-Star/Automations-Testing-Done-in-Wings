package tradeTesting.sales.transactions;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestSalesInvoiceAgainstOrderTrade {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    String dataFile="./src/main/resources/tradeAutomation/sales/transactions/497086 - Sales Invoice against Orders-Trd_SIAO_5_SO_7.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException {
        driver=appLogin.tradeLogin();
    }

    @Test
    public void invoiceAgainstOrdersTrade() throws Exception {
        SalesInvoiceAgainstOrderTrade salesInvoiceAgainstOrderTrade=new SalesInvoiceAgainstOrderTrade(driver,dataFile);
        salesInvoiceAgainstOrderTrade.invoiceAgainstOrdersTrade("SO 8","","","");
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
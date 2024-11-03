package menuItems.finance.transactions.Banking;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Banking.BankReconciliation;
import com.wings.pages.finance.transactions.Banking.DepositPostDatedCheques;
import com.wings.pages.sales.transactions.SalesOrder;
import com.wings.pages.sales.transactions.SalesOrderPostDatedCheques;

import java.awt.*;
import java.io.IOException;

public class DepositPostDatedChequesTransaction {
        WindowsDriver driver;
        AppLogin appLogin=new AppLogin();
        String dataFile="./src/main/resources/menuItems/finance/transaction/depositPostDatedCheques.json";
        String orderFile="./src/main/resources/menuItems/Sales/Transactions/salesOrder.json";

        @BeforeTest
        public void beforeTest() throws IOException, ParseException, InterruptedException {
            driver = appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
            SalesOrderPostDatedCheques orderPostDatedCheques=new SalesOrderPostDatedCheques(driver,orderFile);
            orderPostDatedCheques.salesOrder();
        }

        @Test
        public void postDatedCheques() throws IOException, ParseException, InterruptedException, AWTException {
            DepositPostDatedCheques postDatedCheques=new DepositPostDatedCheques(driver,dataFile);
            postDatedCheques.postDatedChques();
        }

        @AfterTest
        public void afterTest(){
            appLogin.logout();
        }
    }

package util;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.SalesOrderValidation;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class Validations {
        WindowsDriver driver;
        AppLogin appLogin = new AppLogin();
        String file = "./src/main/resources/validationDataFile.json";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver = appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
        }

        @Test
        public void salesOrderValidation() throws IOException, InterruptedException, ParseException {
            SalesOrderValidation salesOrderValidation=new SalesOrderValidation(driver,file);
            salesOrderValidation.navigateToSalesOrder();
            salesOrderValidation.voucherType();
            salesOrderValidation.transCurrency();
            salesOrderValidation.transactionSaveCheck();
        }

        @AfterTest
        public void afterTest(){
            appLogin.logout();
        }
    }

package menuItems.finance.transactions.Payments;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Payments.SalaryPayments;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalaryPayments {

        WindowsDriver driver;
        AppLogin appLogin = new AppLogin();
        String file = "./src/main/resources/menuItems/finance/transaction/475812 - Salary Payments-AC_SP_1.xls";

        @BeforeTest
        public void beforeTest() throws IOException, InterruptedException, ParseException {
            driver = appLogin.login();
        }

        @Test
        public void salaryPayments() throws InterruptedException, AWTException, IOException, ParseException {
            SalaryPayments salaryPayments=new SalaryPayments(driver,file);
            salaryPayments.salaryPayments();
        }

        @AfterTest
        public void afterTest() throws IOException {
        appLogin.logout();
        }
    }
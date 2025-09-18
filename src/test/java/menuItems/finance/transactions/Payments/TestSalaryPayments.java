package menuItems.finance.transactions.Payments;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Payments.SalaryPayments;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestSalaryPayments {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BODY_SALARY_PAYMENTS="./output/temp_api_request_bodies/salaryPayments.json";
    private static final String API_RESPONSE_SALARY_PAYMENTS="./output/api_responses/salaryPayments.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/finance/transaction/475812 - Salary Payments-AC_SP_1_Output.xls";

    String file = "./src/main/resources/menuItems/finance/transaction/475812 - Salary Payments-AC_SP_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void salaryPayments() throws Exception {
        SalaryPayments salaryPayments=new SalaryPayments(driver,file);
        salaryPayments.salaryPayments(TEMP_API_BODY_SALARY_PAYMENTS,API_RESPONSE_SALARY_PAYMENTS,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
package menuItems.finance.transactions.Receipts;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Receipts.ReceiptsFromCreditCardCompanies;
import java.awt.*;
import java.io.IOException;

public class ReceiptsFromCreditCardCompaniesTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile="./src/main/resources/menuItems/finance/transaction/receiptsFromCCCompany.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test- Receipts From Credit Card Companies");
    }

    @Test
    public void receiptsFromCCCompany() throws InterruptedException, AWTException, IOException, ParseException {
        ReceiptsFromCreditCardCompanies creditCardCompanies=new ReceiptsFromCreditCardCompanies(driver,dataFile);
        creditCardCompanies.creditCardCompanyReceipt();
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
        Allure.step("After Test- Receipts From Credit Card Companies");
    }
}
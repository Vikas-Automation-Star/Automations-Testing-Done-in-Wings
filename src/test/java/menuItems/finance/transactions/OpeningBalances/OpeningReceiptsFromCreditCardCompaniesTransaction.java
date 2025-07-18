package menuItems.finance.transactions.OpeningBalances;

import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.OpeningBalances.OpeningReceiptsFromCreditCardCompanies;
import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class OpeningReceiptsFromCreditCardCompaniesTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/finance/transaction/openingReceiptsFromCCC.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void openingReceipts() throws IOException, ParseException, InterruptedException, AWTException {
        OpeningReceiptsFromCreditCardCompanies receiptsFromCreditCardCompanies = new OpeningReceiptsFromCreditCardCompanies(driver, dataFile);
        receiptsFromCreditCardCompanies.openingReceipts();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
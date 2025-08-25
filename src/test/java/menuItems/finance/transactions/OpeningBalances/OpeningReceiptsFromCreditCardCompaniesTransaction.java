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
    private static final String TEMP_API_BODY_OPENINGRECEIPTS_FROM_CCC="./output/temp_api_request_bodies/openingReceiptsFromCCC.json";
    private static final String API_RESPONSE_OPENINGRECEIPTS_FROM_CCC="./output/api_responses/openingReceiptsFromCCC.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/finance/transaction/460910 - Opening Receipts from Credit Card Companies-AC_ORFCC_1_Output.xls";
    String dataFile = "./src/main/resources/menuItems/finance/transaction/460910 - Opening Receipts from Credit Card Companies-AC_ORFCC_1.xls";

    @BeforeTest
    public void beforeTest() throws Exception {
        driver = appLogin.login();
    }

    @Test
    public void openingReceipts() throws Exception, AWTException {
        OpeningReceiptsFromCreditCardCompanies receiptsFromCreditCardCompanies = new OpeningReceiptsFromCreditCardCompanies(driver, dataFile);
        receiptsFromCreditCardCompanies.openingReceipts(TEMP_API_BODY_OPENINGRECEIPTS_FROM_CCC,API_RESPONSE_OPENINGRECEIPTS_FROM_CCC,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
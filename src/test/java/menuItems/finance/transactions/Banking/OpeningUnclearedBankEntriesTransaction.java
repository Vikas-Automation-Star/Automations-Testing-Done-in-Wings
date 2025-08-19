package menuItems.finance.transactions.Banking;

import io.appium.java_client.windows.WindowsDriver;

import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Banking.OpeningUnclearedBankEntries;
import java.awt.*;
import java.io.IOException;

public class OpeningUnclearedBankEntriesTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/menuItems/finance/transaction/458165 - Opening Uncleared Bank Entries-AC_OUCBE_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.login();
    }

    @Test
    public void openingUnclearedBankEntries() throws IOException, ParseException, InterruptedException, AWTException {
        OpeningUnclearedBankEntries unclearedBankEntries = new OpeningUnclearedBankEntries(driver, dataFile);
        unclearedBankEntries.unclearedBankEntries();
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
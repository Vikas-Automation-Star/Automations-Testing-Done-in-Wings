package menuItems.finance.transactions.Banking;

import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Banking.InterBankFundTransfers;
import java.io.IOException;

public class InterBankFundTransferTransaction {
    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();

    private static final String TEMP_API_BODY_INTER_BANKFUND_TRANSFER="./output/temp_api_request_bodies/interBankFundTransfer.json";
    private static final String API_RESPONSE_INTER_BANKFUND_TRANSFER="./output/api_responses/interBankFundTransfer.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/finance/transaction/482403 - Inter Bank Fund Transfers-AC_BFT_1_Output.xls";

    String dataFile="./src/main/resources/menuItems/finance/transaction/482403 - Inter Bank Fund Transfers-AC_BFT_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver=appLogin.login();
    }

    @Test
    public void bankFundTransfer() throws IOException, ParseException, InterruptedException {
        InterBankFundTransfers bankFundTransfers=new InterBankFundTransfers(driver,dataFile);
        bankFundTransfers.bankFundTransfer(TEMP_API_BODY_INTER_BANKFUND_TRANSFER,API_RESPONSE_INTER_BANKFUND_TRANSFER,OUTPUT_FILE);
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
    }
}
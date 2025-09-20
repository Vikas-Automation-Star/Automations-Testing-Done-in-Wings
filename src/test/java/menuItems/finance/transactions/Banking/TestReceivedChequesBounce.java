package menuItems.finance.transactions.Banking;

import com.wings.pages.finance.transactions.Banking.ReceivedChequesBounce;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import java.io.IOException;

public class TestReceivedChequesBounce {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_RECEIVED_CHEQUES_BOUNCE="./output/temp_api_request_bodies/receivedChequesBounce.json";
    private static final String API_RESPONSE_RECEIVED_CHEQUES_BOUNCE="./output/api_responses/receivedChequesBounce.json";
    private static final String OUTPUT_FILE_RECEIVED_CHEQUES_BOUNCE="./src/main/resources/menuItems/finance/transaction/460820 - Received Cheques Bounce-AC_CBR_3_Output.xls";
    String dataFile = "./src/main/resources/menuItems/finance/transaction/460820 - Received Cheques Bounce-AC_CBR_3.xls";


    @BeforeTest
    public void beforeTest() throws Exception {
        driver = appLogin.login();
    }

    @Test
    public void chequesBounce() throws Exception {
        ReceivedChequesBounce chequesBounce = new ReceivedChequesBounce(driver, dataFile);
        chequesBounce.receivedCheckBounce("CBI 3",TEMP_API_RECEIVED_CHEQUES_BOUNCE,API_RESPONSE_RECEIVED_CHEQUES_BOUNCE,OUTPUT_FILE_RECEIVED_CHEQUES_BOUNCE);
    }

    @AfterTest
    public void afterTest() throws IOException {
//        appLogin.logout();
    }
}
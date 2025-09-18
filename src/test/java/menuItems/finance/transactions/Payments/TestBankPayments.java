package menuItems.finance.transactions.Payments;

import com.wings.pages.purchase.transactions.PurchaseVoucher;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Payments.BankPayment;
import java.io.IOException;

public class TestBankPayments {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_BANK_PAYMENTS="./output/temp_api_request_bodies/bankPayments.json";
    private static final String API_RESPONSE_BANK_PAYMENTS="./output/api_responses/bankPayments.json";
    private static final String OUTPUT_FILE_BANK_PAYMENTS="./src/main/resources/menuItems/finance/transaction/458284 - Bank Payments-AC_BP_2_Output.xls";

    private static final String TEMP_API_BODY_PURCHASE_VOUCHERS="./output/temp_api_request_bodies/PurchaseVouchers.json";
    private static final String API_RESPONSE_PURCHASE_VOUCHERS="./output/api_responses/PurchaseVouchers.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9_Output.xlsx";

    String purchaseFile = "./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9.xlsx";
    String file = "./src/main/resources/menuItems/finance/transaction/458284 - Bank Payments-AC_BP_2.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void bankPayment() throws Exception {
        PurchaseVoucher purchaseVoucher=new PurchaseVoucher(driver,purchaseFile);
        String voucehrNum=purchaseVoucher.purchaseVoucher(TEMP_API_BODY_PURCHASE_VOUCHERS,API_RESPONSE_PURCHASE_VOUCHERS,OUTPUT_FILE);

        appLogin.logout();
        driver=appLogin.login();

        BankPayment bankPayment = new BankPayment(driver, file);
        bankPayment.bankPayment(voucehrNum,TEMP_API_BANK_PAYMENTS,API_RESPONSE_BANK_PAYMENTS,OUTPUT_FILE_BANK_PAYMENTS);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
package menuItems.finance.transactions.Payments;

import com.wings.pages.purchase.transactions.PurchaseVoucher;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.finance.transactions.Payments.CashPayments;
import java.io.IOException;

public class TestCashPayments {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_CASH_PAYMENTS="./output/temp_api_request_bodies/cashPayments.json";
    private static final String API_RESPONSE_CASH_PAYMENTS="./output/api_responses/cashPayments.json";
    private static final String OUTPUT_FILE_CASH_PAYMENTS="./src/main/resources/menuItems/finance/transaction/459470 - Cash Payments-AC_CP_1_Output.xls";

    private static final String TEMP_API_BODY_PURCHASE_VOUCHERS="./output/temp_api_request_bodies/PurchaseVouchers.json";
    private static final String API_RESPONSE_PURCHASE_VOUCHERS="./output/api_responses/PurchaseVouchers.json";
    private static final String OUTPUT_FILE="./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9_Output.xlsx";

    String purchaseFile = "./src/main/resources/menuItems/purchase/transactions/479081 - Purchase Vouchers-AC_PV_9.xlsx";
    String file = "./src/main/resources/menuItems/finance/transaction/459470 - Cash Payments-AC_CP_1.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void cashPayment() throws Exception {
        PurchaseVoucher purchaseVoucher=new PurchaseVoucher(driver,purchaseFile);
        String voucehrNum=purchaseVoucher.purchaseVoucher(TEMP_API_BODY_PURCHASE_VOUCHERS,API_RESPONSE_PURCHASE_VOUCHERS,OUTPUT_FILE);

        appLogin.logout();
        driver=appLogin.login();

        CashPayments cashPayments = new CashPayments(driver, file);
        cashPayments.cashPayment(voucehrNum,TEMP_API_CASH_PAYMENTS,API_RESPONSE_CASH_PAYMENTS,OUTPUT_FILE_CASH_PAYMENTS);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}
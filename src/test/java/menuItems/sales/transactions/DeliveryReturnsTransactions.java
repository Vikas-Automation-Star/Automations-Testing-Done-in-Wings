package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.Deliveries;
import com.wings.pages.sales.transactions.DeliveryReturns;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class DeliveryReturnsTransactions {

    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();

    private static final String TEMP_API_SALES_DELIVERIES="./output/temp_api_request_bodies/deliveries.json";
    private static final String API_RESPONSE_SALES_DELIVERIES="./output/api_responses/deliveries.json";
    private static final String OUTPUT_FILE_SALES_DELIVERIES="./src/main/resources/menuItems/Sales/Transactions/476979 - Deliveries-AC_Output.xls";

    private static final String TEMP_API_SALES_DELIVERY_RETURNS="./output/temp_api_request_bodies/deliveryReturns.json";
    private static final String API_RESPONSE_SALES_DELIVERY_RETURNS="./output/api_responses/deliveryReturns.json";
    private static final String OUTPUT_FILE_SALES_DELIVERY_RETURNS="./src/main/resources/menuItems/Sales/Transactions/458761 - Delivery Returns-AC_Output.xls";

    String dataFile1="./src/main/resources/menuItems/Sales/Transactions/476979 - Deliveries-AC.xls";
    String dataFile = "./src/main/resources/menuItems/Sales/Transactions/458761 - Delivery Returns-AC.xls";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.login();
    }

    @Test
    public void deliveryReturns() throws IOException, InterruptedException, ParseException, AWTException {
        Deliveries deliveries=new Deliveries(driver,dataFile1);
        String deliveryVoucher=deliveries.salesDeliveries(TEMP_API_SALES_DELIVERIES,API_RESPONSE_SALES_DELIVERIES,OUTPUT_FILE_SALES_DELIVERIES);

        appLogin.logout();
        driver=appLogin.login();

        DeliveryReturns deliveryReturns = new DeliveryReturns(driver, dataFile);
        deliveryReturns.deliveryreturns(deliveryVoucher,TEMP_API_SALES_DELIVERY_RETURNS,API_RESPONSE_SALES_DELIVERY_RETURNS,OUTPUT_FILE_SALES_DELIVERY_RETURNS);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }
}

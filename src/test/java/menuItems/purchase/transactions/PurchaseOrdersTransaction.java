package menuItems.purchase.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.purchase.transactions.PurchaseOrder;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PurchaseOrdersTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/purchase/transactions/PurchaseOrders.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test PurchaseOrder Transaction");
    }

    @Test
    public void purchaseOrders() throws IOException, ParseException, InterruptedException, NoSuchMethodException {
        PurchaseOrder pr = new PurchaseOrder(driver, file);
        pr.purchaseOrder();
//        Method[] method=pr.getClass().getDeclaredMethods();
//        for(Method method1:method){
//            System.out.println("calling methode"+method1.getName());
//        }
//        String callingMethode =method.getName();
//        System.out.println("callingMethode methode :"+ callingMethode);
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
        Allure.step("After Test PurchaseOrder Transaction");

    }
}
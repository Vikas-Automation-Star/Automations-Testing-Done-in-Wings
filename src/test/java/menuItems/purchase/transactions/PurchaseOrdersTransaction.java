package menuItems.purchase.transactions;

import com.wings.pages.purchase.transactions.PurchaseOrder;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

public class PurchaseOrdersTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/purchase/transactions/PurchaseOrders.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void purchaseOrders() throws IOException, ParseException, InterruptedException, NoSuchMethodException {
        PurchaseOrder pr=new PurchaseOrder(driver,file);
        pr.purchaseOrder();
//        Method[] method=pr.getClass().getDeclaredMethods();
//        for(Method method1:method){
//            System.out.println("calling methode"+method1.getName());
//        }
//        String callingMethode =method.getName();
//        System.out.println("callingMethode methode :"+ callingMethode);
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
    }
}

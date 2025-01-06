package menuItems.purchase.transactions;

import com.wings.pages.purchase.transactions.PurchaseVoucher;
import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PurchaseVouchersTransaction  {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file = "./src/main/resources/MenuItems/purchase/transactions/PurchaseVouchers.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test PurchaseVouchers Transaction");
    }

    @Test
    public void purchaseVouchers() throws IOException, ParseException, InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        PurchaseVoucher po=new PurchaseVoucher(driver,file);
        po.purchaseVoucher();
//        Method method = po.getClass().getMethod("purchaseVoucher");
//        String callerMethodName = method.getName();
//        System.out.println("Caller method: " + callerMethodName);
    }

    @AfterTest
    public void afterTest(){
        appLogin.logout();
        Allure.step("After Test PurchaseVouchers Transaction");
    }

}

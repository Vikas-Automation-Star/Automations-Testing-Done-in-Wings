package menuItems.sales.transactions;

import io.appium.java_client.windows.WindowsDriver;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.ProformaSalesInvoice;

import java.io.IOException;

public class ProformaSalesInvoiceTransaction {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String file="./src/main/resources/menuItems/Sales/Transactions/proformasales.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
        Allure.step("Before Test Proforma Sales Invoice");
    }

    @Test
    public void proformaSalesInvoice() throws IOException, ParseException, InterruptedException {
        ProformaSalesInvoice sales=new ProformaSalesInvoice(driver,file);
        sales.salesProforma();
    }

    @AfterTest
    public void afterTest() throws IOException{
        appLogin.logout();
        Allure.step("After Test Proforma Sales Invoice");
    }
}
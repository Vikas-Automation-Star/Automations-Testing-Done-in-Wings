package menuItems.sales.transactions;

import com.wings.pages.AppLogin;
import com.wings.pages.sales.transactions.*;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.*;
import java.io.IOException;

public class TestSalesReturnWIR_SIADUnRegInterInclusive {

    WindowsDriver driver;
    AppLogin appLogin=new AppLogin();
    Common common;
    String dataFile="./src/main/resources/menuItems/Sales/Transactions/salesEnquiry.json";

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        common=new Common(driver);
        driver=appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(dataFile,"salesEnquiry","userName"),common.getData(dataFile,"salesEnquiry","password"));
    }

    @Test
    public void siad_UnRegInterInclusiveWithInvoice() throws IOException, ParseException, InterruptedException, AWTException {
//        SalesOrders salesOrders=new SalesOrders(driver,dataFile);
//        String salesOrderVoucher=salesOrders.salesOrders();
//
//        appLogin.logout();
//        driver = appLogin.launchSingleUserApp();
//        appLogin.singleUserLogin(common.getData(dataFile,"salesEnquiry","userName"),common.getData(dataFile,"salesEnquiry","password"));
//
//        DeliveriesAgainstOrdersTransaction deliveriesAgainstOrdersTransaction=new DeliveriesAgainstOrdersTransaction(driver,dataFile);
//        String deliveriesAgainstOrdersVoucher=deliveriesAgainstOrdersTransaction.deliveriesAgainstOrders(salesOrderVoucher);
//
//        appLogin.logout();
//        driver = appLogin.launchSingleUserApp();
//        appLogin.singleUserLogin(common.getData(dataFile,"salesEnquiry","userName"),common.getData(dataFile,"salesEnquiry","password"));
//
//        SalesInvoiceAgainstDeliveries salesInvoiceAgainstDeliveries=new SalesInvoiceAgainstDeliveries(driver,dataFile);
//        String salesInvoiceAgainstDelVoucher=salesInvoiceAgainstDeliveries.salesInvoiceAgainstDeliveries(deliveriesAgainstOrdersVoucher);
//
//        appLogin.logout();
//        driver = appLogin.launchSingleUserApp();
//        appLogin.singleUserLogin(common.getData(dataFile,"salesEnquiry","userName"),common.getData(dataFile,"salesEnquiry","password"));
//
//        SalesReturnWithInvoiceReference salesReturnWithInvoiceReference=new SalesReturnWithInvoiceReference(driver,dataFile);
//        salesReturnWithInvoiceReference.salesReturnWithInvoiceReference(salesInvoiceAgainstDelVoucher);

//            SalesOrder_UnRegInterInclusive unRegInterInclusive =new SalesOrder_UnRegInterInclusive(driver,dataFile);
//            DeliveriesAgainstOrders_UnRegInterInclusive againstOrdersUnRegInterInclusive=new DeliveriesAgainstOrders_UnRegInterInclusive(driver,dataFile);
//            String []deliveriesAgainstOrders=againstOrdersUnRegInterInclusive.interInclusiveUnRegDeliveries(unRegInterInclusive.interInclusiveUnReg()[1]);
//            SalesInvoiceAgainstDeliveries_UnRegInterInclusive againstDeliveries_unRegInterInclusive=new SalesInvoiceAgainstDeliveries_UnRegInterInclusive(driver,dataFile);
//            String []againstDeliveries=againstDeliveries_unRegInterInclusive.UnRegInterInclusiveSIAD(deliveriesAgainstOrders[1]);
//            SRWIR_SIADUnRegInterInclusive srwirSiadUnRegInterInclusive=new SRWIR_SIADUnRegInterInclusive(driver,dataFile);
//            srwirSiadUnRegInterInclusive.unRegInclusiveInterInvoiceRef_SIAD(againstDeliveries[1]);



        SalesInvoice salesInvoice=new SalesInvoice(driver,dataFile);
        salesInvoice.salesInvoice();

    }

    @AfterTest
    public void afterTest() throws IOException {
//            appLogin.logout();
    }
}
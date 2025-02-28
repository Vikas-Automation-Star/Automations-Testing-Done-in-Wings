package phase_1_TestCases;

import com.wings.pages.AppLogin;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class VoucherTypesAndSeries {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/phase_1_List/vouchersAndTypes.json";
    Common common;

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test
    public void voucherTypes() throws InterruptedException, AWTException, IOException, ParseException {
        VoucherSeriesAndTypes types = new VoucherSeriesAndTypes(driver, dataFile);
        String voucherID = types.setVouchersTypes("SI", "//MenuItem[@Name='Sales']", "//MenuItem[@Name='Invoices']", "//Menu/MenuItem[@Name='Sales Invoices']", "SI", "//TabItem[@Name='Sales Invoices']/Button[@Name='Close']");
        System.out.println("givenVoucherID :" + voucherID);
        appLogin.logout();
        driver = appLogin.launchSingleUserApp();
        common = new Common(driver);
        appLogin.singleUserLogin();
        Thread.sleep(1500);
        VoucherSeriesAndTypes seriesAndTypes = new VoucherSeriesAndTypes(driver, dataFile);
        String TransactionVoucherID = seriesAndTypes.validateVoucherTypes("//MenuItem[@Name='Sales']", "//MenuItem[@Name='Invoices']", "//Menu/MenuItem[@Name='Sales Invoices']");
        System.out.println("expectedTransactionVoucherID :" + TransactionVoucherID);
        Assert.assertEquals(voucherID, TransactionVoucherID, "both vouchers are not same please check again");
    }

    //    @Test
    public void manualVoucherSeries() throws IOException, ParseException, InterruptedException {
        VoucherSeriesAndTypes manualVS = new VoucherSeriesAndTypes(driver, dataFile);
        manualVS.setManualVoucherSeries("//MenuItem[@Name='Sales']", "//MenuItem[@Name='Enquiries']", "//Menu/MenuItem[@Name='Sales Enquiries']", "//TabItem[@Name='Sales Enquiries']/Button[@Name='Close']");
    }

    //    @Test
    public void multipleVoucherSeries() throws IOException, ParseException, InterruptedException {
        VoucherSeriesAndTypes multipleVT = new VoucherSeriesAndTypes(driver, dataFile);
        multipleVT.setMultipleVoucherSeries("Sales", "Enquiries", "Sales Enquiries", "Sales Enquiries");
    }

    @AfterTest
    public void afterTest() throws IOException {
        appLogin.logout();
    }

}

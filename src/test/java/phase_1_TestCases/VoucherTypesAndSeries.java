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
import java.util.List;

public class VoucherTypesAndSeries {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/phase_1_List/voucherTypesAndSeries.json";
    Common common;

    @BeforeTest
    public void beforeTest() throws IOException, InterruptedException, ParseException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }
    @Test(priority = 1)
    public void voucherTypes() throws InterruptedException, AWTException, IOException, ParseException {
        VoucherSeriesAndTypes types = new VoucherSeriesAndTypes(driver, dataFile);
        List<String> voucherIdAndSeries = types.setVouchersTypes("AT-SE","Sales Enquiry", "Sales", "Enquiries", "//Menu/MenuItem[@Name='Sales Enquiries']", "//TabItem[@Name='Sales Enquiries']/Button[@Name='Close']");
        String voucher=voucherIdAndSeries.get(0);
        String series=voucherIdAndSeries.get(1);
        appLogin.logout();
        driver = appLogin.launchSingleUserApp();
        common = new Common(driver);
        appLogin.singleUserLogin();
        VoucherSeriesAndTypes seriesAndTypes = new VoucherSeriesAndTypes(driver, dataFile);
        List<String> TransactionVoucherTypeAndSeries = seriesAndTypes.validateVoucherTypeAndSeries("Sales", "Enquiries", "//Menu/MenuItem[@Name='Sales Enquiries']","//TabItem[@Name='Sales Enquiries']/Button[@Name='Close']");
        String voucherType= TransactionVoucherTypeAndSeries.get(0);
        String voucherSeries= TransactionVoucherTypeAndSeries.get(1);
        Assert.assertEquals(voucher, voucherType, "both vouchers are not same please check again");
        Assert.assertTrue(voucherSeries.contains(series),"both vouchers series are not same please check again");
        appLogin.logout();
    }
    @Test(priority = 2)
    public void manualVoucherSeries() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        common = new Common(driver);
        appLogin.singleUserLogin();
        VoucherSeriesAndTypes manualVS = new VoucherSeriesAndTypes(driver, dataFile);
        manualVS.setManualVoucherSeries();
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

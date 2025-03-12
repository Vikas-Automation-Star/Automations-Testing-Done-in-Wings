package phase_1_TestCases;

import com.wings.pages.AppLogin;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TransactionFeatureConfig {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    String dataFile = "./src/main/resources/phase_1_List/transactionFeatureConfiguration.json";

    @BeforeTest
    public void beforeTest() throws IOException, ParseException, InterruptedException {
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin();
    }

    @Test(priority = 1)
    public void draftTransaction() throws IOException, ParseException, InterruptedException {
        TransactionFeatures features=new TransactionFeatures(driver,dataFile);
        features.saveAsDraft();
    }

    @Test(priority = 2)
    public void holdTransaction() throws IOException, ParseException, InterruptedException {
        TransactionFeatures features=new TransactionFeatures(driver,dataFile);
        features.holdTransaction();
    }

    @Test(priority = 3)
    public void editTransaction() throws IOException, ParseException, InterruptedException {
        TransactionFeatures editTrans=new TransactionFeatures(driver,dataFile);
        editTrans.editTransaction();
    }

    @Test(priority = 4)
    public void addToFavourite() throws IOException, ParseException, InterruptedException {
        TransactionFeatures addToFav =new TransactionFeatures(driver,dataFile);
        addToFav.addToFavourites();
    }

    @Test(priority = 5)
    public void saveAsTemplate() throws IOException, ParseException, InterruptedException {
        TransactionFeatures template=new TransactionFeatures(driver,dataFile);
        template.saveAsTemplate();
    }


    @AfterTest
    public void afterTest() throws IOException {
            appLogin.logout();
    }
}
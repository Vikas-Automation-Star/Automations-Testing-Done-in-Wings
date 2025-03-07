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

    @Test
    public void draftTransaction() throws IOException, ParseException, InterruptedException {
        TransactionFeatures features=new TransactionFeatures(driver,dataFile);
        features.saveAsDraft();
    }

    @Test
    public void holdTransaction() throws IOException, ParseException, InterruptedException {
        TransactionFeatures features=new TransactionFeatures(driver,dataFile);
        features.holdTransaction();
    }

    @Test
    public void saveAsTemplate() throws IOException, ParseException, InterruptedException {
        TransactionFeatures template=new TransactionFeatures(driver,dataFile);
        template.saveAsTemplate();
    }

    @Test
    public void editTransaction() throws IOException, ParseException, InterruptedException {
        TransactionFeatures editTrans=new TransactionFeatures(driver,dataFile);
        editTrans.editTransaction();
    }

    @Test
    public void addToFavourite() throws IOException, ParseException, InterruptedException {
        TransactionFeatures addToFav =new TransactionFeatures(driver,dataFile);
        addToFav.addToFavourites();
    }


    @AfterTest
    public void afterTest() throws IOException {
            appLogin.logout();
    }
}
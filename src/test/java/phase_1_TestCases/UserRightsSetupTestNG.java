package phase_1_TestCases;

import com.wings.pages.AppLogin;
import com.wings.utils.Common;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;


public class UserRightsSetupTestNG {
    WindowsDriver driver;
    AppLogin appLogin = new AppLogin();
    Common common;
    String dataFile = "./src/main/resources/phase_1_List/userRightsSetup.json";
    
    @BeforeTest
    public void beforeTest() throws Exception {
        common=new Common(driver);
        driver = appLogin.launchSingleUserApp();
        appLogin.singleUserLogin(common.getData(dataFile,"superUser"), common.getData(dataFile,"password"));
    }

    @Test(priority = 1)
    public void assignPasswordAndVerify() throws Exception {
        System.out.println("PRIORITY-1");
        UserRightsSetup assignPassword = new UserRightsSetup(driver, dataFile, appLogin);
        assignPassword.userCreationAssignPassword();
        appLogin.logout();
    }

    @Test(priority = 2)
    public void grantRevokeAccessMaster() throws IOException, InterruptedException, ParseException {
        System.out.println("PRIORITY-2");
        //grant access
        //use below lines(till singleUserLogin) while doing suite execution, if individually, no need.
        driver=appLogin.launchSingleUserApp();
        common=new Common(driver);
        appLogin.singleUserLogin(common.getData(dataFile,"superUser"), common.getData(dataFile,"password") );
        UserRightsSetup grantAccessMaster =new UserRightsSetup(driver,dataFile,appLogin);
        grantAccessMaster.grantAccessForMaster();
        appLogin.logout();
        //check granted access
        driver=appLogin.launchSingleUserApp();
        common=new Common(driver);
        appLogin.singleUserLogin(common.getData(dataFile,"user"), common.getData(dataFile,"password") );
        UserRightsSetup checkAccessMaster =new UserRightsSetup(driver,dataFile,appLogin);
        checkAccessMaster.checkGrantForMaster();
        appLogin.logout();
        //revoke access
        driver=appLogin.launchSingleUserApp();
        common=new Common(driver);
        appLogin.singleUserLogin(common.getData(dataFile,"superUser"), common.getData(dataFile,"password") );
        UserRightsSetup revokeAccessMaster=new UserRightsSetup(driver,dataFile,appLogin);
        revokeAccessMaster.revokeAccessForMaster();
        appLogin.logout();
        //check revoke access
        driver=appLogin.launchSingleUserApp();
        common=new Common(driver);
        appLogin.singleUserLogin(common.getData(dataFile,"user"), common.getData(dataFile,"password"));
        UserRightsSetup checkRevokeMaster=new UserRightsSetup(driver,dataFile,appLogin);
        checkRevokeMaster.checkRevokeAccessForMaster();
        Thread.sleep(1500);
        appLogin.logout(); //this logout isn't working
    }

    @Test(priority = 3)
    public void grantRevokeAccessTransaction() throws InterruptedException, IOException, ParseException {
        System.out.println("PRIORITY-3");
        //grantAccess
        //use below lines(till singleUser Login) while doing suite execution, if individually, no need.
        driver=appLogin.launchSingleUserApp();
        common=new Common(driver);
        appLogin.singleUserLogin(common.getData(dataFile,"superUser"), common.getData(dataFile,"password") );
        UserRightsSetup transactionGrant=new UserRightsSetup(driver,dataFile,appLogin);
        transactionGrant.grantAccessForTransactions();
        appLogin.logout();
        //check Grant
        driver=appLogin.launchSingleUserApp();
        common=new Common(driver);
        appLogin.singleUserLogin(common.getData(dataFile,"user"), common.getData(dataFile,"password"));
        UserRightsSetup checkGrant=new UserRightsSetup(driver,dataFile,appLogin);
        checkGrant.checkGrantForTransaction();
        appLogin.logout();
        //revoke grant
        driver=appLogin.launchSingleUserApp();
        common=new Common(driver);
        appLogin.singleUserLogin(common.getData(dataFile,"superUser"), common.getData(dataFile,"password"));
        UserRightsSetup revokeRights=new UserRightsSetup(driver,dataFile,appLogin);
        revokeRights.revokeRightsForTransaction();
        appLogin.logout();
        //check revoke access
        driver=appLogin.launchSingleUserApp();
        common=new Common(driver);
        appLogin.singleUserLogin(common.getData(dataFile,"user"), common.getData(dataFile,"password") );
        UserRightsSetup checkRevoke=new UserRightsSetup(driver,dataFile,appLogin);
        checkRevoke.checkRevokeAccessForTransaction();
        appLogin.logout();
    }

    @Test(priority = 4)
    public void grantRevokeAccessReport() throws Exception {
        System.out.println("PRIORITY-4");
        //grantAccess
        //use below lines(till singleUser Login) while doing suite execution, if individually, no need.
        driver=appLogin.launchSingleUserApp();
        common=new Common(driver);
        appLogin.singleUserLogin(common.getData(dataFile,"superUser"), common.getData(dataFile,"password") );
        UserRightsSetup grantAccess =new UserRightsSetup(driver,dataFile,appLogin);
        grantAccess.grantUserRightsForReports();
        appLogin.logout();
        //check granted access
        driver=appLogin.launchSingleUserApp();
        common=new Common(driver);
        appLogin.singleUserLogin(common.getData(dataFile,"user"), common.getData(dataFile,"password"));
        UserRightsSetup checkGrant =new UserRightsSetup(driver,dataFile,appLogin);
        checkGrant.checkGrantAccessForReports();
        appLogin.logout();
        //revoke Access
        driver=appLogin.launchSingleUserApp();
        common=new Common(driver);
        appLogin.singleUserLogin(common.getData(dataFile,"superUser"), common.getData(dataFile,"password"));
        UserRightsSetup revokeRights =new UserRightsSetup(driver,dataFile,appLogin);
        revokeRights.revokeUserRightsForReports();
        appLogin.logout();
        //check revoked access
        driver=appLogin.launchSingleUserApp();
        common=new Common(driver);
        appLogin.singleUserLogin(common.getData(dataFile,"user"), common.getData(dataFile,"password"));
        UserRightsSetup checkRevoke =new UserRightsSetup(driver,dataFile,appLogin);
        checkRevoke.checkRevokeAccessForReports();
        appLogin.logout();
    }

//    @Test(priority = 5)
    public void grantRevokeAccessMasterProperties() throws Exception {
        System.out.println("PRIORITY -5");
        //grant Access
        //use below lines in suite execution, if single, no need
        driver=appLogin.launchSingleUserApp();
        common=new Common(driver);
        appLogin.singleUserLogin(common.getData(dataFile,"superUser"), common.getData(dataFile,"password") );
        UserRightsSetup propertiesSetup=new UserRightsSetup(driver,dataFile,appLogin);
        propertiesSetup.grantAccessForMasterProperties();
        appLogin.logout();
        //check grant
        driver=appLogin.launchSingleUserApp();
        common=new Common(driver);
        appLogin.singleUserLogin(common.getData(dataFile,"user"), common.getData(dataFile,"password"));
//        UserRightsSetup checkGrant=new UserRightsSetup(driver,dataFile);
//        checkGrant.checkGrantForMasterProperties();

    }
    @AfterTest
    public void afterTest() throws IOException {
        System.out.println("after test method");
        appLogin.logout();
    }
}
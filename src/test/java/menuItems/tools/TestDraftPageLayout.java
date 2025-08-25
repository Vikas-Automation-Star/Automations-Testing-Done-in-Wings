package menuItems.tools;

import com.wings.pages.AppLogin;
import com.wings.pages.tools.formsAndPrintTemplates.dotMatrixPrinters.DraftPageLayout;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestDraftPageLayout {

        WindowsDriver driver;
        AppLogin appLogin = new AppLogin();
        String file = "./src/main/resources/menuItems/tools/draftPageLayout.json";

        @BeforeTest
        public void beforeTest() throws InterruptedException, IOException, ParseException {
            driver = appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
        }

        @Test
        public void draftPageLayout() throws Exception {
            DraftPageLayout draftPageLayout=new DraftPageLayout(driver,file);
            draftPageLayout.draftPageLayout();
        }

        @AfterTest
        public void afterTest() throws IOException {
            appLogin.logout();
        }
    }
package menuItems.tools;

import com.wings.pages.AppLogin;
import com.wings.pages.tools.formsAndPrintTemplates.DraftRollInvoiceDesigns;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestDraftRollInvoiceDesigns {

        WindowsDriver driver;
        AppLogin appLogin = new AppLogin();
        String file = "./src/main/resources/menuItems/tools/draftRollInvoiceDesigns.json";

        @BeforeTest
        public void beforeTest() throws InterruptedException, IOException, ParseException {
            driver = appLogin.login();
        }

        @Test
        public void draftRollInvoiceDesigns() throws Exception {
            DraftRollInvoiceDesigns draftRollInvoiceDesigns=new DraftRollInvoiceDesigns(driver,file);
            draftRollInvoiceDesigns.draftRollInvoiceDesigns();
        }

        @AfterTest
        public void afterTest() throws IOException {
            appLogin.logout();
        }
    }
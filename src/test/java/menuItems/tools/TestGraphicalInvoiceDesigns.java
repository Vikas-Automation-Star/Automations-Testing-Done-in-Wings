package menuItems.tools;

import com.wings.pages.AppLogin;
import com.wings.pages.tools.formsAndPrintTemplates.laserPrinters.GraphicalInvoiceDesigns;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestGraphicalInvoiceDesigns {

        WindowsDriver driver;
        AppLogin appLogin = new AppLogin();
        String file = "./src/main/resources/menuItems/tools/graphicalInvoiceDesigns.json";

        @BeforeTest
        public void beforeTest() throws InterruptedException, IOException, ParseException {
            driver = appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
        }

        @Test
        public void graphicalInvoiceDesigns() throws IOException, ParseException, InterruptedException {
            GraphicalInvoiceDesigns graphicalInvoiceDesigns=new GraphicalInvoiceDesigns(driver,file);
            graphicalInvoiceDesigns.graphicalInvoiceDesigns();
        }

        @AfterTest
        public void afterTest() throws IOException {
            appLogin.logout();
        }
    }
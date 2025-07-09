package Technical;

import com.wings.Technical.IncorrectCredentials;
import io.appium.java_client.windows.WindowsDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestIncorrectCredentials {
    WindowsDriver driver;
    String dataFile="./src/main/resources/Technical/technicalFeatures.json";

    @Test
    public void incorrectPassword() throws IOException, InterruptedException, ParseException {
        IncorrectCredentials incorrectCredentials=new IncorrectCredentials(driver,dataFile);
//        incorrectCredentials.incorrectPassword();
        incorrectCredentials.incorrectUserName();
    }
}

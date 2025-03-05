import com.wings.pages.AppLogin;
import com.wings.utils.FileUtil;
import com.wings.utils.StringUtil;
import io.appium.java_client.windows.WindowsDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class downloadLatestBuild_Resource {

    WindowsDriver appDriver;
    ChromeDriver driver = null;
    FileUtil fileUtil = new FileUtil();
    AppLogin appLogin=new AppLogin();
    String oldBuild,oldResource;

    @BeforeTest
    public void setUp() throws Exception {
        try {
            //login and fetch build and resource
            appDriver=appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
            appDriver.findElementByXPath("//MenuItem[@Name='Help']").click();
            appDriver.findElementByXPath("//MenuItem[@Name='About']").click();

            oldBuild= StringUtil.extractVersion(appDriver.findElementByXPath("//Text[contains(@Name,'Build')]").getText());
            System.out.println("Old Build: " + oldBuild );
            oldResource = StringUtil.extractVersion(appDriver.findElementByXPath("//Text[contains(@Name,'Resource')]").getText());
            System.out.println("Old Resource: " + oldResource);
            appDriver.findElementByXPath("//Button[@Name='OK']").click();
            appLogin.logout();

            //strta downlaoding process
            String downloadPath = System.getProperty("user.home") + "\\Downloads\\Books New Resources\\";
            System.out.println("Download Path: " + downloadPath);
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dell\\Downloads\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("--safebrowsing-disable-download-protection");
            options.addArguments("--disable-features=InsecureDownloadWarnings");
            //http://downloads.wingsoncloud.com/
            options.addArguments("--unsafely-treat-insecure-origin-as-secure=http://downloads.wingsoncloud.com");
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.default_directory", downloadPath);
            prefs.put("profile.default_content_settings.popups", 0);
            prefs.put("safebrowsing.enabled", false);
            prefs.put("download.prompt_for_download", false);
            options.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(options);
            System.out.println("Current download path: " + downloadPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() throws Exception {
        try {
            String username = "Downloads";
            String password = "@*WINGS12";
            // appending username, password with URL
            String s = "http://" + username + ":" + password + "@" + "downloads.wingsoncloud.com/downloads/";
            driver.manage().window().maximize();
            driver.navigate().to(s);
            Thread.sleep(2000);
            driver.findElement(By.linkText("misc")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText("Support Files")).click();
            Thread.sleep(2000);
            // Start downloading the files

            driver.findElementByLinkText("Build_14066.1.zip").click();
            driver.findElementByLinkText("Wings 24D Books 14K (14066.1--20637) Encrypted.zip").click();
            // Define the download paths
            String filename1 = "Build_14066.1.zip";
            String filename2 = "Wings 24D Books 14K (14066.1--20637) Encrypted.zip";

            String filepath = System.getProperty("user.home") + "\\Downloads\\Books New Resources\\" + filename1;
            String filepath1 = System.getProperty("user.home") + "\\Downloads\\Books New Resources\\" + filename2;
            File file = new File(filepath);
            File file1 = new File(filepath1);

            // Wait for the download to complete by checking the files are fully downloaded
            FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(400)).pollingEvery(Duration.ofMillis(1000));
            wait.until(x -> file.exists() && file1.exists());
            Thread.sleep(3000);
            System.out.println("Downloaded the files");
            // Unzip the downloaded files
            String target = System.getProperty("user.home") + "\\Downloads\\Books New Resources\\Target\\";
            fileUtil.unzip(filepath, target);
            fileUtil.unzip(filepath1, target);
            System.out.println("Unzipping completed successfully");

            //copying build
            FileUtil.copyFile(new File("C:\\Users\\Dell\\Downloads\\Books New Resources\\Target\\Build_14066.1\\Wings.dll"), "C:\\Program Files (x86)\\Wings Infonet\\Wings Books 24D\\Wings.dll");
            System.out.println("Build is copied successfully");
            //renaming and moving resources
            File dir = new File("C:\\Users\\Dell\\Downloads\\Books New Resources\\Target\\Wings 24D Books 14K (14066.1--20637) Encrypted");
            if (!dir.isDirectory()) {
                System.err.println("There is no directory at the given path");
            } else {
                System.out.println("Resource Files");
                String newDirName = "ResourceFiles";  // You want to rename the directory to "ResourceFiles"
                // Create a new File object with the correct path for the new directory name
                File newDir = new File(dir.getParent() + "\\" + newDirName);  // Proper concatenation of path
                System.out.println("dir name" + newDir);
                // Attempt to rename the directory
                if (newDir.exists()) {
                    System.err.println("Directory already exists: " + newDir.getAbsolutePath());
                } else {
                    if (dir.renameTo(newDir)) {
                        System.out.println("Directory renamed successfully.");
                    } else {
                        System.err.println("Failed to rename the directory.");
                    }
                }
            }
            //move ResourceFiles
            File sourceResource = new File("C:\\Users\\Dell\\Downloads\\Books New Resources\\Target\\ResourceFiles\\");
            File destResource = new File("C:\\Program Files (x86)\\Wings Infonet\\Wings Books 24D\\");
            File deleteDir = new File("C:\\Program Files (x86)\\Wings Infonet\\Wings Books 24D\\ResourceFiles\\");

            if (deleteDir.exists()) {
                FileUtils.deleteDirectory(deleteDir); //deletes the files in ResourceFiles
                System.out.println("Destination ResourceFiles deleted");
            }
            FileUtils.copyDirectoryToDirectory(sourceResource, destResource);  // Copies contents of source to dest
            System.out.println("Copied Resources Files");
            System.out.println("Test execution successfully done");
            //move FileStore.dll
            File sourceFileStore=new File("C:\\Program Files (x86)\\Wings Infonet\\Wings Books 24D\\ResourceFiles\\WingsFileStore.dll"), destFileStore=new File("C:\\Program Files (x86)\\Wings Infonet\\Wings Books 24D\\WingsFileStore.dll");
            if (destFileStore.exists()){
                destFileStore.delete();
                System.out.println("Wings File Store is already present in Root. Deleted now");
            }
            FileUtils.moveFile(sourceFileStore,destFileStore);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @AfterTest
    public void tearDown() throws Exception {
        try {
            //login and fetch latest build and resource
            appDriver=appLogin.launchSingleUserApp();
            appLogin.singleUserLogin();
            appDriver.findElementByXPath("//MenuItem[@Name='Help']").click();
            appDriver.findElementByXPath("//MenuItem[@Name='About']").click();

            String newBuild = StringUtil.extractVersion(appDriver.findElementByXPath("//Text[contains(@Name,'Build')]").getText());
            System.out.println("New Build: " + newBuild);
            String newResource = StringUtil.extractVersion(appDriver.findElementByXPath("//Text[contains(@Name,'Resource')]").getText());
            System.out.println("New Resource: " + newResource);
            appDriver.findElementByXPath("//Button[@Name='OK']").click();
            appLogin.logout();
            Assert.assertNotEquals(oldResource,newResource, "Resource/Build is not updated properly. Both are same");
            driver.close();
            driver.quit();
            System.out.println("tear down");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
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
import com.wings.utils.FileUtil;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

    public class downloadLatest {

        ChromeDriver driver = null;
        FileUtil fileUtil=new FileUtil();

        @BeforeTest
        public void setUp() throws Exception {
            try{
                String downloadPath = System.getProperty("user.dir") + "\\downloads";
                System.out.println(downloadPath);

                // Create a FileUtil object for the downloads directory
                File downloadDir = new File(downloadPath);

                // Check if the directory exists, if not, create it
                if (!downloadDir.exists()) {
                    downloadDir.mkdirs(); // Create the directory and any necessary parent directories
                }

                System.out.println(downloadPath);
                FileUtils.cleanDirectory(new File(downloadPath));
                System.setProperty("webdriver.chrome.driver",
                        "C:\\Users\\Dell\\Downloads\\chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                options.addArguments("--safebrowsing-disable-download-protection");
                options.addArguments("--disable-features=InsecureDownloadWarnings");
                //http://downloads.wingsoncloud.com/
                options.addArguments("--unsafely-treat-insecure-origin-as-secure=http://downloads.wingsoncloud.com");
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("download.default_directory", System.getProperty("user.dir")+"\\downloads");
                prefs.put("profile.default_content_settings.popups", 0);
                prefs.put("safebrowsing.enabled", false);
                prefs.put("download.prompt_for_download", false);
                options.setExperimentalOption("prefs", prefs);

                System.out.println("Before test");

                driver = new ChromeDriver(options);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        @Test
        public void test() throws Exception {
            try{


                String username = "Downloads";
                String password = "@*WINGS12";
                // appending username, password with URL
                String s = "http://"+username+":"+password+"@"+"downloads.wingsoncloud.com/downloads/";
                driver.manage().window().maximize();
                driver.navigate().to(s);
                Thread.sleep(2000);
                //driver.navigate().to("http://Downloads:@*WINGS12@downloads.wingsoncloud.com/downloads/");
                // driver.get(s);
                driver.findElement(By.linkText("Wings24D")).click();
                Thread.sleep(2000);
                driver.findElement(By.linkText("24DBooks")).click();
                Thread.sleep(2000);

//                String filename1=driver.findElement(By.linkText("14046.2_19810")).getText();
                driver.findElement(By.linkText("14046.2_19810")).click();
                Thread.sleep(2000);
                //download the 1st two files
                driver.findElementByLinkText("Build_14046.2.zip").click();
                driver.findElementByLinkText("Wings 24D Books 14K (14046.2--19810) Encrypted.zip").click();

                String filename1 ="Build_14046.2.zip";
                String filename2 ="Wings 24D Books 14K (14046.2--19810) Encrypted.zip";



//                String filename1=driver.findElement(By.xpath("(//a)[last()]")).getText();
//                driver.findElement(By.xpath("(//a)[last()]")).click();
                Thread.sleep(2000);

                String filepath=System.getProperty("user.dir")+"\\downloads\\"+ filename1;
                String filepath1=System.getProperty("user.dir")+"\\downloads\\"+ filename2;

                String target=System.getProperty("user.dir")+"\\downloads\\target";
                //FileUtil file = new FileUtil(System.getProperty("user.dir")+"\\downloads\\Build_14026_19013_24DBooks.zip");
                File file = new File(filepath);
                File file1=new File(filepath1);

                FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(300)).pollingEvery(Duration.ofMillis(1000));
                wait.until( x -> file.exists() && file1.exists());
                Thread.sleep(3000);

                fileUtil.unzip(filepath,target);
                fileUtil.unzip(filepath1,target);
                System.out.println("Test execution successfully done");
            }
            catch (Exception e)
            {
                e.printStackTrace();
                Assert.fail();
            }
        }

        @AfterTest
        public void tearDown() throws Exception {
            try{
                driver.close();
                driver.quit();
                System.out.println("tear down");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }

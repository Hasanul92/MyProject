package testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseTestCase1 {
    public static WebDriver driver;
    public static Properties prop;


    @BeforeClass(groups = {"Sanity","Regression","Master","DataDriven"})
    @Parameters({"os","browser"})
    public void setup(String os, String browser) throws IOException {
        prop = new Properties();
        try {
            InputStream input = new FileInputStream("./src/test/resources/config.properties");
            prop.load(input);
            if(prop.getProperty("execution_env").equalsIgnoreCase("remote")){
                DesiredCapabilities dcap=new DesiredCapabilities();
                // Set OS platform
                if(os.equalsIgnoreCase("Windows")){
                    dcap.setPlatform(Platform.WIN11);
                }
                else if(os.equalsIgnoreCase("mac")) {
                    dcap.setPlatform(Platform.MAC);
                }
                else {
                    System.out.println("No matching OS");
                    return;
                }

                // Set Browser
                if(browser.equalsIgnoreCase("chrome")){
                    dcap.setBrowserName("chrome");
                }
                else if (browser.equalsIgnoreCase("edge")) {
                    dcap.setBrowserName("edge");
                }
                else if (browser.equalsIgnoreCase("firefox")) {
                    dcap.setBrowserName("firefox");
                }
                else {
                    System.out.println("No matching browser");
                    return;
                }

                // Initialize remote WebDriver
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dcap);
            }

            if(prop.getProperty("execution_env").equalsIgnoreCase("local")){
                if(browser.equalsIgnoreCase("chrome")){
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                }
                else if (browser.equalsIgnoreCase("edge")) {
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                }
                else if (browser.equalsIgnoreCase("firefox")) {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.get(prop.getProperty("URL"));
            driver.manage().window().maximize();

        } catch (Exception e) {
            System.out.println("I am inside the catch block");
            System.out.println("Message is: " + e.getMessage());
        }
    }

    public String randomString() {
        return RandomStringUtils.randomAlphabetic(8);
    }

    public String randomNumber() {
        return RandomStringUtils.randomNumeric(8);
    }

    public String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphanumeric(10); // Increase the length for more randomness
    }
    public String captureScreen(String tname){
        String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
        File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePath=System.getProperty("user.dir")+"//screenshots//"+tname+"_"+timeStamp;
        File targetFile=new File(targetFilePath);
        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }
    @AfterClass(groups = {"Sanity","Regression","Master","DataDriven"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

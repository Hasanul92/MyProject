package testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseTestCase {
    public static WebDriver driver;
    public static Properties prop;


    @BeforeClass(groups = {"Sanity","Regression","Master","DataDriven"})
    public void setup() throws IOException {
//        prop = new Properties();
//        try {
//            InputStream input = new FileInputStream("./src/test/resources/config.properties");
//            prop.load(input);
//            WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver();
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//            driver.get(prop.getProperty("URL"));
//            driver.manage().window().maximize();
//
//        } catch (Exception e) {
//            System.out.println("I am inside the catch block");
//            System.out.println("Message is: " + e.getMessage());
//        }
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
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

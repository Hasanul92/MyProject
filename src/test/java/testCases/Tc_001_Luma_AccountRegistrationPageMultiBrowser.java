package testCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.LumaCreateAccountPageObjects;
import pageObjects.LumaHomePageObjects;

import java.time.Duration;

public class Tc_001_Luma_AccountRegistrationPageMultiBrowser {
    public WebDriver driver;


    @BeforeClass
    @Parameters({"browserName"})
    public void setup(String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://magento.softwaretestingboard.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void AccountRegistration() throws InterruptedException {
        // Home Page
        LumaHomePageObjects hp = new LumaHomePageObjects(driver);
        hp.clickOnCreateAccount();

        // Create Account Page
        LumaCreateAccountPageObjects ar=new LumaCreateAccountPageObjects(driver);
        ar.setFirstName(randomString());
        ar.setTxt_LastName(randomString());
        ar.setEmail(randomString()+"@gmail.com");

        // Generate one password and use it for both password fields
        String password = randomAlphaNumeric();
        ar.setPassword(password);
        ar.setConfPassword(password);
        ar.clickOnCreateAccount();
        Thread.sleep(3000);

    }
    public String randomString(){
        String generateString=RandomStringUtils.randomAlphabetic(8);
        return generateString;
    }
    public String randomNumber(){
        String generateNumber=RandomStringUtils.randomAlphanumeric(8);
        return generateNumber;
    }
    public String randomAlphaNumeric(){
        String generateString=RandomStringUtils.randomAlphabetic(5);
        String generateNumber=RandomStringUtils.randomAlphanumeric(5);
        return generateString+"@"+generateNumber;
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}

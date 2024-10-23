package testCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.LumaCreateAccountPageObjects;
import pageObjects.LumaHomePageObjects;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class Tc_001_Luma_AccountRegistrationPage {
    public WebDriver driver;
    public Properties prop;

    @BeforeClass
    public void setup(){
        prop=new Properties();
        try{
            InputStream input=new FileInputStream("./src//test//resources//config.properties");
            prop.load(input);
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.get(prop.getProperty("URL"));
            driver.manage().window().maximize();
        }
        catch(Exception e){
            System.out.println("I am inside catch block");
            System.out.println("Massege is: "+e.getMessage());
        }

    }
    @Test(groups = {"Sanity", "Master"})
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

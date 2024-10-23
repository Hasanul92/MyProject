package testCases;

import freemarker.core.CSSOutputFormat;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.LumaHomePageObjects;
import pageObjects.LumiaLoginPageObjects;
import pageObjects.LumiaMyAccountPageObjects;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class Tc_002_Lumia_LoginPage {
    public WebDriver driver;
    public Properties prop;
    @BeforeClass
    public void setup(){
        prop=new Properties();
//      FileReader file=new FileReader("./src//test//resources//config.properties");
        try {
            InputStream input=new FileInputStream("./src//test//resources//config.properties");
            prop.load(input);
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(prop.getProperty("URL"));
            driver.manage().window().maximize();
        }
        catch(Exception e){
            System.out.println("I am inside catch block");
            System.out.println("Massege is: "+e.getMessage());
        }
    }
    @Test
    public void LoginPage() throws InterruptedException {
        // Home Page
        LumaHomePageObjects hp=new LumaHomePageObjects(driver);
        hp.clickSignIn();

        // Login Page

        LumiaLoginPageObjects lp=new LumiaLoginPageObjects(driver);
        lp.setUsername(prop.getProperty("email"));
        lp.setPassword(prop.getProperty("password"));
        lp.clickOnSignin();
        Thread.sleep(5000);

        // MyAccount Page
//        LumiaMyAccountPageObjects myap=new LumiaMyAccountPageObjects(driver);
//        String targetElement=myap.getConfMsg();
//        System.out.println(targetElement);
//        if(targetElement.equalsIgnoreCase("My Account")){
//            System.out.println("Login successfully");
//            System.out.println("Title of MyAccount page: "+driver.getTitle());
//        }
//        else {
//            System.out.println("Login failed");
//        }
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}

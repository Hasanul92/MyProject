package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LumaCreateAccountPageObjects extends BasePage{
    public LumaCreateAccountPageObjects(WebDriver driver){
        super(driver);
    }
    // Locators
    By txt_FirstName= By.id("firstname");
    By txt_LastName= By.id("lastname");
    By txt_email= By.id("email_address");
    By txt_password= By.id("password");
    By txt_ConfPassword= By.id("password-confirmation");
    By button_CreateAccount= By.xpath("//button[@title='Create an Account']//span[contains(text(),'Create an Account')]");

    // Action methods
    public void setFirstName(String Fname){
        driver.findElement(txt_FirstName).sendKeys(Fname);
    }
    public void setTxt_LastName(String LName){
        driver.findElement(txt_LastName).sendKeys(LName);
    }
    public void setEmail(String email){
        driver.findElement(txt_email).sendKeys(email);
    }
    public void setPassword(String password){
        driver.findElement(txt_password).sendKeys(password);
    }
    public void setConfPassword(String confPassword){
        driver.findElement(txt_ConfPassword).sendKeys(confPassword);
    }
    public void clickOnCreateAccount(){
        driver.findElement(button_CreateAccount).click();
    }

}


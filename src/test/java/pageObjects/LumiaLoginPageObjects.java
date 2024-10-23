package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LumiaLoginPageObjects extends BasePage {
    public LumiaLoginPageObjects(WebDriver driver) {
        super(driver);
    }

    // Locators
    By txt_username = By.id("email");
    By txt_password = By.id("pass");
    By button_signin = By.xpath("//fieldset[@class='fieldset login']//span[contains(text(),'Sign In')]");

    // Action methods
    public void setUsername(String username) {
        driver.findElement(txt_username).sendKeys(username);
    }

    public void setPassword(String password) {
        driver.findElement(txt_password).sendKeys(password);
    }

    public void clickOnSignin() {
        driver.findElement(button_signin).click();
    }
}

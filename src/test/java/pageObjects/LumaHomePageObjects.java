package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LumaHomePageObjects extends BasePage {
    public LumaHomePageObjects(WebDriver driver) {
        super(driver);
    }

    // Locators
    By link_CreateAnAccount = By.xpath("//div[@class='panel header']//a[normalize-space()='Create an Account']");
    By link_Signin = By.xpath("//div[@class='panel header']//a[contains(text(),'Sign In')]");

    // Action methods
    public void clickOnCreateAccount() {
        driver.findElement(link_CreateAnAccount).click();
    }

    public void clickSignIn() {
        driver.findElement(link_Signin).click();
    }
}

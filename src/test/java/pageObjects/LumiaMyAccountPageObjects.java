package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LumiaMyAccountPageObjects extends BasePage{
    public LumiaMyAccountPageObjects(WebDriver driver){
        super(driver);
    }

    // Locators
    By txt_confirmationMsg = By.xpath("//span[text()='My Account']");
    By drpDwn = By.xpath("//div[@class='panel header']//button[@type='button']");
    By link_singout= By.xpath("//div[@aria-hidden='false']//a[normalize-space()='Sign Out']");

    // Action methods
    public Boolean getConfMsg() {
        Boolean confmsg = driver.findElement(txt_confirmationMsg).isDisplayed();
        try {
            return confmsg;
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
            return false;
        }
    }
    public void clickOnProfDrpDwn(){
        driver.findElement(drpDwn).click();
    }
    public void clickOnSignout(){
        driver.findElement(link_singout).click();
    }
}

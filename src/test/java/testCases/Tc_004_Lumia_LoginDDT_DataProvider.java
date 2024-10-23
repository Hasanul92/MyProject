package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LumaHomePageObjects;
import pageObjects.LumiaLoginPageObjects;
import pageObjects.LumiaMyAccountPageObjects;
import testBase.BaseTestCase;
import utilities.DataProviders;

import java.io.IOException;

public class Tc_004_Lumia_LoginDDT_DataProvider extends BaseTestCase {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups = "DataDriven")
    public void verifyLoginDDT(String email, String pswd, String status) throws IOException, InterruptedException {

        // Home page
        LumaHomePageObjects hp = new LumaHomePageObjects(driver);
        hp.clickSignIn();

        // Login Page
        LumiaLoginPageObjects lp = new LumiaLoginPageObjects(driver);
        lp.setUsername(email);
        lp.setPassword(pswd);
        lp.clickOnSignin();
        Thread.sleep(3000); // Replace with WebDriverWait for better practice

        // MyAccount page
        LumiaMyAccountPageObjects mac = new LumiaMyAccountPageObjects(driver);
        mac.clickOnProfDrpDwn(); // Ensure this method is implemented
        Thread.sleep(3000);
        mac.clickOnSignout(); // Ensure this method is implemented
//            Boolean status=mac.getConfMsg();

//            if(result.equalsIgnoreCase("Valid")){
//                if(status==true){
//                    Assert.assertTrue(true);
//                    mac.clickOnProfDrpDwn(); // Ensure this method is implemented
//                    Thread.sleep(3000);
//                    mac.clickOnSignout(); // Ensure this method is implemented
//                }
//                else {
//                    Assert.assertTrue(false);
//                }
//            }
//            if(result.equalsIgnoreCase("Invalid")){
//                if(status==true){
//                    mac.clickOnSignout();
//                    Assert.assertTrue(false);
//                }
//                else {
//                    Assert.assertTrue(true);
//                }
//            }
    }
}

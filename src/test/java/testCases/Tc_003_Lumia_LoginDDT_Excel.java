package testCases;

import org.testng.annotations.Test;
import pageObjects.LumaHomePageObjects;
import pageObjects.LumiaLoginPageObjects;
import pageObjects.LumiaMyAccountPageObjects; // Ensure you have this class implemented
import testBase.BaseTestCase;
import utilities.ExcelUtility;

import java.io.IOException;

public class Tc_003_Lumia_LoginDDT_Excel extends BaseTestCase {
    String path=".//testData//TestData.xlsx";
    ExcelUtility xlutil=new ExcelUtility(path);
//    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
    @Test
    public void verifyLoginDDT() throws IOException, InterruptedException {
        int totalRows=xlutil.getRowCount("Sheet1");

        // Readdata from excel
        for(int r=1;r<=totalRows;r++){
            String username=xlutil.getCellData("Steet1",r,0);
            String password=xlutil.getCellData("Sheet1",r,1);
            String expResult=xlutil.getCellData("Sheet1",r,2);

            // Home page
            LumaHomePageObjects hp = new LumaHomePageObjects(driver);
            hp.clickSignIn();

            // Login Page
            LumiaLoginPageObjects lp = new LumiaLoginPageObjects(driver);
            lp.setUsername(username);
            lp.setPassword(password);
            lp.clickOnSignin();
            Thread.sleep(3000); // Replace with WebDriverWait for better practice

            // MyAccount page
            LumiaMyAccountPageObjects mac = new LumiaMyAccountPageObjects(driver);
            mac.clickOnProfDrpDwn(); // Ensure this method is implemented
            Thread.sleep(3000);
            mac.clickOnSignout(); // Ensure this method is implemented
        }

    }
}

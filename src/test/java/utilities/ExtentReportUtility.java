package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseTestCase;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportUtility implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    String reportName;

    public void onStart(ITestContext testContext) {

//        SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.ss");
//        Date dt=new Date();
//        String currentDate=df.format(dt);

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.ss").format(new Date());
        reportName = "Test-Report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(".//reports//" + reportName);  // Specify location of the report
        sparkReporter.config().setDocumentTitle("Luma Automation Report");  // Title of report
        sparkReporter.config().setReportName("Luma Functional Testing");  // Name of report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Luma");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customer");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }
    public void onTestSuccess(ITestResult result) {
//        test = extent.createTest(result.getTestClass().getTestName());
        test = extent.createTest(result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());       //To display group in report
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }
    public void onTestFailure(ITestResult result) {
//        test = extent.createTest(result.getTestClass().getTestName());
        test = extent.createTest(result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());       //To display group in report
        test.log(Status.FAIL, result.getName() + "got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            BaseTestCase btc = new BaseTestCase();
            String imgPath = btc.captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (Exception e) {
            test.log(Status.FAIL, "Screenshot capture failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void onTestSkipped(ITestResult result) {
//        test = extent.createTest(result.getTestClass().getTestName());
        test = extent.createTest(result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());       //To display group in report
        test.log(Status.SKIP, result.getName() + "got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }
    public void onFinish(ITestContext testContext) {
        extent.flush();

        String pathOfEtentReport=System.getProperty("user.dir")+"//reports//"+reportName;
        File extentReport=new File(pathOfEtentReport);

        try{
            Desktop.getDesktop().browse(extentReport.toURI());
        }
        catch(IOException e1){
            e1.printStackTrace();
        }
    }

}

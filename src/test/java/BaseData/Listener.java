package BaseData;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listener extends BaseClass implements ITestListener {
    ExtentReports extent=ExtentReport.Report();
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
    @Override
    public void onTestStart(ITestResult result){
        System.out.println("Test Started");
        test=extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }
    @Override
    public void onTestSuccess(ITestResult result){
        test.log(Status.PASS,"testPassed");
        //System.out.println("Test success");
    }
    @Override
    public void onTestFailure(ITestResult result){
        extentTest.get().fail(result.getThrowable());
        String path="";
        try {
            path=getScreenshot(result.getMethod().getMethodName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        extentTest.get().addScreenCaptureFromPath(path,result.getMethod().getMethodName());
    }
    @Override
    public void onTestSkipped(ITestResult result){
        System.out.println("Test Skipped");
    }
    @Override
    public void onStart(ITestContext context){
        System.out.println("Test Suite Started");
    }
    @Override
    public void onFinish(ITestContext context){
        extent.flush();
        System.out.println("Test suite Success");
    }
}

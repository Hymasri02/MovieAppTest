package BaseData;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

    public static ExtentReports Report(){
        String path=System.getProperty("user.dir")+"\\report\\index.html";
        ExtentSparkReporter reporter=new ExtentSparkReporter(path);
        reporter.config().setDocumentTitle("MovieApp");
        reporter.config().setReportName("MovieAppProject");

        ExtentReports extent=new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Hymasri");
        return extent;
    }
}

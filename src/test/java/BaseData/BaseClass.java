package BaseData;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class BaseClass {
    public static WebDriver driver;

    public static WebDriver initializeDriver() throws IOException {
        Properties prop=new Properties();
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\BaseData\\GlobalData.properties");
        prop.load(fis);
        String browserName=prop.getProperty("browser");
        if(browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver","C:\\Users\\P C\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
            driver=new ChromeDriver();
        }
        return driver;
    }

    public String getScreenshot(String testCaseName) throws IOException {
        TakesScreenshot ts=(TakesScreenshot)driver;
        File source=ts.getScreenshotAs(OutputType.FILE);
        String path=System.getProperty("user.dir")+"\\report\\"+testCaseName+".png";
        File file=new File(path);
        FileUtils.copyFile(source,file);
        return path;
    }
}

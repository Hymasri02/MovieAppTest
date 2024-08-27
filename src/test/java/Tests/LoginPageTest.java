package Tests;

import BaseData.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import pages.LoginPage;
import BaseData.Listener;

@Listeners(Listener.class)
public class LoginPageTest {
    public WebDriver driver;
    LoginPage loginPage;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() throws IOException {
        driver= BaseClass.initializeDriver();
        loginPage=new LoginPage(driver);
        driver.get("https://qamoviesapp.ccbp.tech");
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    @Test(priority=1)
    public void testWithLoginUI(){
        //Test whether the Website logo image is displayed
        Assert.assertTrue(loginPage.isLogoDisplayed().isDisplayed());

        //Test whether the Heading text is "Login"
        String expectedHeadingText="Login";
        String actualHeadingText=loginPage.testWithHeading();
        Assert.assertEquals(expectedHeadingText,actualHeadingText);

        //Test whether the Username label text is "USERNAME"
        String expectedLabelUsername="USERNAME";
        String currentLabelUsername=loginPage.testUsernameLabel();
        Assert.assertEquals(expectedLabelUsername,currentLabelUsername);

        //Test whether the Password label text is "PASSWORD"
        String passLabelExp="PASSWORD";
        String curPassLabel=loginPage.testPassLabel();
        Assert.assertEquals(passLabelExp,curPassLabel);


        //Test the "Login" button
        String loginTextExpected="Login";
        String loginCurrentText=loginPage.loginClick().getText();
        Assert.assertEquals(loginCurrentText,loginTextExpected);
    }

    //Test the login functionality with empty input fields
    @Test(priority=2)
    public void LoginEmptyInputs(){
        loginPage.login("","");
        loginPage.loginClick().submit();


        String expectedError="*Username or password is invalid";
        String currentError=loginPage.errorMsg();
        Assert.assertEquals(expectedError,currentError);
    }

    //Test the login functionality with empty USERNAME
    @Test(priority=3)
    public void loginWithEmptyUsername(){
        loginPage.enterUsername("");
        loginPage.loginClick().submit();
        String expectedError="*Username or password is invalid";
        String currentError=loginPage.errorMsg();
        Assert.assertEquals(expectedError,currentError);
    }

    //Test the login functionality with an empty PASSWORD
    @Test(priority=4)
    public void loginWithEmptyPassword(){
        loginPage.enterPassword("");
        loginPage.loginClick().submit();
        String expectedError="*Username or password is invalid";
        String currentError=loginPage.errorMsg();
        Assert.assertEquals(expectedError,currentError);
    }

    //Test the login functionality with Invalid Credentials (correct username and wrong password)
    @Test(priority=5)
    public void loginWithInvalidInputs(){
        loginPage.login("rahul","rahul@2020");
        loginPage.loginClick().submit();


        String expectedError="*username and password didn't match";
        String currentError=loginPage.errorMsg();
        Assert.assertEquals(expectedError,currentError);
    }

    //Test the login functionality with Valid Credentials
    @Test(priority = 6)
    public void loginWithValidInputs(){
        loginPage.login("rahul","rahul@2021");
        loginPage.loginClick().submit();

        String expectedUrl="https://qamoviesapp.ccbp.tech/";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String currentUrl=driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,currentUrl);
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}

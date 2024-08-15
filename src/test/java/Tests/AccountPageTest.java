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
import pages.AccountsPage;
import pages.HomePage;
import pages.LoginPage;
import BaseData.Listener;
import java.io.IOException;
import java.time.Duration;

@Listeners(Listener.class)
public class AccountPageTest {
    public WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    AccountsPage accountsPage;
    WebDriverWait wait;
    int c=0;
    @BeforeMethod
    public void setup() throws IOException {
        driver= BaseClass.initializeDriver();
        homePage=new HomePage(driver);
        loginPage=new LoginPage(driver);
        accountsPage=new AccountsPage(driver);
        driver.get("https://qamoviesapp.ccbp.tech");
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test(priority=1)
    public void testWithLogo(){
        loginPage.login("rahul","rahul@2021");
        loginPage.loginClick().submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("website-logo")));
        homePage.accountLink().click();
        String currentAccountHeading=accountsPage.accountHeading();
        String expectedAccountsHeading="Account";
        Assert.assertEquals(currentAccountHeading,expectedAccountsHeading);
        accountsPage.logout();
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}

package Tests;

import BaseData.BaseClass;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import org.openqa.selenium.By;

import java.io.IOException;
import java.time.Duration;
import BaseData.Listener;

import java.util.*;

@Listeners(Listener.class)
public class HeaderSectionTest {
    public WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    WebDriverWait wait;
    int c=0;
    @BeforeMethod
    public void setup() throws IOException {
        driver= BaseClass.initializeDriver();
        homePage=new HomePage(driver);
        loginPage=new LoginPage(driver);
        driver.get("https://qamoviesapp.ccbp.tech");
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test(priority=1)
    public void testWithLogo(){
        loginPage.login("rahul","rahul@2021");
        loginPage.loginClick().submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("website-logo")));
        Assert.assertTrue(homePage.logo().isDisplayed());
    }
    @Test(priority=2)
    public void testWithNavElements(){

        loginPage.login("rahul","rahul@2021");
        loginPage.loginClick().submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='list-item']")));
        List<WebElement> list=homePage.navList();
        String[] expectedList={"Home","Popular"};
        for(int i=0;i<expectedList.length;i++){
            String s1=list.get(i).getText();
            String s2=expectedList[i];
            if(s1.equalsIgnoreCase(s2)){
                c++;
            }
        }
        Assert.assertEquals(expectedList.length,c);
        System.out.println(list.size());
    }

    @Test(priority=3)
    public void navigateToHomeAndPopular(){
        loginPage.login("rahul","rahul@2021");
        loginPage.loginClick().submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("website-logo")));
        List<WebElement> list=homePage.navList();
        //click popular link
        list.get(1).click();
        String expectedPopularUrl="https://qamoviesapp.ccbp.tech/popular";
        wait.until(ExpectedConditions.urlToBe(expectedPopularUrl));
        String currentPopularUrl=driver.getCurrentUrl();
        Assert.assertEquals(expectedPopularUrl,currentPopularUrl);
        //click home link
        list.get(0).click();
        String expectedHomeUrl="https://qamoviesapp.ccbp.tech/";
        wait.until(ExpectedConditions.urlToBe(expectedHomeUrl));
        String currentHomeUrl=driver.getCurrentUrl();
        Assert.assertEquals(expectedHomeUrl,currentHomeUrl);


    }
    @Test(priority = 4)
    public void testAccountPage(){
        loginPage.login("rahul","rahul@2021");
        loginPage.loginClick().submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("website-logo")));
        //click account link
        homePage.accountLink().click();
        String expectedAccountUrl="https://qamoviesapp.ccbp.tech/account";
        wait.until(ExpectedConditions.urlToBe(expectedAccountUrl));
        String currentAccountUrl=driver.getCurrentUrl();
        Assert.assertEquals(expectedAccountUrl,currentAccountUrl);

        //back to Home page
        List<WebElement> list=homePage.navList();
        //click home link
        list.get(0).click();
        String expectedHomeUrl="https://qamoviesapp.ccbp.tech/";
        wait.until(ExpectedConditions.urlToBe(expectedHomeUrl));
        String currentHomeUrl=driver.getCurrentUrl();
        Assert.assertEquals(expectedHomeUrl,currentHomeUrl);
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }

}

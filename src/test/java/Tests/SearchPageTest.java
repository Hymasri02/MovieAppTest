package Tests;

import BaseData.BaseClass;
import BaseData.Listener;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchPage;

import java.util.*;

import java.io.IOException;
import java.time.Duration;

@Listeners(Listener.class)
public class SearchPageTest {
    public WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    SearchPage searchPage;
    WebDriverWait wait;
    int c=0;
    @BeforeMethod
    public void setup() throws IOException {
        driver= BaseClass.initializeDriver();
        homePage=new HomePage(driver);
        loginPage=new LoginPage(driver);
        searchPage=new SearchPage(driver);
        driver.get("https://qamoviesapp.ccbp.tech");
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority=1)
    public void testValidName(){
        loginPage.login("rahul","rahul@2021");
        loginPage.loginClick().submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("website-logo")));
        searchPage.clickSearchBtn();

        searchPage.enterInSearch("venom");
        searchPage.searchText();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("movie-icon-item")));
        List<WebElement> iconlist=searchPage.icons();
        for(int i=0;i<iconlist.size();i++){
            Assert.assertTrue(iconlist.get(i).isDisplayed());
            c++;
        }
        System.out.println(c);
    }

    @Test(priority=2)
    public void testWithInvalidMovieName(){
        loginPage.login("rahul","rahul@2021");
        loginPage.loginClick().submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("website-logo")));
        searchPage.clickSearchBtn();
        String name="venomy";
        searchPage.enterInSearch(name);
        searchPage.searchText();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("not-found-search-paragraph")));
        String expErrorMsg="Your search for "+name+" did not find any matches.";
        String curErrorMsg=searchPage.error();
        Assert.assertEquals(expErrorMsg,curErrorMsg);
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }


}

package Tests;

import BaseData.BaseClass;
import org.openqa.selenium.By;
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

import java.io.IOException;
import java.time.Duration;
import java.util.*;
import BaseData.Listener;

@Listeners(Listener.class)
public class HomePageTest {

    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() throws IOException {
        driver= BaseClass.initializeDriver();
        homePage=new HomePage(driver);
        loginPage=new LoginPage(driver);
        driver.get("https://qamoviesapp.ccbp.tech");
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority=1)
    public void testWithHeadings(){
        int c=0;
        loginPage.login("rahul","rahul@2021");
        loginPage.loginClick().submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("home-movie-heading")));

        List<WebElement> curheadingList=homePage.headingList();
        System.out.println(curheadingList.size());
        String[] expHeadingList={"King Richard","Trending Now","Originals"};
        for(int i=0;i<expHeadingList.length;i++){
            String s1=curheadingList.get(i).getText();
            String s2=expHeadingList[i];
            if(s1.equalsIgnoreCase(s2)){
                c++;
            }
        }
        Assert.assertEquals(expHeadingList.length,c);
    }

    @Test(priority=2)
    public void testWithPlayBtn(){
        loginPage.login("rahul","rahul@2021");
        loginPage.loginClick().submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("home-movie-heading")));
        Assert.assertTrue(homePage.playBtnF().isDisplayed());
    }

    @Test(priority = 3)
    public void testMoviesDisplay(){
        int c=0;
        loginPage.login("rahul","rahul@2021");
        loginPage.loginClick().submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("react-slick-item")));
        List<WebElement> movieList=homePage.moviesList();
        int ExpectedCount=20;
        Assert.assertEquals(movieList.size(),ExpectedCount);
    }

    @Test(priority=4)
    public void testWithContactUs(){
        loginPage.login("rahul","rahul@2021");
        loginPage.loginClick().submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("contact-us-paragraph")));
        String curText=homePage.contactUsF();
        String expectedText="Contact Us";
        Assert.assertEquals(curText,expectedText);
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}

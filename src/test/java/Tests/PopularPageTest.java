package Tests;

import BaseData.BaseClass;
import BaseData.Listener;
import io.cucumber.java.bs.A;
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
import pages.PopularPage;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Listeners(Listener.class)
public class PopularPageTest {
    public WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    PopularPage popularPage;
    WebDriverWait wait;
    @BeforeMethod
    public void setup() throws IOException {
        driver = BaseClass.initializeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        popularPage=new PopularPage(driver);
        driver.get("https://qamoviesapp.ccbp.tech");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test(priority=1)
    public void checkMovies(){
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("movie-icon-item")));
        List<WebElement> movieList=popularPage.movies();
        for(int i=0;i<movieList.size();i++){
            Assert.assertTrue(movieList.get(i).isDisplayed());
        }
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}

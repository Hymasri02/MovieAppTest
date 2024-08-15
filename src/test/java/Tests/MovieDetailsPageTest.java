package Tests;
import BaseData.BaseClass;
import BaseData.Listener;
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
import pages.MoviePage;
import pages.PopularPage;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Listeners(Listener.class)
public class MovieDetailsPageTest {
    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    HomePage homePage;
    MoviePage moviePage;
    PopularPage popularPage;
    @BeforeMethod
    public void setup() throws IOException {
        driver= BaseClass.initializeDriver();
        homePage=new HomePage(driver);
        loginPage=new LoginPage(driver);
        moviePage=new MoviePage(driver);
        popularPage=new PopularPage(driver);
        driver.get("https://qamoviesapp.ccbp.tech");
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
    @Test(priority = 1)
    public void homeMoviesTestUIElements(){
        loginPage.login("rahul","rahul@2021");
        loginPage.loginClick().submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-hidden='false']")));
        List<WebElement> falseMovielist=homePage.falseList();
        falseMovielist.get(1).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("image-style")));

        //In the Home Page click on any Movie and testing heading the UI elements present in it
        String curHeading= moviePage.Heading();
        String expectedHeading="Shang-Chi and the Legend of the Ten Rings";
        Assert.assertEquals(curHeading,expectedHeading);

        //In the Home Page click on any Movie and testing reviewList the UI elements present in it
        List<WebElement> reviewList= moviePage.getReviewList();
        int reviewCount=3;
        Assert.assertEquals(reviewList.size(),3);

        //In the Home Page click on any Movie and testing overview the UI element present in it
        String curOverview= moviePage.getOverview();
        String expectedOverview="Shang-Chi must confront the past he thought he left behind when he is drawn into the web of the mysterious Ten Rings";
        Assert.assertEquals(curOverview,expectedOverview);

        //In the Home Page click on any Movie and testing playBtn the UI elements present in it
        WebElement playBtn= moviePage.getPlayBtn();
        Assert.assertTrue(playBtn.isDisplayed());

        //In the Home Page click on any Movie and testing reviewList the UI elements present in it
        List<WebElement> imageList= moviePage.getImageList();
        int imageCount=33;
        Assert.assertEquals(imageList.size(),imageCount);
    }
    @Test(priority = 2)
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
        movieList.get(1).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("image-style")));

        //In the Home Page click on any Movie and testing heading the UI elements present in it
        String curHeading= moviePage.Heading();
        String expectedHeading="Snake Eyes: G.I. Joe Origins";
        Assert.assertEquals(curHeading,expectedHeading);

        //In the Home Page click on any Movie and testing reviewList the UI elements present in it
        List<WebElement> reviewList= moviePage.getReviewList();
        int reviewCount=3;
        Assert.assertEquals(reviewList.size(),3);

        //In the Home Page click on any Movie and testing reviewList the UI elements present in it
        List<WebElement> imageList= moviePage.getImageList();
        int imageCount=32;
        Assert.assertEquals(imageList.size(),imageCount);
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }

}

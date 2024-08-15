package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MoviePage {
    WebDriver driver;

    public MoviePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(className= "movie-title")
    WebElement movieTitle;

    @FindBy(xpath= "//div[@class='movie-review-container']/child::p")
    List<WebElement> reviewList;

    @FindBy(className= "movie-overview")
    WebElement overView;

    @FindBy(className= "play-button")
    WebElement playBtn;

    @FindBy(className = "image-style")
    List<WebElement> imagesList;

    public String Heading(){
        return movieTitle.getText();
    }
    public List<WebElement> getReviewList(){
        return reviewList;
    }
    public String getOverview(){
        return overView.getText();
    }
    public WebElement getPlayBtn(){
        return playBtn;
    }
    public List<WebElement> getImageList(){
        return imagesList;
    }
}

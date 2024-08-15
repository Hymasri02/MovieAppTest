package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PopularPage {
    WebDriver driver;

    public PopularPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(className= "movie-icon-item")
    List<WebElement> movieList;

    public List<WebElement> movies(){
        return movieList;
    }
}

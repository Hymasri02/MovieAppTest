package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath= "//h1")
    List<WebElement> h1List;

    @FindBy(className = "home-movie-play-button")
    WebElement playBtn;

    @FindBy(className = "react-slick-item")
    List<WebElement> itemList;

    @FindBy(className="contact-us-paragraph")
    WebElement contactUsEl;

    //Locators for HeaderSection
    @FindBy(className = "website-logo")
    WebElement logoEl;

    @FindBy(xpath="//li[@class='list-item']")
    List<WebElement> navItemsList;

    @FindBy(xpath="//div[@aria-hidden='true']")
    List<WebElement> hiddenTrueList;

    @FindBy(xpath="//div[@aria-hidden='false']")
    List<WebElement> hiddenFalseList;

    @FindBy(className="avatar-img")
    WebElement accountEl;


    public List<WebElement> headingList(){
        return h1List;
    }

    public WebElement playBtnF(){
        return playBtn;
    }

    public List<WebElement> moviesList(){
        return itemList;
    }

    public String contactUsF(){
        return contactUsEl.getText();
    }

    //Methods For HeaderSection
    public WebElement logo(){
        return logoEl;
    }
    public List<WebElement> navList(){
        return navItemsList;
    }

    public WebElement accountLink(){
        return accountEl;
    }

    //Methods for movieDetails Test
    public List<WebElement> trueList(){
        return hiddenTrueList;
    }

    public List<WebElement> falseList(){
        return hiddenFalseList;
    }
}

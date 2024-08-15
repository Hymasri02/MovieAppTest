package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.*;

public class SearchPage {
    WebDriver driver;
    public SearchPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(className="search-empty-button")
    WebElement searchBtn;

    @FindBy(id="search")
    WebElement searchInput;

    @FindBy(className="search-button")
    WebElement searchText;

    @FindBy(className="movie-icon-item")
    List<WebElement> iconsList;

    @FindBy(className = "not-found-search-paragraph")
    WebElement errorMsgEl;

    public void clickSearchBtn(){
        searchBtn.click();
    }
    public void enterInSearch(String name){
        searchInput.sendKeys(name);
    }

    public void searchText(){
        searchText.click();
    }

    public List<WebElement> icons(){
        return iconsList;
    }

    public String error(){
        return errorMsgEl.getText();
    }
}

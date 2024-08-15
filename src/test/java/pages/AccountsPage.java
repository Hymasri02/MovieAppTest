package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AccountsPage {
    WebDriver driver;

    public AccountsPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(className= "logout-button")
    WebElement btnEl;

    @FindBy(className = "account-heading")
    WebElement accountHeadingEl;

    public String accountHeading(){
        return accountHeadingEl.getText();
    }
    public void logout(){
        btnEl.click();
    }
}

package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;
    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(className = "login-website-logo")
    WebElement logoEl;

    @FindBy(className = "sign-in-heading")
    WebElement headingEl;

    @FindBy(xpath="//label[@class='input-label'][1]")
    WebElement usernameLabelEl;

    @FindBy(xpath="//label[@for='passwordInput']")
    WebElement passLabelEl;

    @FindBy(className="login-button")
    WebElement loginEl;

    @FindBy(id="usernameInput")
    WebElement username;

    @FindBy(id="passwordInput")
    WebElement password;

    @FindBy(className = "error-message")
    WebElement errorMsgEl;

    //These tests for login UI testing
    public WebElement isLogoDisplayed(){
        return loginEl;
    }
    public String  testWithHeading(){
        return headingEl.getText();
    }
    public String testUsernameLabel(){
        return usernameLabelEl.getText();
    }
    public String testPassLabel(){
        return passLabelEl.getText();
    }
    public WebElement loginClick(){
        return loginEl;
    }

    //these methods for login functionality Testing

    public void enterUsername(String name){
        username.sendKeys(name);
    }
    public void enterPassword(String pass){
        password.sendKeys(pass);
    }

    public void login(String name,String pass){
        enterUsername(name);
        enterPassword(pass);
    }

    public String errorMsg(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));
        return errorMsgEl.getText();
    }
}

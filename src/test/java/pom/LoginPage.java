package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
     PAGE OBJECT MODEL: LOGIN PAGE
        it contains all elements of page and action on that elements.
 */

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;


    /*
        This part is xpath of element of page
     */
    private By usernameField = By.xpath("//input[@id='AppUserUsername']");
    private By passwordField = By.xpath("//input[@id='AppUserPassword']");
    private By loginButton = By.xpath("//button[@id='btnSignIn']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /*
        Define action for element
     */
    public void enterUsername(String username) {
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginBtn.click();
    }

    //Login function
    public void loginWithUsernameAndPassword(String username, String password){
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}

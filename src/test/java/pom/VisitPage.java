package pom;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ExtentReportListener;

import java.time.Duration;

public class VisitPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(VisitPage.class);

    // Page elements
    private By enterWaitingRoomButton = By.xpath("//input[@value='Enter Waiting Room']");
    private By firstNameInput = By.xpath("//input[@name='first_name']");
    private By reasonForVisitTextarea = By.xpath("//textarea[@name='reason_for_visit']");
    private By consentCheckbox = By.xpath("//input[@id='jsonform-1-elt-consent']");
    private By reminderModalButton = By.xpath("//div[@id='ReminderModal']/div/div/div[3]/button[@data-action='reminder-btn-clicked']");
    private By forProviderLink = By.xpath("//a[text()='For Providers']");
    private By enableButton = By.xpath("//div[text()='Text me when the provider is ready']");
    private By toolBoxMenu = By.xpath("//div[@id=\"new-toolbox\"]");
    private By joinNowButton = By.xpath("//div[@data-testid='prejoin.screen']/div/div");
    private By messageBubble = By.xpath("//div[@class=\"webchat-message-bubble\"]");

    // Constructor
    public VisitPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    // Actions
    public void clickEnterWaitingRoom() {
        logger.info("Waiting for 'Enter Waiting Room' button to be visible");
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(enterWaitingRoomButton));
        logger.info("Clicking on 'Enter Waiting Room' button");
        button.click();
    }

    public void enterFirstName(String firstName) {
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        logger.info("Entering first name: {}", firstName);
        firstNameField.sendKeys(firstName);
    }

    public void enterReasonForVisit(String reason) {
        logger.info("Waiting for reason for visit textarea to be visible");
        WebElement reasonTextarea = wait.until(ExpectedConditions.visibilityOfElementLocated(reasonForVisitTextarea));
        logger.info("Entering reason for visit: {}", reason);
        reasonTextarea.sendKeys(reason);
    }

    public void checkConsentCheckbox() {
        logger.info("Waiting for consent checkbox to be visible");
        WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(consentCheckbox));
        if (!checkbox.isSelected()) {
            logger.info("Checking consent checkbox");
            checkbox.click();
        } else {
            logger.info("Consent checkbox is already checked");
        }
    }

    public void clickForProviders() {
        logger.info("Waiting for 'For Providers' link to be visible");
        WebElement forProvidersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(forProviderLink));
        logger.info("Clicking on 'For Providers' link");
        forProvidersLink.click();
    }

    public void waitEnableButtonToBeVisible() {
        logger.info("Waiting for 'Text me when the provider is ready' button to be visible");
        wait.until(ExpectedConditions.visibilityOfElementLocated(enableButton));
    }

    public void waitToolBoxMenuToBeVisible() {
        logger.info("Waiting for tool box menu to be visible");
        wait.until(ExpectedConditions.visibilityOfElementLocated(toolBoxMenu));
    }

    public void validateChatMessage() {
        logger.info("Waiting for chat message bubble to be visible");
        WebElement chatMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(messageBubble));
        String messageText = chatMessage.getText();
        logger.info("Validating chat message content: {}", messageText);
        Assert.assertEquals(messageText, "hello world", "Chat message content does not match");
    }

    //User A input username, description, consent checkbox and click Join button
    public void joinWaitingRoom(String firstName, String reason){
        enterFirstName(firstName);
        enterReasonForVisit(reason);
        checkConsentCheckbox();
        clickEnterWaitingRoom();
    }
}

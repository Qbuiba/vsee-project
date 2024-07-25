package pom;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ProviderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private static final Logger logger = LoggerFactory.getLogger(ProviderPage.class);

    // Page elements
    private By readdyForVisitsHeading = By.xpath("//h4[contains(text(), 'Ready for Visits')]");
    private By callButton = By.xpath("//a[@title='Call']");
    private By chatButton = By.xpath("//div[@id='new-toolbox']/div/div/div/div[4]/div");
    private By chatBoxInput = By.xpath("//div[contains(@id, 'visit_chat-conference-talk-vsee-com')]/div[2]/div[2]/form/input[@placeholder=\"Type your message here\"]");
    private By iframeLocator = By.xpath("//iframe[@id='jitsiConferenceFrame0']"); // Change to the correct iframe locator

    // Constructor
    public ProviderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void switchToIframe() {
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(iframeLocator));
        logger.info("Switching to iframe");
        driver.switchTo().frame(iframe);
    }

    // Switch back to default content
    public void switchToDefaultContent() {
        logger.info("Switching back to default content");
        driver.switchTo().defaultContent();
    }

    public void waitReadyForVisitsToBeVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(readdyForVisitsHeading));
    }

    public void clickCallButton() {
        WebElement callBtn = wait.until(ExpectedConditions.elementToBeClickable(callButton));
        logger.info("Clicking on 'Call' button");
        callBtn.click();
    }

    public void sendChatMessage(String message) {
        WebElement chatInput = wait.until(ExpectedConditions.visibilityOfElementLocated(chatBoxInput));
        logger.info("Sending message to chatbox: {}", message);
        chatInput.sendKeys(message);
        logger.info("press Enter key");
        chatInput.sendKeys(Keys.ENTER);
    }

    public void clickChatButton() {
        WebElement chatBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(chatButton));
        logger.info("Clicking on chat button");
        chatBtn.click();
    }


}

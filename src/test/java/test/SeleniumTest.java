package test;

import config.TestConfig;
import pom.ProviderPage;
import pom.LoginPage;
import pom.VisitPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.PageFactory;
import utils.WebDriverSetup;


public class SeleniumTest {
    private WebDriver chromeDriver;
    private WebDriver edgeDriver;
    private VisitPage userAPage;
    private VisitPage userBPage;
    private LoginPage LoginPage;
    private ProviderPage providerPage;


    @BeforeMethod
    public void setUp() {
        chromeDriver = WebDriverSetup.getChromeDriver();
    }

    @Test
    public void testChatInteraction() {
        /*
            Visitor actions in Chrome browser:
            1-Go to robin4 url
            2-Input username, description, consent checkbox
            3-Click enter waiting room
         */
        chromeDriver.get(TestConfig.ROOM_URL);
        assert chromeDriver.getTitle().equals(TestConfig.EXPECTED_TITLE);
        userAPage = PageFactory.getVisitPage(chromeDriver);
        userAPage.joinWaitingRoom("User A", "dental appointment");
        userAPage.waitEnableButtonToBeVisible();
        /*
            Provider actions in Edge browser:
            1-Go to robin4 url
            2-Click "For Provider" link
            3-Login with username and password
         */
        edgeDriver = WebDriverSetup.getEdgeDriver();
        edgeDriver.get(TestConfig.ROOM_URL);
        assert edgeDriver.getTitle().equals(TestConfig.EXPECTED_TITLE);
        userBPage = PageFactory.getVisitPage(edgeDriver);
        userBPage.clickForProviders();
        LoginPage = PageFactory.getLoginPage(edgeDriver);
        LoginPage.loginWithUsernameAndPassword(TestConfig.USERNAME, TestConfig.PASSWORD);
        /*
            After login, provider actions:
            1-Access to Meeting by clicking Call button
            2-Toggle the chat box
            3-Send message to User A
         */
        providerPage = PageFactory.getProviderPage(edgeDriver);
        providerPage.waitReadyForVisitsToBeVisible();
        providerPage.clickCallButton();
        providerPage.switchToIframe();
        providerPage.clickChatButton();
        providerPage.switchToDefaultContent();
        providerPage.sendChatMessage("hello world");
        /*
            Validate that User A get correct message
         */
        userAPage.validateChatMessage();
    }

    @AfterMethod
    public void tearDown() {
        WebDriverSetup.tearDown(chromeDriver, edgeDriver);
    }
}

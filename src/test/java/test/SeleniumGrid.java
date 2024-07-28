package test;

import config.TestConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pom.LoginPage;
import pom.ProviderPage;
import pom.VisitPage;
import utils.PageFactory;
import utils.WebDriverSetup;


public class SeleniumGrid {
    private WebDriver chromeDriver1;
    private WebDriver chromeDriver2;
    private VisitPage userAPage;
    private VisitPage userBPage;
    private LoginPage LoginPage;
    private ProviderPage providerPage;


    @BeforeMethod
    public void setUp() {
        chromeDriver1 = WebDriverSetup.getChromeDriverGrid();
        chromeDriver2 = WebDriverSetup.getChromeDriverGrid();
    }

    @Test
    public void testChatInteraction() {
        /*
            Visitor actions in Chrome browser:
            1-Go to robin4 url
            2-Input username, description, consent checkbox
            3-Click enter waiting room
         */
        chromeDriver1.get(TestConfig.ROOM_URL);
        assert chromeDriver1.getTitle().equals(TestConfig.EXPECTED_TITLE);
        userAPage = PageFactory.getVisitPage(chromeDriver1);
        userAPage.joinWaitingRoom("User A", "dental appointment");
        userAPage.waitEnableButtonToBeVisible();
        /*
            Provider actions in Chrome browser:
            1-Go to robin4 url
            2-Click "For Provider" link
            3-Login with username and password
         */

        chromeDriver2.get(TestConfig.ROOM_URL);
        assert chromeDriver2.getTitle().equals(TestConfig.EXPECTED_TITLE);
        userBPage = PageFactory.getVisitPage(chromeDriver2);
        userBPage.clickForProviders();
        LoginPage = PageFactory.getLoginPage(chromeDriver2);
        LoginPage.loginWithUsernameAndPassword(TestConfig.USERNAME, TestConfig.PASSWORD);
        /*
            After login, provider actions:
            1-Access to Meeting by clicking Call button
            2-Toggle the chat box
            3-Send message to User A
         */
        providerPage = PageFactory.getProviderPage(chromeDriver2);
        providerPage.checkAndClickSyncTimezoneButton();
        providerPage.waitReadyForVisitsToBeVisible();
        providerPage.clickCallButton();
        providerPage.switchToIframe();
        providerPage.clickChatButton();
        providerPage.switchToDefaultContent();
        providerPage.sendChatMessage("hello world");
        /*
            Validate that User get correct message
         */
        userAPage.validateChatMessage();
    }

    @AfterMethod
    public void tearDown() {
        WebDriverSetup.tearDown(chromeDriver1, chromeDriver2);
    }
}

package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;


/*
    THIS CLASS IS USED TO CONFIG AND MANAGEMENT ALL WEB DRIVER
 */
public class WebDriverSetup {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverSetup.class);

    /*
        Set up Chrome browser in local machine
     */
    public static WebDriver getChromeDriver() {
        logger.info("Setting up ChromeDriver");
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--use-fake-ui-for-media-stream");
        chromeOptions.setExperimentalOption("prefs", new HashMap<String, Object>() {{
            put("profile.default_content_setting_values.media_stream_camera", 1);
            put("profile.default_content_setting_values.media_stream_mic", 1);
            put("profile.default_content_setting_values.geolocation", 1);
            put("profile.default_content_setting_values.notifications", 1);
        }});
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        logger.info("ChromeDriver setup complete and maximized");
        return driver;
    }
    /*
        Set up Edge browser in local machine
     */
    public static WebDriver getEdgeDriver() {
        logger.info("Setting up EdgeDriver");
        WebDriverManager.edgedriver().setup();
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--use-fake-ui-for-media-stream");
        WebDriver driver = new EdgeDriver(edgeOptions);
        driver.manage().window().maximize();
        logger.info("EdgeDriver setup complete and maximized");
        return driver;
    }

    /*
        SELENIUM GRID: HUB AND NODE MODE
     */

    public static WebDriver getChromeDriverGrid() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--use-fake-ui-for-media-stream");
        chromeOptions.setExperimentalOption("prefs", new HashMap<String, Object>() {{
            put("profile.default_content_setting_values.media_stream_camera", 1);
            put("profile.default_content_setting_values.media_stream_mic", 1);
            put("profile.default_content_setting_values.geolocation", 1);
            put("profile.default_content_setting_values.notifications", 1);
        }});

        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
        } catch (MalformedURLException e) {
            logger.error("MalformedURLException: ", e);
        }
        return driver;
    }

    public static void tearDown(WebDriver chromeDriver, WebDriver edgeDriver) {
        if (chromeDriver != null) {
            chromeDriver.quit();
            logger.info("Chrome Browser closed");
        }
        if (edgeDriver != null) {
            edgeDriver.quit();
            logger.info("Edge Browser closed");
        }
    }
}

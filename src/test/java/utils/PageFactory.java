package utils;

import org.openqa.selenium.WebDriver;
import pom.VisitPage;
import pom.LoginPage;
import pom.ProviderPage;

public class PageFactory {
    public static VisitPage getVisitPage(WebDriver driver) {
        return new VisitPage(driver);
    }

    public static LoginPage getLoginPage(WebDriver driver) {
        return new LoginPage(driver);
    }

    public static ProviderPage getProviderPage(WebDriver driver) {
        return new ProviderPage(driver);
    }
}

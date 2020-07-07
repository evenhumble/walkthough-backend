package io.qmeta.saucelab.domain.Pages.DesktopWeb;

import com.yourcompany.Pages.BasePage;
import com.yourcompany.Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class DesktopHomePage extends BasePage implements HomePage {

    public DesktopHomePage(WebDriver driver) {
        super(driver);
    }
    private By signInButton = By.cssSelector("header a[href='/beta/login']");


    public void visitPage(HashMap<String, String> parameterMap) {
        this.driver.get(parameterMap.get("DESKTOP_HOMEPAGE_URL"));
    }

    public void goToLogin() throws Exception { click(signInButton); }
}

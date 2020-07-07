package io.qmeta.saucelab.domain.Pages.MobileWeb;

import com.yourcompany.Pages.BasePage;
import com.yourcompany.Pages.HomePage;
import com.yourcompany.Utils.JsonMapperUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

/**
 * Created by neil on 5/27/17.
 * Modified by Marina on 7/5/17.
 */
public class MobileWebHomePage extends BasePage implements HomePage {

    public MobileWebHomePage(WebDriver driver) {
        super(driver);
    }
    private By menuBtn = By.cssSelector(".N54G");
    private By signInButton = By.cssSelector("#site-header a[data-reactid='69']");


    public void visitPage(HashMap<String, String> parameterMap) {
        this.driver.get(parameterMap.get("MOBILE_WEB_HOMEPAGE_URL"));
    }

    public void goToLogin() throws Exception {
        click(menuBtn);
        click(signInButton);
    }
}

package io.qmeta.saucelab.domain.Tests;

import com.tngtech.java.junit.dataprovider.UseDataProvider;
import com.yourcompany.Pages.HomePage;
import com.yourcompany.Pages.LoginPage;
import com.yourcompany.Utils.JsonMapperUtil;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Method;
import java.util.HashMap;


/**
 * Created by neil
 */

public class LoginTest extends TestBase {
    private static HashMap<String,String> parameterMap = JsonMapperUtil.getParameterHashMapFromJsonFile("src/test/resources/paramCfg.json");

    /**
     * Runs a simple test verifying if the comment input is functional.
     * @throws InvalidElementStateException
     */
    @Test
    @UseDataProvider("sauceBrowsersDataProvider")
    public void invalidLoginTest(String browser, String version, String os, String pageobject)
            throws Exception {
        this.createDriver(browser, version, os, pageobject,"LoginTest");
        WebDriver driver = getWebDriver();

        HomePage homePage = pageFactory.createSauceHomePage(driver);
        homePage.visitPage(parameterMap);
        homePage.goToLogin();

        LoginPage loginPage = pageFactory.createSauceLoginPage(driver);
        loginPage.invalidLogin();

        Assert.assertTrue("Invalid Login Message not present",
                loginPage.isInvalidMessage());
    }

}

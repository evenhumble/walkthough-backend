package io.qmeta.saucelab.domain.Tests;

import com.tngtech.java.junit.dataprovider.UseDataProvider;
import com.yourcompany.Pages.GuineaPigPage;
import junit.framework.Assert;
import org.junit.Test;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import java.util.UUID;


/**
 * Created by neil
 */

public class MobileNativeTest extends TestBase {

    /**
     * Runs a simple test verifying if the comment input is functional.
     * @throws InvalidElementStateException
     */
    @Test
    @UseDataProvider("sauceDevicesDataProvider")
    public void mobileNativeTestTest(String browser, String version, String os, String pageobject)
            throws Exception {
        this.createDriver(browser, version, os, pageobject, "MobileNativeTest");
        WebDriver driver = getWebDriver();

        String commentInputText = UUID.randomUUID().toString();

        GuineaPigPage page = mobileNativePageFactory.createGuineaPigPage(driver);

        page.submitComment(commentInputText);

        Assert.assertTrue(page.getSubmittedCommentText().contains(commentInputText));
    }

}

package io.qmeta.saucelab.advanced;

import com.saucelabs.salsaverde.Browser;
import com.saucelabs.salsaverde.junit.SauceTestWatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class TestWatcherTest {
    public Browser browser;

    @Rule
    public SauceTestWatcher testWatcher = new SauceTestWatcher();

    @Before
    public void setup() {
        browser = new Browser(testWatcher.getDriver());
    }

    @Test
    public void testFail() {
        throw new RuntimeException();
    }

    @Test
    public void testPass() {
        System.out.println("This should pass");
    }
}

package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utility.Utility;

public class TopMenuTest extends Utility {
    @Before
    public void setUp() {

        openBrowser();

    }

    @Test
    public void verifyPageNavigation() {
        // Select any tab from menu
        selectMenu("Digital downloads");
    }

    @After
    public void tearBrowser() {
        closeBrowser();
    }
}
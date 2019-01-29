import environment.EnvironmentManager;
import environment.RunEnvironment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import testScripts.GoogleSearch;

public class JunitExec {
    @BeforeEach
    public void startBrowser() {
        EnvironmentManager.initWebDriver();
        WebDriver driver = RunEnvironment.getWebDriver();
        driver.get("http://www.google.com");
    }

    @Test
    public void test() {
        GoogleSearch.searchAndTakeScreenshot("Adaptavist");
        GoogleSearch.searchAndCheckTitleDoesNotContains("Atlassian", "Adaptavist");
    }

    @AfterEach
    public void tearDown() {
        EnvironmentManager.shutDownDriver();
    }
}

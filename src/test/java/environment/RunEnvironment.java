package environment;

import org.openqa.selenium.WebDriver;

public class RunEnvironment {
    public static WebDriver driver;

    public static WebDriver getWebDriver() {
        return driver;
    }

    public static void setWebDriver(WebDriver driver) {
        RunEnvironment.driver = driver;
    }
}

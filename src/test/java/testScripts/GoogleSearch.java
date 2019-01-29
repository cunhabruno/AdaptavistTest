package testScripts;

import com.google.common.io.Files;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import environment.RunEnvironment;
import locators.GoogleLocators;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GoogleSearch {

    public static void googleSearch(String searchString) {
        WebDriver driver = RunEnvironment.driver;

        driver.findElement(GoogleLocators.searchInput).clear();
        driver.findElement(GoogleLocators.searchInput).sendKeys(searchString);
        driver.findElement(GoogleLocators.searchInput).sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(GoogleLocators.searchResultTitle));
    }

    public static void searchAndTakeScreenshot(String searchString){
        WebDriver driver = RunEnvironment.driver;

        GoogleSearch.googleSearch(searchString);

        WebDriverWait wait = new WebDriverWait(driver, 50);

        List<WebElement> elms = driver.findElements(GoogleLocators.searchResultTitle);
        for(int i = 0; i < elms.size(); i++) {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(GoogleLocators.searchResultTitle));
            WebElement link = driver.findElements(GoogleLocators.searchResultTitle).get(i);

            if(link.getText().contains(searchString)) {
                link.click();
                File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                try {
                    Files.copy(srcFile, new File(srcFile.getName()));
                } catch (IOException e) {
                  System.out.print(e);
                }
                driver.navigate().back();
            }
        }
    }

    public static void searchAndCheckTitleDoesNotContains(String searchKey, String notFoundKey) {
        WebDriver driver = RunEnvironment.driver;

        GoogleSearch.googleSearch(searchKey);

        List<WebElement> elms2 = driver.findElements(GoogleLocators.searchResultTitle);

        for (WebElement el: elms2) {
            String textFound = el.getText();
            Assertions.assertFalse(
                    textFound.contains(notFoundKey),
                    "Title should not contains the string " + notFoundKey +
                            " Text found: " + textFound
            );
        }
    }
}

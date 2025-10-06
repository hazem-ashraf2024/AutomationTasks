package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task4 {
    WebDriver driver;
    /**
     * #4
     * ________________ Moderate ________________
     * Open Mozilla Firefox
     * Navigate to [<a href="https://duckduckgo.com/">...</a>]
     * Search for [TestNG]
     * Assert that the text of the fourth result is [TestNG Tutorial]
     * Close Mozilla Firefox
     */
    @Test
    public void task4(){
        driver.manage().window().maximize();
        driver.get("https://duckduckgo.com/");
        By searchBoxLocator=By.xpath("//input[@aria-label='Search with DuckDuckGo']");
        driver.findElement(searchBoxLocator).sendKeys("TestNG"+ Keys.ENTER);
        By fourthResultTextLocator=By.xpath("//span[text()='TestNG Tutorial | Selenium with TestNG | TestNg for Beginners - Tools QA']");
        String actualText=driver.findElement(fourthResultTextLocator).getText();
        String expectedText="TestNG Tutorial";
        Assert.assertEquals(actualText,expectedText,"Fourth result text is not matching");

    }
    @BeforeMethod
    public void setUp(){
        driver=new FirefoxDriver();
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class TestClass {
    WebDriver driver;


    /**
     * #1
     * ________________ Basic ________________
     * Open Google Chrome
     * Navigate to [<a href="https://duckduckgo.com/">...</a>]
     * Assert that the page title is [Google]
     * Close Google Chrome
     */

    @Test
    public void task1(){

        driver.manage().window().maximize();
        driver.get("https://duckduckgo.com/");
       String actualPageTitle= driver.getTitle();
       String expectedPageTitle="Google";
        Assert.assertEquals(actualPageTitle,expectedPageTitle,"Page title is not matching");

    }

    /**
     * #2
     * ________________ Basic ________________
     * Open Google Chrome
     * Navigate to [<a href="https://duckduckgo.com/">...</a>]
     * Assert that the DuckDuckGo logo is displayed
     * Close Google Chrome
     */
    @Test
    public void task2(){
        driver.manage().window().maximize();
        driver.get("https://duckduckgo.com/");

        // option #1 store by object and then use it , finding the element when we take action
        By logoLocator=By.xpath("//div[contains(@class,'Desktop')]//img");
        boolean isDisplayed=driver.findElement(logoLocator).isDisplayed();
//        // or option #2 find the element directly and use it without storing and take action in the same line
//        boolean isDisplayed2=driver.findElement(By.xpath("//div[contains(@class,'Desktop')]//img")).isDisplayed();
//        // or option #3 find the element directly and store it in a webelemet variable and then use it
//        WebElement logo2=driver.findElement(By.xpath("//div[contains(@class,'Desktop')]//img"));
//        boolean isDisplayed3=logo2.isDisplayed();
//        // stale element reference exception may occur in  option #3 if the page is refreshed or changed between line 54 and 55 execution time
//        // @FindBy annotation is used in Page Factory Model to locate elements it takes By object as parameter and stores the element in a WebElement variable
//        // but not recommended to use in test class by titus fortune (head of tech committee  and simon snick the founder of selenium in the video

        Assert.assertTrue(isDisplayed,"DuckDuckGo logo is not displayed");

    }
    @BeforeMethod
    public void setUp(){
        driver=new ChromeDriver();
    }
    @AfterMethod
    public void tearDown(){
            driver.quit();
    }


    }


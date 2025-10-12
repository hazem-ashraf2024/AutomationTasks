package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

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

    @Test()
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
    @Test (dependsOnMethods = {"task1"})
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

        assertTrue(isDisplayed,"DuckDuckGo logo is not displayed");

        // we go with option #1 based on selenium docs regarding troubleshooting: always relocate the element every time  when you take action

    }

    /**
     * #3
     * ________________ Basic ________________
     * Open Google Chrome
     * Navigate to [<a href="https://duckduckgo.com/">...</a>]
     * Search for [Selenium WebDriver]
     * Assert that the link of the first result is [<a href="https://www.selenium.dev/documentation/webdriver/">...</a>]
     * Close Google Chrome
     */
    @Test (dependsOnMethods = {"task1","task2"})
    public void task3(){
        driver.manage().window().maximize();
        driver.get("https://duckduckgo.com/");
        By searchBoxLocator=By.id("searchbox_input");
        driver.findElement(searchBoxLocator).sendKeys("Selenium WebDriver"+Keys.ENTER);
//        or
//        driver.findElement(searchBoxLocator).sendKeys(Keys.ENTER);
       // By searchButtonLocator=By.xpath("search_button_homepage");
      //  driver.findElement(searchButtonLocator).click();
        By firstResultLinkLocator=By.xpath("(//li[@data-layout='organic'])[1]//a[@data-testid='result-title-a']");
        String actualLink = driver.findElement(firstResultLinkLocator).getDomAttribute("href");
        String expectedLink="https://www.selenium.dev/documentation/webdriver/";
        Assert.assertEquals(actualLink,expectedLink,"First result link is not matching");
    }

    /**
     * #5
     * ________________ Moderate ________________
     * Open Google Chrome
     * Navigate to [<a href="https://duckduckgo.com/">...</a>]
     * Search for [Cucumber IO]
     * Assert that the link of the second result contains [<a href="https://www.linkedin.com">...</a>]
     * Close Google Chrome
     */
    @Test (dependsOnMethods = {"task1","task2","task3"})
    public void task5(){
        driver.manage().window().maximize();
        driver.get("https://duckduckgo.com/");
        By searchBoxLocator=By.xpath("//input[@aria-label='Search with DuckDuckGo']");
        driver.findElement(searchBoxLocator).sendKeys("Cucumber IO"+ Keys.ENTER);
        By secondResultLinkLocator=By.xpath("(//li[@data-layout='organic'])[2]//a[@data-testid='result-title-a']");
        String actualLink=driver.findElement(secondResultLinkLocator).getDomAttribute("href");
        String expectedLink="https://www.linkedin.com";
        Assert.assertTrue(actualLink.contains(expectedLink),"Actual link "+actualLink+" does not contain "+expectedLink);
    }

    /**
     * #6
     * ________________ Basic ________________
     * Open Google Chrome
     * Navigate to [<a href="http://the-internet.herokuapp.com/checkboxes">...</a>]
     * Check Checkbox 1
     * Assert that both Checkboxes are checked
     * Close Google Chrome
     */
    @Test //(dependsOnMethods = {"task1","task2","task3","task5"})
    public void task6() {
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        By checkbox1Locator = By.xpath("(//input[@type='checkbox'])[1]");
        driver.findElement(checkbox1Locator).click();
        Assert.assertTrue(driver.findElement(checkbox1Locator).isSelected(), "Checkbox 1 is not selected");
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


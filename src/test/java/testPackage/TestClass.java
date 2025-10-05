package testPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class TestClass {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver=new ChromeDriver();
    }
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
    @AfterMethod
    public void tearDown(){
            driver.quit();
    }


    }


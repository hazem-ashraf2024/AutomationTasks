package testPackage;

import duckDuckGoSearch.Home;
import duckDuckGoSearch.Results;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FirstModularTest {
    WebDriver driver;
    ChromeOptions options;
    Home home;
    Results results;
    @Test()
    public void task3(){
        driver.manage().window().maximize();
        home.navigateToHomePage();
        var keysToSend = "Selenium WebDriver" + Keys.ENTER;
        home.search(keysToSend);
        String actuaFirstlLink = results.getActualFirstResultLink();
        String expectedFirstLink = "https://www.selenium.dev/documentation/webdriver/";
        Assert.assertEquals(actuaFirstlLink, expectedFirstLink, "First result link is not matching");
    }
    @BeforeMethod
    public void setup(){
        options = new ChromeOptions();
        options.setImplicitWaitTimeout(Duration.ofSeconds(10));
        driver = new ChromeDriver(options);
        home = new Home(driver);
        results = new Results(driver);

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

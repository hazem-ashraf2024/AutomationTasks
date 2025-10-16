package testPackage;

import duckDuckGoSearch.Home;
import duckDuckGoSearch.Results;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FirstModularTestThemesApproach {
    WebDriver driver;
    ChromeOptions options;
    Results results;



    @Test()
    public void task3(){
        results.navigateToResultsPage();
        Assert.assertEquals(results.getActualFirstResultLink(), "https://www.selenium.dev/documentation/webdriver/", "First result link is not matching");
    }



    @BeforeMethod
    public void setup(){
        options = new ChromeOptions();
        options.setImplicitWaitTimeout(Duration.ofSeconds(10));
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        results = new Results(driver);

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

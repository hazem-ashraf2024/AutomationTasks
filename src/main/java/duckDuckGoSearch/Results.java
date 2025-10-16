package duckDuckGoSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Results {
  private final  WebDriver driver;
  private final String searchForSeleniumWebDriverURL="https://duckduckgo.com/?origin=funnel_home_website&t=h_&q=Selenium+WebDriver&ia=web";
  private final By firstResultLinkLocator=By.xpath("(//li[@data-layout='organic'])[1]//a[@data-testid='result-title-a']");

    public Results(WebDriver driver) {
        this.driver = driver;
    }
    public void navigateToResultsPage(){
        driver.navigate().to(searchForSeleniumWebDriverURL);
    }
    public String getActualFirstResultLink(){
        return driver.findElement(firstResultLinkLocator).getDomAttribute("href");
    }
}

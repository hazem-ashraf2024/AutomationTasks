package duckDuckGoSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Home {
    private final WebDriver driver;
private final String url = "https://duckduckgo.com/";
private final By searchBoxLocator=By.id("searchbox_input");
    public Home(WebDriver driver) {
        this.driver = driver;
    }
    public void navigateToHomePage() {
        driver.navigate().to(url);
    }
    public void search(CharSequence keysToSend){
        driver.findElement(searchBoxLocator).sendKeys(keysToSend);
    }
}

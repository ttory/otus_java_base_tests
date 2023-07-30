package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class DuckDuckGo {
    private static final By INPUT_FIELD = By.xpath("//div/input[@id='searchbox_input']");
    private final WebDriver driver;

    public DuckDuckGo(WebDriver driver) {
        this.driver = driver;
    }

    public void searchIn(String searchKey) {
        driver.findElement(INPUT_FIELD).sendKeys(searchKey);
        driver.findElement(INPUT_FIELD).sendKeys(Keys.RETURN);
    }
}

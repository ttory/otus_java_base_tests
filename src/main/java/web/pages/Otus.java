package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Otus {

    private static final By LOGIN = By.xpath("//div/input[@name='email' and @type='text']");
    private static final By PASSWORD = By.xpath("//div/input[@type='password']");
    private static final By LOGIN_BUTTON_MAIN_PAGE = By.xpath("//div/button[text()='Войти']");
    private static final By LOGIN_BUTTON = By.xpath("//button/div[text()='Войти']");

    private final WebDriver driver;

    public Otus(WebDriver driver) {
        this.driver = driver;
        driver.manage().window().maximize();
    }

    public void loginIn(String login, String password) {
        driver.findElement(LOGIN_BUTTON_MAIN_PAGE).click();
        driver.findElement(LOGIN).sendKeys(login);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

}

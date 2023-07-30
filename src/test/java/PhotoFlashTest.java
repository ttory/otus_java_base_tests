
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.utils.Constants;

import java.time.Duration;
import java.util.List;

public class PhotoFlashTest {
    private static final By PICTURES_LIST = By.xpath("//li[contains(@class, 'portfolio-item2 content')]");
    private static final By MODAL_PICTURE = By.xpath("//div[contains(@class, 'pp_pic_holder')]");
    private WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--kiosk");
        driver = new ChromeDriver(options);
        driver.get(Constants.PHOTO_FLASH_URL);
    }

    @Test
    public void openPicture() {

        List<WebElement> pictureList = driver.findElements(PICTURES_LIST);
        int size = pictureList.size();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
                .elementToBeClickable(pictureList.get((int) Math.round(Math.random() * (size - 1)))));
        driver.findElements(MODAL_PICTURE);
    }

    @AfterEach
    public void after() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}

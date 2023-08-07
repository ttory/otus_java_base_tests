import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.utils.Constants;
import web.pages.Otus;

import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

public class OtusTest {
    private WebDriver driver;
    Logger logger = LogManager.getLogger(OtusTest.class);
    private static final By USER_INFO_NAME = By.xpath("//span[contains(@class,'sc-199a3eq')]");

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(Constants.OTUS_URL);
    }


    @Test
    public void LoginTest() {
        try {
            new Otus(driver).loginIn(Constants.OTUS_LOGIN, Constants.OTUS_PASS);
            Set<Cookie> cookies = driver.manage().getCookies();
            for (Cookie each : cookies) {
                logger.info(each);
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
        WebElement userName = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(USER_INFO_NAME));

        String userNameText = userName.getText();
        Assertions.assertEquals(userNameText,Constants.OTUS_USER_NAME );
    }

    @AfterEach
    public void after() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}

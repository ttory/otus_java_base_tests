import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import web.utils.Constants;
import web.pages.Otus;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

public class OtusTest {
    private WebDriver driver;
    Logger logger = LogManager.getLogger(OtusTest.class);

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
    public void testLogin() {
        try {
            new Otus(driver).loginIn(Constants.OTUS_LOGIN, Constants.OTUS_PASS);
            Set<Cookie> cookies = driver.manage().getCookies();
            for (Cookie each : cookies) {
                logger.info(each);
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @AfterEach
    public void after() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}

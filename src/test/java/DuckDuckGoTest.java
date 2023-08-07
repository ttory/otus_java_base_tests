import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import web.pages.DuckDuckGo;
import web.utils.Constants;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class DuckDuckGoTest {

    private static final By FIRST_RESULT = By.xpath("//div//li[1]//article[@id='r1-0']" +
            "/div[2]//span");
    private WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get(Constants.DUCK_DUCK_GO_URL);
    }

    @Test
    public void searchTest() {
         String text = "Онлайн‑курсы для профессионалов, дистанционное обучение";
        try {
            new DuckDuckGo(driver).searchIn("ОТУС");
        } catch (Exception e) {
            fail(e.getMessage());
        }
        String realText = driver.findElement(FIRST_RESULT).getText();
        assertTrue(realText.startsWith(text));
    }

    @AfterEach
    public void after() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

}

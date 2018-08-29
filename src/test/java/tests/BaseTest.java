package tests;

import core.pageobject.Utils.Config;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import core.pageobject.LoginPage;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class BaseTest {
    LoginPage loginPage;
    private WebDriver _driver;

    @BeforeTest
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        options.addArguments(Arrays.asList("--no-sandbox", "--no-first-run", "--disable-gpu"));
        ChromeDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        this._driver = driver;
        driver.get(Config.URL);
        this.loginPage = new LoginPage(driver);
    }

    @AfterTest
    public void tearDown() {
        _driver.quit();
    }
}

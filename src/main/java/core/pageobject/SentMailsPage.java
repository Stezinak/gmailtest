package core.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SentMailsPage extends MenuAndBaseFunctions {

    SentMailsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final WebDriver driver;

    public EmailBodyPage openEmailBySubject(String subject) {
        driver.findElement(By.xpath(String.format("//span[text()='%s']", subject))).click();
        return new EmailBodyPage(driver);
    }
}

package core.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class EmailBodyPage extends MenuAndBaseFunctions {

    EmailBodyPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private WebDriver driver;

    public String getEmailBodyText() {
        return driver.findElement(By.xpath("//div[@class='adn ads']//div[@dir='ltr']")).getText();
    }

}

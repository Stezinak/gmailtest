package core.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends MenuAndBaseFunctions {

    HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final WebDriver driver;

    @FindBy(xpath = "//div[contains(text(), 'Compose')]")
    private WebElement composeBtn;

    @FindBy(name = "to")
    private WebElement recipientInput;

    @FindBy(name = "subjectbox")
    private WebElement subjectInput;

    @FindBy(xpath = "//div[@aria-label='Message Body']")
    private WebElement mainTextInput;

    @FindBy(xpath = "//div[contains(text(), 'Send')]")
    private WebElement sendButton;

    public void sendEmail(String recipient, String subject, String body) {
        composeBtn.click();
        recipientInput.sendKeys(recipient);
        subjectInput.sendKeys(subject);
        mainTextInput.sendKeys(body);
        sendButton.click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Message sent.']")));
    }

}

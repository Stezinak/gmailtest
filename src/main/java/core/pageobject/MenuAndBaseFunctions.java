package core.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuAndBaseFunctions {

    public MenuAndBaseFunctions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private WebDriver driver;

    @FindBy(xpath = "//a[@title='Sent']")
    private WebElement sentButton;

    @FindBy(xpath = "//a[contains(@title,'Inbox')]")
    private WebElement inboxButton;

    @FindBy(xpath = "//a[contains(@href, 'SignOutOptions')]")
    private WebElement accountButton;

    @FindBy(xpath = "//a[text()='Sign out']")
    private WebElement signOutButton;

    public SentMailsPage openSentMailsPage() {
        sentButton.click();
        return new SentMailsPage(driver);
    }

    public LoginPage SignOut() {
        accountButton.click();
        signOutButton.click();
        return new LoginPage(driver);
    }

    public EmailBodyPage openEmailBySubject(String subject) {
        /*  Sometimes email didn't receive in a few seconds. I can took 1-120 seconds, so we have to wait.
            Updating using refresh button can help us get result faster */
        String emailXpath = String.format("//span[@class='bog']//span[text()='%s']", subject);
        while (driver.findElements(By.xpath(emailXpath)).size() == 0) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            refreshEmailInbox();
        }

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(emailXpath)));

        driver.findElement(By.xpath(emailXpath)).click();
        return new EmailBodyPage(driver);
    }

    private void refreshEmailInbox() {
        driver.findElement(By.xpath("//div[(@title='Refresh' or @aria-label='Refresh') ]")).click();
    }
}

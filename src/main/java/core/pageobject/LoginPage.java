package core.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "identifierId")
    private WebElement inputField;

    @FindBy(id = "identifierNext")
    private WebElement loginNextButton;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(id = "passwordNext")
    private WebElement passwordNextButton;

    public HomePage authorizationInGmail(String email, String password) {
        typeEmail(email);
        typePassword(password);
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
        return new HomePage(driver);
    }

    public HomePage authorizationChangeAccount(String email, String password) {
        driver.findElement(By.id("profileIdentifier")).click();
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("identifierLink")));
        driver.findElement(By.id("identifierLink")).click();
        return authorizationInGmail(email, password);
    }

    private void typeEmail(String email) {
        inputField.sendKeys(email);

        /*  We can avoid thread sleep because it isn't a good practice
            but standard selenium expectations (ExpectedConditions) don't work
            Using javascript here can avoid sleep but using this construction we are
            not closed to typical user behavior, so thread.sleep here is good.
        */

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        loginNextButton.click();
    }

    private void typePassword(String password) {
        passwordField.sendKeys(password);

        //  Same here
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        passwordNextButton.click();
    }


}

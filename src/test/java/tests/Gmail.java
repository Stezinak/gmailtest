package tests;

import core.pageobject.EmailBodyPage;
import core.pageobject.HomePage;
import core.pageobject.Utils.Config;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.time.LocalDateTime;

public class Gmail extends BaseTest {

    @Test
    public void sendAndCheckEmail() {
        String emailSubject = String.format("test subject, time: %s", LocalDateTime.now());
        String emailBody = "some text to body";

        HomePage homePage = loginPage.authorizationInGmail(Config.SENDER_EMAIL, Config.SENDER_PASSWORD);
        homePage.sendEmail(Config.RECEIVER_EMAIL, emailSubject, emailBody);
        homePage.openSentMailsPage();
        loginPage = homePage.SignOut();
        homePage = loginPage.authorizationChangeAccount(Config.RECEIVER_EMAIL, Config.RECEIVER_PASSWORD);
        EmailBodyPage emailBodyPage = homePage.openEmailBySubject(emailSubject);

        Assert.assertEquals(emailBody, emailBodyPage.getEmailBodyText(), "Text is not equal!");
    }

}

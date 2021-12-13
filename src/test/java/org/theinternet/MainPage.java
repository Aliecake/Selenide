package org.theinternet;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;

/**
 * Testing The-Internet Heroku app
 */
public class MainPage
{

    @Test
    public void abControlShouldExist()
    {
        open("https://the-internet.herokuapp.com/");
        $(By.xpath("//a[contains(text(),'A/B Testing')]")).click();
        $(By.tagName("html")).should(exist);
    }
}

package org.theinternet;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.impl.CollectionElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class checkboxes {

    @Test
    public static void checkboxesEnabledTest() {
        open("https://the-internet.herokuapp.com/");
        $(By.linkText("Checkboxes")).click();

        //gather all checkboxes
        ElementsCollection checkboxes = $$(By.cssSelector("input[type='checkbox']"));



        for(WebElement checkbox: checkboxes) {
            //check if enabled
            if (checkbox.isEnabled()) {
                checkboxTest(checkbox);
            } else {
                System.out.println("Not Enabled checkbox: " + checkbox);
            }

        }


    }

    public static void checkboxTest(WebElement checkbox) {

        if (checkbox.isDisplayed()) {
            System.out.println("Displayed" + checkbox);
            //clicks
            $(checkbox).click();
            //Keyboard functionality for accessibility
            $(checkbox).sendKeys(Keys.SPACE);
        } else {
            //not displayed, can see if selected
            System.out.println("Not Displayed " + checkbox);
        }
    }
}

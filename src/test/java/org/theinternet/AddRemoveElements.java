package org.theinternet;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;

public class AddRemoveElements {

    @Test
    public void addRemoveElements() {
        open("https://the-internet.herokuapp.com/");
        $(By.xpath("//a[contains(text(),'Add/Remove Elements')]")).click();
        //Item should be visible
        $(By.xpath("//button[contains(text(),'Add Element')]")).shouldBe(Condition.visible);
        //On load no added elements should exist
        $(By.className("#elements")).shouldNot(exist);
        //when clicked it should add an element
        $(By.xpath("//button[contains(text(),'Add Element')]")).click();

        //elements should now exist
        $(By.className("#elements")).isDisplayed();
        $(By.xpath("//body/div[2]/div[1]/div[1]/div[1]/button[1]")).should(exist);
        //add more elements
        for(int ii = 0; ii <5; ii++) {
            $(By.xpath("//button[contains(text(),'Add Element')]")).click();
        }

        ElementsCollection buttons = $$(".added-manually").shouldHave(size(6));


        //remove element
        $(By.xpath("//body/div[2]/div[1]/div[1]/div[1]/button[2]")).click();

        int buttonCount = $$(".added-manually").size();
        buttons.shouldHave((size(5)));

        //remove all elements
        for (int jj = buttonCount; jj >= 1; jj --) {
            try {
                $(By.xpath("//body/div[2]/div[1]/div[1]/div[1]/button[1]")).click();
                buttonCount --;
            } catch(Exception e) {
                System.out.println("There are no more buttons to delete" + buttonCount);
            }

        }
        //verify element is empty
        buttons.shouldHave((size(0)));

    }
}

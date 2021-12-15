package org.theinternet;

import com.codeborne.selenide.WebDriverRunner;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class ImageChecks {
    private static int invalidCount = 0;

    @Test
    public static void checkIfImagesAreValid() {
        open("https://the-internet.herokuapp.com/");
        $(By.xpath("//a[contains(text(),'Broken Images')]")).click();

        try {
            //get all the images and number of images
            List<WebElement> images = WebDriverRunner.getWebDriver().findElements(By.tagName("img"));
            int numberOfImages = images.size();

            System.out.println("There are a total number of images: " + numberOfImages);

            //loop and check if valid
            for (WebElement imgElement : images) {
                if(imgElement != null) {
                    //pass individual element for test

                    checkIfImageBroken(imgElement);
                }
            }

            if (invalidCount > 0) {
                Assert.fail("There are broken images, see above for list");
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void checkIfImageBroken(WebElement imgElement) {

        try {
            //client setup and request
            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(new HttpGet(imgElement.getAttribute("src")));
            //test image
            if (response.getCode() != HttpStatus.SC_OK) {
                System.out.println("broken image: " + response.getCode() + " " + imgElement.getAttribute("src"));
                invalidCount++;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

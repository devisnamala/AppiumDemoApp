package com.appium.pages;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class SwipePage extends BasePage {

    @FindBy(xpath = "//android.view.View[@content-desc=\"Swipe\"]")
    WebElement swipeIcon;

    @FindBy(xpath = "//android.view.View[@content-desc=\"Webview\"]")
    WebElement webView;


    @FindBy(xpath = "//android.widget.TextView[@text=\"Swipe horizontal\"]")
    RemoteWebElement element;

//    @FindBy(xpath="//android.widget.ScrollView[@content-desc=\"Swipe-screen\"]/android.view.ViewGroup")
//    WebElement swipeScreen;

    By swipeScreen= By.xpath("//android.widget.ScrollView[@content-desc=\"Swipe-screen\"]/android.view.ViewGroup");

    @FindBy(xpath="//android.widget.ImageView[@content-desc=\"WebdriverIO logo\"]")
    WebElement target;



    public void clickSwipeIcon() throws InterruptedException {
        click(webView);
        Thread.sleep(1000);
    }




    public void findMe() throws InterruptedException {
        String text=driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                        ".scrollIntoView(new UiSelector().textContains(\"Test in Real Environments\").instance(0))"
        )).getText();

        System.out.println(text);
        Thread.sleep(1000);
    }


    public SwipePage(AppiumDriver driver) {
        super(driver);
    }
}

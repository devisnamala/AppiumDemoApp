package com.appium.pages;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.Assertions;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BasePage {
    private final WebDriverWait wait;
    protected AppiumDriver driver;

    private PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
   
    public BasePage(AppiumDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(70));
    }

    public void waitForVisibility(WebElement e) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(600));
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    public void clear(WebElement e) {
        waitForVisibility(e);
        e.clear();
    }
    public void click(WebElement webElement) {
        waitForVisibility(webElement);
        webElement.click();
    }

    public String getAttribute(WebElement element, String attribute) {
        waitForVisibility(element);
        return element.getAttribute(attribute);
    }

    public void sendKeys(WebElement e, String txt) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }

    public void Assertcheck(WebElement e,String expectedText){
        waitForVisibility(e);
        String actualMessage= e.getText();
        Assertions.assertEquals(actualMessage,expectedText);
        Assertions.assertEquals(expectedText,actualMessage);
    }

    public void dragAndDrop(By sourceLocator, By destinationLocator) {
        RemoteWebElement source = (RemoteWebElement) wait.until(ExpectedConditions.elementToBeClickable(sourceLocator));
        RemoteWebElement destination = (RemoteWebElement) wait.until(ExpectedConditions.elementToBeClickable(destinationLocator));

        driver.executeScript("mobile: dragGesture", Map.of(
                "elementId", source.getId(),
                "endX", destination.getLocation().getX() + (destination.getSize().getWidth() / 2),
                "endY", destination.getLocation().getY() + (destination.getSize().getHeight() / 2)
        ));
    }

    public void selectDropdownValue(WebElement dropdown, WebElement value) {
        dropdown.click();
        waitForVisibility(value);
        value.click();
    }

    public void swipe(SwipeDirection direction) {
        WebElement ele = getElement(direction);
        int startX, startY, endX, endY;

        switch (direction) {
            case SWIPE_RIGHT:
                startX = ele.getRect().x + (ele.getSize().width / 4);
                startY = ele.getRect().y + (ele.getSize().height / 2);
                endX = ele.getRect().x + (ele.getSize().width * 3 / 4);
                endY = startY;
                break;
            case SWIPE_LEFT:
                startX = ele.getRect().x + (ele.getSize().width * 3 / 4);
                startY = ele.getRect().y + (ele.getSize().height / 2);
                endX = ele.getRect().x + (ele.getSize().width / 4);
                endY = startY;
                break;
            case SWIPE_DOWN:
                startX = ele.getRect().x + (ele.getSize().width / 2);
                startY = ele.getRect().y + (ele.getSize().height / 4);
                endX = startX;
                endY = ele.getRect().y + (ele.getSize().height * 3 / 4);
                break;
            case SWIPE_UP:
                startX = ele.getRect().x + (ele.getSize().width / 2);
                startY = ele.getRect().y + (ele.getSize().height * 3 / 4);
                endX = startX;
                endY = ele.getRect().y + (ele.getSize().height / 4);
                break;
            default:
                throw new IllegalArgumentException("Invalid swipe direction: " + direction);
        }

        performSwipe(startX, startY, endX, endY);
    }

    private void performSwipe(int startX, int startY, int endX, int endY) {
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }

    private WebElement getElement(SwipeDirection direction) {
        if (driver instanceof AndroidDriver) {
            return driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"You found me!!!\"]"));
        }
        else if (driver instanceof IOSDriver && (direction == SwipeDirection.SWIPE_RIGHT || direction == SwipeDirection.SWIPE_LEFT)) {
            return driver.findElement(AppiumBy.xpath("IOS_GEN_LOCATOR_02"));
        } else {
            return driver.findElement(AppiumBy.accessibilityId("BDSBHBBHKJ"));
        }
    }

    public enum SwipeDirection {
        SWIPE_RIGHT,
        SWIPE_LEFT,
        SWIPE_DOWN,
        SWIPE_UP
    }

}

package com.appium.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class DragPage extends BasePage {
    private WebDriverWait wait;
    private AppiumDriver driver;

    public DragPage(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(70));
        initializeLocators();
    }

    private Map<By, By> dragToDropMap = new HashMap<>();

    private void initializeLocators() {
        for (int i = 1; i <= 9; i++) {
            String posXPath = String.format("//android.view.ViewGroup[@content-desc='drop-%s%d']/android.view.ViewGroup", getColumnPrefix(i), getRowNumber(i));
            String pieceXPath = String.format("//android.view.ViewGroup[@content-desc='drag-%s%d']/android.widget.ImageView", getColumnPrefix(i), getRowNumber(i));
            dragToDropMap.put(By.xpath(pieceXPath), By.xpath(posXPath));
        }
    }

    @FindBy(xpath = "//android.view.View[@content-desc='Drag']")
    WebElement dragIcon;

    private String getColumnPrefix(int index) {
        switch ((index - 1) % 3) {
            case 0:
                return "l";
            case 1:
                return "c";
            case 2:
                return "r";
            default:
                return "";
        }
    }

    private int getRowNumber(int index) {
        return (index - 1) / 3 + 1;
    }

    public DragPage clickDragicon() {
        click(dragIcon);
        return this;
    }

    public void dragAndDrop() {
        for (Map.Entry<By, By> entry : dragToDropMap.entrySet()) {
            dragAndDrop(entry.getKey(), entry.getValue());
        }
    }
}

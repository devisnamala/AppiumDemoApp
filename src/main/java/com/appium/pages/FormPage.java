package com.appium.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormPage extends BasePage{
    public FormPage(AppiumDriver driver){
        super(driver);
    }

    @FindBy(xpath="//android.view.View[@content-desc=\"Forms\"]")
    WebElement FormPage;

    @FindBy(xpath="//android.widget.EditText[@content-desc=\"text-input\"]")
    WebElement input;

    @FindBy(xpath="//android.widget.Switch[@content-desc=\"switch\"]")
    WebElement switchElement;

    @FindBy(xpath = "//android.widget.TextView[@content-desc='switch-text']")
    WebElement switchText;

    @FindBy(xpath="//android.widget.EditText[@resource-id=\"text_input\"]")
    WebElement dropDown;

    @FindBy(xpath="//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Appium is awesome\"]")
    WebElement value;

    @FindBy(xpath="//android.widget.TextView[@text=\"Active\"]")
    WebElement activeButton;

    @FindBy(xpath="//android.widget.TextView[@resource-id=\"android:id/message\"]")
    WebElement message;

    @FindBy(xpath="//android.widget.Button[@resource-id=\"android:id/button1\"]")
    WebElement ok;

    public FormPage clickFormTab(){
        click(FormPage);
        return this;
    }

    public FormPage fillInputField(String text){
        sendKeys(input, text );
        return this;
    }

    public void toggleSwitchBasedOnText() {
        String currentText = switchText.getText();
        if (currentText.contains("Click to turn the switch OFF")) {
            System.out.println("Switch is already ON. No action needed.");
        } else {
            switchElement.click();
            System.out.println("Switch was OFF. Now it's turned ON.");
        }
    }

    public void dropDownSelection(){
        selectDropdownValue(dropDown, value);
    }

    public void clickButton(){
        click(activeButton);
    }

    public void statusCheck(String expectedtext){
        Assertcheck(message,expectedtext);
        click(ok);
    }



}

package com.appium.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {
    @FindBy(xpath = "//android.widget.TextView[@text=\"\uDB80\uDF42\"]")
    WebElement logintab;


    @AndroidFindBy(accessibility = "input-email")
    private WebElement email;

    @AndroidFindBy(accessibility = "input-password")
    private WebElement password;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='button-LOGIN']/android.view.ViewGroup")
    private WebElement loginbutton;

    @AndroidFindBy(id="android:id/message")
    private WebElement message;

    @AndroidFindBy(id="android:id/button1")
    private WebElement button;


    public LoginPage clickLoginTab(){
        click(logintab);
        return this;
    }

    public LoginPage enterUserName(String username){
        clear(email);
        sendKeys(email,username);
        return this;
    }

    public LoginPage enterPassword(String pass){
        clear(password);
        sendKeys(password, pass);
        return this;
    }

    public LoginPage clickSubmit(){
        click(loginbutton);
        return this;
    }

    public LoginPage getMessage(String expectedText) throws InterruptedException {
        Assertcheck(message, expectedText);
        click(button);
        return this;
    }
    public LoginPage(AppiumDriver driver) {
        super(driver);
    }
}

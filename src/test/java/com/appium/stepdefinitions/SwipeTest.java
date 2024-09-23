package com.appium.stepdefinitions;

import com.appium.pages.SwipePage;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SwipeTest {

    private AppiumDriver driver = Hooks.getDriver();
    SwipePage swipePage=new SwipePage(driver);
    @Given("I am on swipe page")
    public void i_am_on_swipe_page() throws InterruptedException {
        swipePage.clickSwipeIcon();
    }

    @Then("I swipe down to find robot")
    public void i_swipe_down_to_find_robot() throws InterruptedException {
        swipePage.findMe();

    }
}

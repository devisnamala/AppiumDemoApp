package com.appium.stepdefinitions;

import com.appium.pages.DragPage;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DragandDropTest {
    private AppiumDriver driver = Hooks.getDriver();
    DragPage dragpage =new DragPage(driver);


    @Given("I am on the Drag page")
    public void i_am_on_the_drag_page() {
        dragpage.clickDragicon();

    }
    @When("I retrieve the element to drag from the source location")
    public void i_retrieve_the_element_to_drag_from_the_source_location() {
        dragpage.dragAndDrop();

    }
    @Then("I drop it to the target location")
    public void i_drop_it_to_the_target_location() {
        System.out.println("drag and drop");

    }
}

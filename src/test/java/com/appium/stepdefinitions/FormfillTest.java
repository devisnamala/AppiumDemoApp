package com.appium.stepdefinitions;

import com.appium.pages.FormPage;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FormfillTest {
    private AppiumDriver driver = Hooks.getDriver();
    FormPage formPage=new FormPage(driver);
    @Given("I am on the form page")
    public void i_am_on_the_form_page() {
        formPage.clickFormTab();
    }

    @When("I fill out the form with input fields as {string}")
    public void i_fill_out_the_form_with_input_fields_as(String string) {
        formPage.fillInputField(string);
    }

    @When("select switch")
    public void select_switch() {
        formPage.toggleSwitchBasedOnText();
    }

    @When("select value from dropdown")
    public void select_value_from_dropdown() {
        formPage.dropDownSelection();
    }

    @When("select the button as active")
    public void select_the_button_as_active() {
        formPage.clickButton();
    }

    @Then("it shows message as {string}")
    public void it_shows_message_as(String string) {
        formPage.statusCheck(string);
    }
}

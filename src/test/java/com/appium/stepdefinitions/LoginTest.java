package com.appium.stepdefinitions;

import com.appium.pages.LoginPage;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

public class LoginTest {

  private AppiumDriver driver = Hooks.getDriver();
  LoginPage loginpage=new LoginPage(driver);

  @Given("I am on loginpage")
  public void i_am_on_loginpage() {
    loginpage.clickLoginTab();
  }

  @When("I enter username as {string}")
  public void i_enter_username_as(String username) {
    loginpage.enterUserName(username);
    System.out.println("Entered username");
  }

  @When("I enter password as {string}")
  public void i_enter_password_as(String password) {
    loginpage.enterPassword(password);
    System.out.println("On login page"+password);
  }

  @And("I click on login")
  public void i_click_on_login() {
    loginpage.clickSubmit();
    System.out.println("On login page");
  }

  @Then("I should get message as {string}")
  public void i_should_get_message_as(String expectedMessage) throws InterruptedException {
    loginpage.getMessage(expectedMessage);
    System.out.println("On login page");
  }
}



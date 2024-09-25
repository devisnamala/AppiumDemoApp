@smoke
Feature: Form Filling

  Scenario: Filling out the form with specific data
    Given I am on the form page
    When I fill out the form with input fields as "form filling"
    And select switch
    And select value from dropdown
    And select the button as active
    Then it shows message as "This button is activ"
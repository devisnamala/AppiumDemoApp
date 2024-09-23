@regression
Feature: Drag and Drop

  Scenario: Perform drag and drop action
    Given I am on the Drag page
    When I retrieve the element to drag from the source location
    Then I drop it to the target location

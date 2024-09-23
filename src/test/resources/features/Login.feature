@regression
Feature: login functionality
@LoginFeature
  Scenario Outline: Login
  Given I am on loginpage
  When I enter username as "<username>"
  And I enter password as "<password>"
  And I click on login
  Then I should get message as "You are logged in!"
  Examples:
    | username | password |
    | user1@gmail.com | pass@2000 |

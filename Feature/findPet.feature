@findPets @regression
Feature: feature find the pet from database
  Scenario Outline:find pets according to the status
    Given set the token to find pets
    When get call to find pets status is "<status>"
    Examples:
      |status|
    |available|
    |pending  |
    |sold     |
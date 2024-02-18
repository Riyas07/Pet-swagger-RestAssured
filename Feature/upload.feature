@upload @sanity @regression
Feature: feature to upload file
  Scenario Outline: upload file into pet swagger
    Given token generation
    When test update petid "<id>"  additional data "<additionalData>" the payload and trigger
    Examples:
      | id |additionalData|
      |101 |by riyas      |


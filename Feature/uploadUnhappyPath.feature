@uploadUnhappy @sanity @regression
Feature: to validate the upload negevite scenario
Scenario Outline: upload file with invalid id attribute
Given token generation unhappy
When test update petid "<id>"  additional data "<additionalData>" the payload and trigger unhappy
Examples:
| id |additionalData|
|sam |by riyas    |
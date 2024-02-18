@addpet @sanity @regression
Feature: feature to add the pet into the store
  Scenario Outline: add the pet info into store
    Given set the token
    When buillt the payload with following details id "<id>" and name "<name>" and status "<status>"
    Then add the pet category
    |id|name|
    |1 |nadan|
    Then add photo urls
    |urls|
    |string1|
    |string2|
    |string3|
    |string4|
    |string5|
    Then add the tags
      |id|name |
      |1 |nadan|
      |2 |patti|
      |3 |pomeranian|
      |4 |foreign|
    Then generate the pet payload
    Then validate the schema of the payload
    Then trigger the POST request to add resource
    Examples:
      | id | name |status|
      |191 |darry |available|
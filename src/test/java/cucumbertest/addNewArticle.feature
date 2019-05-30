Feature: Adding new Article
  A new Article can be added by admin

  Scenario Outline: Adding new Article by admin
    Given I am logged in as admin
    When I go to Content page
    And click Add Content button
    And click to Article link
    Then the page title should start with "Create Article"

    #fill in new Article
    When fill in Title field with "<title>"
    And fill in Body field with "<page_content>"
    And click Save button
    And info message should appear for "<title>" article
    Then the page title should start with "<title>"
    And page should be opened with "<page_content>"

    #check new Article
    And item "<title>" should have type Article in Content table
    And item "<title>" should have status Published in Content table
    Examples:
      | title    | page_content                       |
      | Article1 | Article1 is created using Cucumber |
      | Article2 | Article2 is created using Cucumber |
      | Article3 | Article3 is created using Cucumber |
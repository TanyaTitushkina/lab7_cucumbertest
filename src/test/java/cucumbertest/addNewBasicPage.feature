Feature: Adding new Basic Page
  A new Basic Page can be added by admin

  Scenario Outline: Adding new Basic Page by admin
    Given I am logged in as admin
    When I go to Content page
    And click Add Content button
    And click to Basic Page link
    Then the page title should start with "Create Basic page"

    #fill in new Basic page
    When fill in Title field with "<title>"
    And fill in Body field with "<page_content>"
    And select Provide Menu Link option
    And click Save button
    And info message should appear for "<title>" basic page
    Then the page title should start with "<title>"
    And page should be opened with "<page_content>"

    #check new Basic page
    And item "<title>" should have type Basic page in Content table
    And item "<title>" should have status Published in Content table
    Examples:
      | title     | page_content                         |
      | FrodoCucu | Frodo page is created using Cucumber |
      | SamCucu   | Sam page is created using Cucumber   |
      | BilboCucu | Bilbo page is created using Cucumber |
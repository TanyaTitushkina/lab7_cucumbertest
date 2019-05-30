Feature: Edit existing Basic Page
  Existed Basic Page can be edited by admin

  Scenario Outline: Edit Basic Page content by admin
    Given I am logged in as admin
    When I go to Content page
    And click Edit button for content item with "<title>"
    Then the page title should start with "<title>"

    #edit Basic page content
    When edit Body field with "<updated_page_content>"
    And click Save button
    Then info message "Basic page <title> has been updated." should appear for edited basic page

    #check updated Basic page content
    When open content item with "<title>"
    Then the page title should start with "<title>"
    And page should be opened with "<updated_page_content>"
    Examples:
      | title     | updated_page_content   |
      | FrodoCucu | updated using Cucumber |
      | SamCucu   | updated using Cucumber |
      | BilboCucu | updated using Cucumber |
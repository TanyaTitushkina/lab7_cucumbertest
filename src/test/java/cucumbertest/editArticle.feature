Feature: Edit existing Article
  Existed Article can be edited by admin

  Scenario Outline: Edit Article content by admin
    Given I am logged in as admin
    When I go to Content page
    And click Edit button for content item with "<title>"
    Then the page title should start with "<title>"

    #edit Article content
    When edit Body field with "<updated_page_content>"
    And click Save button
    Then info message "Article <title> has been updated." should appear for edited basic page

    #check updated Article content
    When open content item with "<title>"
    Then the page title should start with "<title>"
    And page should be opened with "<updated_page_content>"
    Examples:
      | title    | updated_page_content   |
      | Article1 | updated using Cucumber |
      | Article2 | updated using Cucumber |
      | Article3 | updated using Cucumber |
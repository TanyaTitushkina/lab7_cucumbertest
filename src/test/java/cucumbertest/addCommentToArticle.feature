Feature: Add comment to an Article
  As an admin I can add comment to an Article

  Scenario Outline: Add comment to an Article by admin
    Given I am logged in as admin
    When I go to Home page
    And open Article with "<title>"
    And fill in Subject field with "<subject>"
    And fill in Comment field with "<comment>"
    And click Save button
    Then info message about added comment should appear

    #check Article's comment content
    And should appear comment with "<subject>" and "<comment>"

    Examples:
      | title    | subject     | comment           |
      | Article1 | test Cucu 1 | hello from Cucu 1 |
      | Article2 | test Cucu 2 | hello from Cucu 2 |
      | Article3 | test Cucu 3 | hello from Cucu 3 |

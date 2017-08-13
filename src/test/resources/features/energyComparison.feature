Feature: Compare energy prices for Gas and Electricity
  In order to make better decision for energy suppliers
  As a user on CTM
  I want to compare prices for gas and electricity

  Scenario: In order to compare the price, User needs to provide contact details
    Given user is on CTM energy comparison home
    When user finds the postcode "PE26YS"
    And user has the bill
#    When user wants to compare "Gas & Electricity"
    And user provides the "dual" supplier as "Breeze"
    When user provides the recent "Electricity" bill information as:
      | Tarrif type            | All Good June V1 |
      | Payment type           | Monthly Direct Debit   |
      | Current Annually usage | kwh,100,Annually       |
      | Gas main source        | No                     |
    When user provides the recent "Gas" bill information as:
      | Current Annually usage | kwh,100,Annually       |
      | Gas main source        | No                     |
    When user confirms the terms and conditions
    And compares the prices
    Then user should see error "Complete this section to continue"

  Scenario: User should be able to compare the prices when user has the bill handy
    #    And user provides the preferences as:
#      | Interested Tarrif | Variable tarrif         |
#      | Payment type      | Monthly direct debit |
#      | Refine Results    | No                   |
#      | Email address     |                      |
    Then user should be on results page

#  Scenario: User should be able to compare the prices when user doesn't has the bill handy

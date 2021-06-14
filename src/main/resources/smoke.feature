Feature: Smoke
  As a user
  I want to test all main site functionality
  So that I can be sure that site works correctly

  Scenario Outline: Check user authorization
    Given User opens "https://www.amazon.com" page
    When User clicks 'Sign in' button
    And User logs in site as login: "<login>" password: "<password>"
    Then User checks that user is authorized as "<user name>" "<password>"

    Examples:
      | login                       | password | user name  |
      | amazonTestAcc1234@gmail.com | qweasd   | Selindzher |
      | testAmazon1235@gmail.com    | qwea     | amazonTest |
      | testAmazon1235@gmail.com    |          | amazonTest |
      | qqq                         | qqq      | testAmazon |
      |                             | qweasd   | amazonTest |
      | #@$$@#$                     | qweqwe   | test       |
      | testAmazon1235@gmail.com    | @#$@#$   | amTest     |



  Scenario Outline: Check changing password
    Given User opens "https://www.amazon.com" page
    And User clicks 'Sign in' button
    And User logs in site as login: "amazonTestAcc1234@gmail.com" password: "<current password>"
    And User clicks 'Sign in' button
    And User clicks on 'Login and security' button
    And User clicks on ' Edit password' button
    When User enter "<current password>" as 'Current password'
    And User enter "<new password>" to 'New password' input field
    And User enter "<new password>" to 'Reenter new password' input field
    And User clicks on 'Save changes' button
    Then User checks that information box about password changing is "<password change status>"

    Examples:
      | current password | new password | password change status |
      | qweasd           |              | false                  |
      | qweasd           | qweasd       | true                   |
      | qweasd           | qwert        | false                  |
      | qweasd           | @#$@#$       | true                   |
      | @#$@#$           | qweasd       | true                   |



  Scenario Outline: Check product cart
    Given User opens "https://www.amazon.com" page
    And User searches "QNAP TS-451D2-4G " using search field
    When User clicks on first product
    And User clicks on 'Add to cart' button
    And User clicks on 'Cart' button
    Then User checks amount of products into the cart
    When User clicks on 'Quantity' button
    And User clicks on 10+ dropdown list item
    And User enters "<amount of products>" to 'Quantity input field'
    And User clicks 'Update' button
    Then User checks that total price is changed accordingly to <amount of products> or existence of error
    When User clicks on 'Delete' button
    Then User checks that cart is empty

    Examples:
      | amount of products |
      | 999                |
      | 0                  |
      | 10                 |
      | -5                 |



  Scenario: Check product purchasing
    Given User opens "https://www.amazon.com" page
    When User clicks 'Sign in' button
    And User logs in site as login: "amazonTestAcc1234@gmail.com" password: "qweasd"
    And User searches "QNAP TS-451D2-4G" using search field
    And User clicks on first product
    When User clicks on 'Buy now' button
    And User clicks on 'Country & Region' drop-down list button
    And User clicks on 'Ukraine' item into drop-down list
    And User enters his "Ivan", "Boost red street", "Dnipro", "09898" and "380690000000"
    And User clicks 'Use this address' button
    And User clicks on 'Continue' button
    And User checks that current page is 'Select a payment method' page
    And User checks that 'Add credit cart' button is visible
    And User checks that 'Enter a gift card, voucher or promotional code' link is visible
    And User checks that 'Learn more' link in the 'Amazon store card' block is visible
    Then User checks that 'Add a personal checking account' button is visible



  Scenario Outline: Check product adding to wish list
    Given User opens "https://www.amazon.com" page
    When User clicks 'Sign in' button
    And User logs in site as login: "amazonTestAcc1234@gmail.com" password: "qweasd"
    And User searches "<product name>" using search field
    And User clicks on first product
    When User clicks on 'Add to list' button
    And User enters list name - "<list name>"
    And User clicks on 'Create list' button
    And User clicks on 'View List' button
    Then User checks that "<product name>" is added to wish list correctly

    Examples:
      | product name     | list name |
      | QNAP TS-451D2-4G | listName  |
      | Xerox            | #$@$      |
      | Apple            | 123123132 |




  Scenario: Check product filter
    Given User opens "https://www.amazon.com" page
    And User clicks 'Catalogue' button
    And User clicks 'Computer' menu item
    And User clicks 'Data storage' menu item
    When User clicks 'External Hard Drives' button
    And User clicks '4 stars & up' button
    And User clicks 'Toshiba' button
    And User clicks 'Price $50 to $100' button
    And User clicks 'Portable' button
    And User clicks 'PC' button for 'Platform support'
    And User clicks '4TB & Above' for 'Hard Drive Size' filter
    And User clicks 'Tablet' button as 'Compatible Devices'
    And User clicks on first 'Toshiba' product
    And User checks that product is external hard drive
    And User checks that product has rate '4 stars' or higher
    And User checks that product is manufactured by 'Toshiba'
    And User checks that product's price is between '50$' and '100$'
    And User checks that product is portable
    And User checks that product supports "PC" platform
    And User checks that product's capacity is 4TB or above
    Then User checks that product comparable with "Tablet"




  Scenario Outline: Check adding address to list
    Given User opens "https://www.amazon.com" page
    And User clicks 'Sign in' button
    And User logs in site as login: "amazonTestAcc1234@gmail.com" password: "qweasd"
    And User clicks on 'Deliver to' button
    And User clicks on 'Manage address book' button
    When User clicks on 'Add address' panel
    And User clicks on 'Country & Region' drop-down list button
    And User clicks on 'Ukraine' item into drop-down list
    And User enters his "<Name>", "<Street>", "<City>", "<ZipCode>" and "<Phone number>"
    And User clicks 'Add address' button
    And User checks that address is added to addressBook with "<Name>", "<Street>", "<City>", "<ZipCode>" and "<Phone number>"
    And User checks that name "<Name>" was added to addressBook correctly
    And User checks that street "<Street>" was added to addressBook correctly
    And User checks that city "<City>" was added to addressBook correctly
    And User checks that zipCode "<ZipCode>" was added to addressBook correctly
    And User checks that phone number "<Phone number>" was added to addressBook correctly
    Then User checks that country "Ukraine" was added to addressBook correctly

    Examples:
      | Name | Street             | City         | ZipCode | Phone number |
      | Ivan | Ross Gellar street | New Zhytomir | 09898   | 380690000000 |
      | Ivan | Ross Gellar street | New Zhytomir | 09898   | #$##$#$#$#$# |
      | Ivan | Ross Gellar street | New Zhytomir | 09898   | qweqweqweqww |
      | Ivan | Ross Gellar street | New Zhytomir | 09898   |              |
      | Ivan | Ross Gellar street | New Zhytomir |         | 380690000000 |
      | Ivan | Ross Gellar street |              | 09898   | 380690000000 |
      | Ivan |                    | New Zhytomir | 09898   | 380690000000 |
      |      | Ross Gellar street | New Zhytomir | 09898   | 380690000000 |





  Scenario Outline: Check account switching
    Given User opens "https://www.amazon.com" page
    And User clicks 'Sign in' button
    And User logs in site as login: "amazonTestAcc1234@gmail.com" password: "qweasd"
    And User hovers on 'Sign In' button
    And User clicks 'Switch accounts' button
    When User clicks on 'Add account' button
    And User logs in site as login: "<login>" password: "<password>"
    Then User checks that account is switched to "<account name>"

    Examples:
      | login                    | password | account name |
      | testAmazon1235@gmail.com | qweasd   | amazonTest   |




  Scenario: Check Amazon Prime videos restriction to Ukraine
    Given User opens "https://www.amazon.com" page
    And User clicks 'Sign in' button
    And User logs in site as login: "amazonTestAcc1234@gmail.com" password: "qweasd"
    And User hovers on 'Sign In' button
    And User clicks 'Watchlist' button
    When User searches 'The boys' using search field
    And User clicks 'The boys' series block
    And User clicks 'Watch Trailer' button
    Then User checks 'Service Area Restriction' popup visibility




  Scenario Outline: Check products sorting
    Given User opens "https://www.amazon.com" page
    And User searches "Nesquik" using search field
    When User clicks 'Sorting type' drop-down list button
    And User chooses "<sorting type>" and click this sorting type dropdown list item
    Then User checks that products are sorted by "<sorting type>"

    Examples:
      | sorting type |
      | asc          |
      | desc         |







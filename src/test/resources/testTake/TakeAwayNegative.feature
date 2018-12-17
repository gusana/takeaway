Feature: Scenarios for TakeAway negative tests

  Scenario: abracadabra is in address
    Given User navigates to "http://lieferando.de"
    When User puts index "adracadabratger"
    Then Message "Bitte gib Deine Straße und Hausnummer ein" should be shown

  Scenario: Error message is on main page
    Given User navigates to "http://lieferando.de"
    When User puts index "Marzahn"
    When User clicks on title
    Then User presses Find button
    Then User sees error message "Die eingegebene Adresse ist leider inkorrekt. Probiere es noch einmal."
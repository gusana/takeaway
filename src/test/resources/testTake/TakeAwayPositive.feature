Feature:  Scenarios for TakeAway positive tests

  Scenario: User chooses to find certain restaurant
    Given User navigates to "http://lieferando.de"
    When User puts index "Zola"
    Then User presses Enter button
    Then Certain restaurant "Zola" should be on the page

  Scenario: User puts proper index
    Given User navigates to "http://lieferando.de"
    When User puts index "12619"
    Then User presses Enter button
    Then Page with offers is shown
    Then Address "12619" will be shown on page

  Scenario: User puts the whole address
    Given User navigates to "http://lieferando.de"
    When User puts index "Teterower Ring 6, 12619 Berlin"
    Then User chooses from drop-down list
    Then Page with offers is shown
    Then Address "Teterower Ring 6, 12619 Berlin" will be shown on page

  Scenario: User puts name of the city
    Given User navigates to "http://lieferando.de"
    When User puts index "Hamburg"
    Then User presses Enter button
    Then Page with offers is shown
    Then Address "Hamburg" will be shown on page

  Scenario: User chooses first address from drop-down list with Enter button
    Given User navigates to "http://lieferando.de"
    When User puts index "Pankow"
    Then User chooses from drop-down list
    Then User presses Enter button
    Then Page with offers is shown

  Scenario: User chooses not first address from drop-down list with Enter button
    Given User navigates to "http://lieferando.de"
    When User puts index "Pankow"
    Then User chooses not first address from the list
    Then User presses Enter button
    Then Page with offers is shown

  Scenario: User chooses not first address from drop-down list with Find button
    Given User navigates to "http://lieferando.de"
    When User puts index "Pankow"
    Then User chooses not first address from the list
    Then User presses Find button
    Then Page with offers is shown

  Scenario: User puts name of area to filter
    Given User navigates to "http://lieferando.de"
    When User puts index "mitte"
    Then User chooses from drop-down list
    Then Page with offers is shown

  Scenario Outline: Some address is put
    Given User navigates to "http://lieferando.de"
    When User puts index "<address>"
    Then Addresses with this number should be shown

    Examples:
    | address |
    | Berlin |
    | 1261 |
    | Mitte |
    | Alexanderplatz |
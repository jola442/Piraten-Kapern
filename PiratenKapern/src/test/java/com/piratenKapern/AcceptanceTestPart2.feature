Feature: Part 2 of the acceptance tests
  Scenario Outline: A player draws a Sorceress fortune card
    Given a player wishes to roll dice thrice for their turn
    When the player draws a SORCERESS and rolls <roll_1>
    And the player wishes to re-roll dice numbers <dice_numbers>
    And the player gets <roll_2>
    And the player wishes to re-roll dice numbers <dice_numbers_2>
    And the player gets <roll_3>
    Then the player scores <score> for this round

    Examples:
      |row|score|roll_1                                                       |dice_numbers|roll_2               |dice_numbers_2|roll_3        |
      |77 |500  |DIAMOND, DIAMOND, SWORD, MONKEY, COIN, PARROT, PARROT, PARROT|6,7,8       |SKULL, MONKEY, MONKEY|6             |MONKEY        |
      |78 |1000 |SKULL, SKULL, SKULL, PARROT, PARROT, PARROT, SWORD, SWORD    |1           |PARROT               |7,8           |PARROT, PARROT|
      |70 |2000 |SKULL, PARROT, PARROT, PARROT, PARROT, MONKEY, MONKEY, MONKEY|6,7,8       |SKULL, PARROT, PARROT|6             |PARROT        |


  Scenario Outline: A player draws a Monkey Business fortune card and rolls dice once
    Given a player wishes to roll dice only once for their turn
    When the player draws a MONKEY_AND_PARROT and rolls <roll>
    Then the player scores <score> for this round

    Examples:
      |row|score|roll                                                       |
      |82 |1100 |MONKEY, MONKEY, MONKEY, PARROT, PARROT, PARROT, SKULL, COIN|
      |84 |0    |SKULL, SKULL, SKULL, MONKEY, MONKEY, MONKEY, PARROT, PARROT|

  Scenario: A player draws a Monkey Business fortune card and rolls dice twice (Row 83)
    Given a player wishes to roll dice twice for their turn
    When the player draws a MONKEY_AND_PARROT and rolls MONKEY, MONKEY, SWORD, SWORD, PARROT, PARROT, COIN, COIN
    And the player wishes to re-roll dice numbers 3,4
    And the player gets MONKEY, PARROT
    Then the player scores 1700 for this round

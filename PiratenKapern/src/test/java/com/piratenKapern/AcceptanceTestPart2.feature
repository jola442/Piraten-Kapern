Feature: Part 2 of the acceptance tests
  Scenario Outline: A player draws a sorceress fortune card
    Given a player wishes to roll dice only once for their turn
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
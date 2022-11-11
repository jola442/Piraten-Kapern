Feature: Part 2 of the acceptance tests
  Scenario Outline: A player draws a Sorceress fortune card
    Given a player wishes to roll dice thrice for their turn
    When the player draws a SORCERESS and rolls <roll_1>
    And the player re-rolls dice numbers <dice_numbers>
    And the player gets <roll_2>
    And the player re-rolls dice numbers <dice_numbers_2>
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
    And the player re-rolls dice numbers 3,4
    And the player gets MONKEY, PARROT
    Then the player scores 1700 for this round

  Scenario: A player draws a Treasure Chest fortune card, rolls dice thrice, places and takes out dice from the chest
    Given a player wishes to roll dice thrice for their turn
    When the player draws a CHEST and rolls PARROT, PARROT, PARROT, SWORD, SWORD, DIAMOND, DIAMOND, COIN
    And the player keeps dice numbers 6,7,8 in the chest
    And the player re-rolls dice numbers 4,5
    And the player gets PARROT, PARROT
    And the player keeps dice numbers 1,2,3,4,5 in the chest
    And the player takes out dice numbers 6,7,8 from the chest
    And the player re-rolls dice numbers 6,7,8
    And the player gets SKULL, COIN, PARROT
    Then the player scores 1100 for this round

  Scenario: A player draws a Treasure Chest fortune card, rolls dice thrice, places dice in the chest
    Given a player wishes to roll dice thrice for their turn
    When the player draws a CHEST and rolls SKULL, SKULL, PARROT, PARROT, PARROT, COIN, COIN, COIN
    And the player keeps dice numbers 6,7,8 in the chest
    And the player re-rolls dice numbers 3,4,5
    And the player gets DIAMOND, DIAMOND, COIN
    And the player keeps dice numbers 5 in the chest
    And the player re-rolls dice numbers 3,4
    And the player gets SKULL, COIN, PARROT
    Then the player scores 600 for this round

    Scenario Outline: A player rolls dice once and scores a full chest bonus
      Given a player wishes to roll dice only once for their turn
      When the player draws a <fortune_card> and rolls <roll>
      Then the player scores <score> for this round
      Examples:
        |row|fortune_card     |score|roll                                                         |
        |97 |COIN             |400  |MONKEY, MONKEY, MONKEY, SWORD, SWORD, SWORD, DIAMOND, PARROT |
        |98 |CAPTAIN          |1800 |MONKEY, MONKEY, MONKEY, SWORD, SWORD, SWORD, COIN, COIN      |
        |99 |COIN             |1000 |MONKEY, MONKEY, MONKEY, SWORD, SWORD, SWORD, SWORD, DIAMOND  |
        |103|MONKEY_AND_PARROT|1200 |MONKEY, MONKEY, PARROT, COIN, COIN, DIAMOND, DIAMOND, DIAMOND|
    
    Scenario: A player draws a Two Swords Sea Battle card and rolls dice twice for their turn
      Given a player wishes to roll dice twice for their turn
      When the player draws a TWO_SWORDS and rolls MONKEY, MONKEY, MONKEY, MONKEY, SWORD, PARROT, PARROT, COIN
      And the player re-rolls dice numbers 6,7
      And the player gets COIN, SWORD
      Then the player scores 1200 for this round


    Scenario Outline: A player dies after getting 3 skulls(including Skull fortune cards) on their first roll
      Given a player wishes to roll dice only once for their turn
      When the player draws a <fortune_card> and rolls <roll>
      Then the player scores <score> for this round
      Examples:
        |row|fortune_card|score|roll                                                  |
        |106 |TWO_SKULLS |0    |SKULL, SWORD, SWORD, SWORD, SWORD, SWORD, SWORD, SWORD|
        |107 |ONE_SKULL  |0    |SKULL, SKULL, SWORD, SWORD, SWORD, SWORD, SWORD, SWORD|

    Scenario Outline: A player re-rolls once after entering Skulls Island
      Given a player wishes to roll dice twice for their turn
      When the player draws a <fortune_card> and rolls <roll_1>
      And the player enters the Skulls Island
      And the player re-rolls dice numbers <dice_numbers>
      And the player gets <roll_2>
      Then the player scores 0 for this round
      And the other players receive a deduction of <deduction>
      Examples:
        |row|fortune_card|deduction|roll_1                                                       |dice_numbers|roll_2                      |
        |110|CAPTAIN     |1400     |SKULL, SKULL, SKULL, SKULL, SKULL, MONKEY, MONKEY, MONKEY    |6,7,8       |SKULL, SKULL, SWORD         |
        |111|TWO_SKULLS  |500      |SKULL, SKULL, SKULL, SWORD, SWORD, SWORD, SWORD, SWORD       |4,5,6,7,8   |COIN, COIN, COIN, COIN, COIN|
      
      Scenario: A player re-rolls twice after entering Skulls Island
        Given a player wishes to roll dice thrice for their turn
        When the player draws a TWO_SKULLS and rolls SKULL, SKULL, PARROT, PARROT, PARROT, MONKEY, MONKEY, MONKEY
        And the player enters the Skulls Island
        And the player re-rolls dice numbers 3,4,5
        And the player gets SKULL, SKULL, SWORD
        And the player re-rolls dice numbers 5,6,7,8
        And the player gets SKULL, SKULL, SKULL, SWORD
        Then the player scores 0 for this round
        And the other players receive a deduction of 900
      
    
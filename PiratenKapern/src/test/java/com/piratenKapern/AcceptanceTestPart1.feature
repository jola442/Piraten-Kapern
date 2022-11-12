Feature: Part 1 of the acceptance tests
  @Rows45,52,54,55,56,57,62,63,64,65,66,72
  Scenario Outline: A player scores their first dice roll
    Given a player wishes to roll dice only once for their turn
    When the player draws a <fortune_card> and rolls <roll>
    Then the player scores <score> for this round
    Examples:
      |row|fortune_card|score|roll                                                          |
      |45 |COIN        |0    |SKULL, SKULL, SKULL, SWORD, SWORD, SWORD, SWORD, SWORD        |
      |52 |CAPTAIN     |800  |MONKEY, MONKEY, PARROT,PARROT, DIAMOND, DIAMOND, COIN, COIN   |
      |54 |COIN        |300  |MONKEY, MONKEY, MONKEY, SWORD, SWORD, SWORD, SKULL, SKULL     |
      |55 |COIN        |500  |DIAMOND, DIAMOND, DIAMOND, SKULL, SKULL, MONKEY, SWORD, PARROT|
      |56 |DIAMOND     |700  |COIN, COIN, COIN, COIN, SKULL, SKULL, SWORD, SWORD            |
      |57 |COIN        |400  |SWORD, SWORD, SWORD, PARROT, PARROT, PARROT, PARROT, SKULL    |
      |62 |COIN        |1100 |MONKEY, MONKEY, MONKEY, MONKEY, MONKEY, MONKEY, SKULL, SKULL  |
      |63 |COIN        |2100 |PARROT, PARROT, PARROT, PARROT, PARROT, PARROT, PARROT, SKULL |
      |64 |COIN        |5400 |COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN                |
      |65 |DIAMOND     |5400 |COIN, COIN, COIN, COIN, COIN, COIN, COIN, COIN                |
      |66 |CAPTAIN     |9000 |SWORD, SWORD, SWORD, SWORD, SWORD, SWORD, SWORD, SWORD        |
      |72 |COIN        |600  |MONKEY, MONKEY, MONKEY, MONKEY, COIN, COIN, SKULL, SKULL      |

  @Rows46,47,53,58,59,67,68,69,70,71
  Scenario Outline: A player scores their second dice roll
    Given a player wishes to roll dice twice for their turn
    When the player draws a <fortune_card> and rolls <roll_1>
    And the player re-rolls dice numbers <dice_numbers>
    And the player gets <roll_2>
    Then the player scores <score> for this round
    Examples:
      |row|fortune_card|score|roll_1                                                      |dice_numbers|roll_2              |
      |46 |COIN        |0    |SKULL, PARROT, PARROT, PARROT, PARROT, SWORD, SWORD, SWORD  |6,7,8       |SKULL, SKULL, SWORD |
      |47 |COIN        |0    |SKULL, SKULL, PARROT, PARROT, PARROT, PARROT, SKULL, SWORD  |7,8         |SKULL, SWORD        |
      |53 |COIN        |300  |MONKEY, MONKEY, SKULL,SKULL, SWORD, SWORD, PARROT, PARROT   |7,8         |SWORD, MONKEY       |
      |58 |COIN        |800  |SKULL, COIN, COIN, PARROT, PARROT, SWORD, SWORD, SWORD      |4,5         |COIN, SWORD         |
      |59 |CAPTAIN     |1200 |SKULL, COIN, COIN, PARROT, PARROT, SWORD, SWORD, SWORD      |4,5         |COIN, SWORD         |
      |67 |COIN        |4600 |MONKEY, MONKEY, MONKEY, MONKEY, MONKEY, MONKEY, SWORD, SWORD|7,8         |MONKEY, MONKEY      |
      |68 |DIAMOND     |400  |MONKEY, MONKEY, SKULL, SKULL, SWORD, SWORD, PARROT, PARROT  |7,8         |DIAMOND, DIAMOND    |
      |69 |COIN        |500  |MONKEY, MONKEY, SKULL, SKULL, SWORD, SWORD, DIAMOND, PARROT |1,2         |DIAMOND, DIAMOND    |
      |70 |COIN        |600  |SKULL, COIN, COIN, MONKEY, PARROT, SWORD, SWORD, SWORD      |6,7,8       |COIN, MONKEY, PARROT|
      |71 |DIAMOND     |500  |SKULL, COIN, COIN, MONKEY, PARROT, SWORD, SWORD, SWORD      |6,7,8       |COIN, MONKEY, PARROT|

  @Rows49,51,61
  Scenario Outline: A player scores their third dice roll
    Given a player wishes to roll dice thrice for their turn
    When the player draws a <fortune_card> and rolls <roll_1>
    And the player re-rolls dice numbers <dice_numbers>
    And the player gets <roll_2>
    And the player re-rolls dice numbers <dice_numbers_2>
    And the player gets <roll_3>
    Then the player scores <score> for this round
    Examples:
      |row|fortune_card|score|roll_1                                                    |dice_numbers|roll_2              |dice_numbers_2|roll_3        |
      |49 |COIN        |0    |SKULL, PARROT, PARROT, PARROT, PARROT, SWORD, SWORD, SWORD|6,7,8       |SKULL, MONKEY, MONKEY|7,8          |SKULL,MONKEY  |
      |51 |COIN        |4800 |SKULL, PARROT, PARROT,SWORD, SWORD, SWORD, COIN, COIN     |2,3         |COIN, COIN           |4,5,6        |COIN,COIN,COIN|
      |61 |COIN        |600  |SKULL, MONKEY, MONKEY, PARROT, PARROT, SWORD, SWORD, SWORD|2,3         |SKULL, SWORD         |4,5          |SWORD, MONKEY |



